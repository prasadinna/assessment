package com.sample.application.model;

public class Address implements java.io.Serializable{
	private String name;
	private String street;
	private String zipCode;
	private String city;
	public String getName() {
		return name;
	}
	public String getStreet() {
		return street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
