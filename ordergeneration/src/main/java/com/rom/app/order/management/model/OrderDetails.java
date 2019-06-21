package com.rom.app.order.management.model;

import java.util.List;

public class OrderDetails {
	private long id;
	private String name;
	private List<Product> productList;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
