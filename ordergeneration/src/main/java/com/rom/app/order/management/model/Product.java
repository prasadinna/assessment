package com.rom.app.order.management.model;

public class Product {


	private String productId;
	private int orderQuantity;
	private String fromLocation;
	private String toLocation;
	private String unitOfMeasure;
	private String uomType;
    public Product(){}

	public Product(String productId, int orderQuantity, String fromLocation, String toLocation, String unitOfMeasure,
			String uomType) {
		super();
		this.productId = productId;
		this.orderQuantity = orderQuantity;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.unitOfMeasure = unitOfMeasure;
		this.uomType = uomType;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public String getUomType() {
		return uomType;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public void setUomType(String uomType) {
		this.uomType = uomType;
	}

	public String getProductId() {
		return productId;
	}
}
