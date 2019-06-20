package com.myretail.app.products.model;

public class ProductDescriptionModel {
	private String id;
	private String name;
	private String description;
	private String supplier;
	public String getId() {
		return id;
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
	public void setId(String id) {
		this.id = id;
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
}
