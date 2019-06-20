package com.myretail.app.products.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myretail.app.products.entity.ProductDetailsEntity;
import com.myretail.app.products.model.MoneyModel;
import com.myretail.app.products.model.ProductDescriptionModel;
import com.myretail.app.products.model.ProductDetailsModel;
import com.myretail.app.products.repository.ProductDetailsRepository;

@Service
public class ProductDetailsService {
	@Autowired
	private ProductDetailsRepository productRepo;
	
	@Value( "${product.details.url}" )
	private String productDescriptionUrl;
	
	@Value( "${product.pricing.url}" )
	private String pricingUrl;
	
	 public ProductDetailsModel getProductDetails(String productId){
		 ProductDescriptionModel prodDescriotion = getRemoteProductDescription(productId);
		 MoneyModel price = getRemoteProductPrice(productId);
		 return mergeProductDetails(prodDescriotion,price);
	 }
	 
	 public ProductDescriptionModel getProductDescription(String productId){
		 ProductDetailsEntity productEntity = productRepo.findByProductId(productId);
		 ProductDescriptionModel descriptionModel = new ProductDescriptionModel();
		 descriptionModel.setId(productId);
		 descriptionModel.setName(productEntity.getName());
		 descriptionModel.setDescription(productEntity.getDescription());
		 descriptionModel.setSupplier(productEntity.getSupplier());
		 return descriptionModel;
	 }
	 
	 public MoneyModel getProductCurrentPrice(String productId){
		 ProductDetailsEntity productEntity = productRepo.findByProductId(productId);
		 MoneyModel price = new MoneyModel();
		 price.setAmount(productEntity.getPriceAmount());
		 price.setCurrency(productEntity.getPriceCurrency());
		 return price;
	 }
	 
	 private ProductDescriptionModel getRemoteProductDescription(String productId){
		 
		 HttpHeaders headers = new HttpHeaders();
	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     HttpEntity <String> entity = new HttpEntity<String>(headers);
	     RestTemplate restTemplate= new RestTemplate();
	     ProductDescriptionModel descriptionModel = 
	    		 restTemplate.exchange(productDescriptionUrl+productId+"/", HttpMethod.GET, entity, ProductDescriptionModel.class).getBody();
	     return descriptionModel;
	}

	 
	 private MoneyModel getRemoteProductPrice(String productId){
		 HttpHeaders headers = new HttpHeaders();
	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     HttpEntity <String> entity = new HttpEntity<String>(headers);
	     RestTemplate restTemplate= new RestTemplate();
	     MoneyModel price = 
	    		 restTemplate.exchange(pricingUrl+productId+"/", HttpMethod.GET, entity, MoneyModel.class).getBody();
	     return price;
	 }
	 
	 private ProductDetailsModel mergeProductDetails(ProductDescriptionModel descrition,MoneyModel price){
		 ProductDetailsModel productDetails = new ProductDetailsModel();
		 productDetails.setPrice(price);
		 productDetails.setId(descrition.getId());
		 productDetails.setName(descrition.getName());
		 return productDetails;
	 }
	 
	 public ProductDetailsModel saveProduct(ProductDetailsModel productDetailsModel){
		 ProductDetailsEntity newEntity = new ProductDetailsEntity();
		 newEntity.setProductId(productDetailsModel.getId());
		 newEntity.setName(productDetailsModel.getName());
		 newEntity.setPriceAmount(productDetailsModel.getPrice().getAmount());
		 productRepo.save(newEntity);
		 return productDetailsModel;
	 }
	 
	 public ProductDetailsModel updateProduct(ProductDetailsModel productDetailsModel){
		 ProductDetailsEntity dbEntity = productRepo.findByProductId(productDetailsModel.getId());
		 if(dbEntity != null){
			 if(productDetailsModel.getName() != null && !productDetailsModel.getName().equals(dbEntity.getName())){
				 dbEntity.setName(productDetailsModel.getName());
			 }
			 if(productDetailsModel.getPrice() != null){
				 if(productDetailsModel.getPrice().getAmount() != dbEntity.getPriceAmount() ||
						 !productDetailsModel.getPrice().getCurrency().equals(dbEntity.getPriceCurrency())){
					 dbEntity.setName(productDetailsModel.getName());
					 dbEntity.setPriceAmount(productDetailsModel.getPrice().getAmount());
				 }
			 }
			 productRepo.save(dbEntity);
		 }
		 return productDetailsModel;
	 }
	 
}
