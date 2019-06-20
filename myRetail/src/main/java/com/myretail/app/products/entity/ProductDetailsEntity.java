package com.myretail.app.products.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_detail")
public class ProductDetailsEntity {
	@Id
	private int id;
	private String productId;
	private String name;
	private String description;
	private String supplier;
	private int priceAmount;
	private String priceCurrency;

	public int getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getSupplier() {
		return supplier;
	}

	public int getPriceAmount() {
		return priceAmount;
	}

	public String getPriceCurrency() {
		return priceCurrency;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public void setPriceAmount(int priceAmount) {
		this.priceAmount = priceAmount;
	}

	public void setPriceCurrency(String priceCurrency) {
		this.priceCurrency = priceCurrency;
	}
}
