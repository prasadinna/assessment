package com.myretail.app.products.model;

import java.math.BigDecimal;

public class MoneyModel {
	private Integer amount;
	private String currency;
	
	public Integer getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
