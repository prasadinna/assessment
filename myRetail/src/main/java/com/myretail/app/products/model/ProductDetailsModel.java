package com.myretail.app.products.model;

public class ProductDetailsModel {
	private String id;
	private String name;
	private MoneyModel price;
	private int errorCode;
	private String errorMessage;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public MoneyModel getPrice() {
		return price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(MoneyModel price) {
		this.price = price;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
