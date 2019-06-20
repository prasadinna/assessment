package com.myretail.app.products.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myretail.app.products.entity.ProductDetailsEntity;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity,Integer> {

	public ProductDetailsEntity findByProductId(String productId);

}
