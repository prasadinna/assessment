package com.myretail.app.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.app.products.model.MoneyModel;
import com.myretail.app.products.model.ProductDescriptionModel;
import com.myretail.app.products.model.ProductDetailsModel;
import com.myretail.app.products.service.ProductDetailsService;

@RestController
@RequestMapping("/api/product/")
public class ProductDetailsController {
	@Autowired
	private ProductDetailsService productService;
	

	@RequestMapping(value = "/v1/details/{id}", method = RequestMethod.GET)
	public ProductDetailsModel getProductDetails(@PathVariable("id") String productId) {
		return productService.getProductDetails(productId);
	}
	
	@RequestMapping(value = "/v1/description/{id}", method = RequestMethod.GET)
	public ProductDescriptionModel getProductDescription(@PathVariable("id") String productId) {
		return productService.getProductDescription(productId);
	}
	
	@RequestMapping(value = "/v1/pricing/{id}", method = RequestMethod.GET)
	public MoneyModel getProductPPrice(@PathVariable("id") String productId) {
		return productService.getProductCurrentPrice(productId);
	}
	
	@RequestMapping(value = "/v1/createdproduct/{id}", method = RequestMethod.POST)
	public ProductDetailsModel createNewProduct(@PathVariable("id") String productId, 
			@RequestBody ProductDetailsModel productDetails) {
		return productService.saveProduct(productDetails);
	}
	
	@RequestMapping(value = "/v1/updateproduct/{id}", method = RequestMethod.PUT)
	public ProductDetailsModel updateProductDetails(@PathVariable("id") String productId, 
			@RequestBody ProductDetailsModel productDetails) {
		return productService.updateProduct(productDetails);
	}
}
