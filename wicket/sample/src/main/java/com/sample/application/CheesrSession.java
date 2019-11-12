package com.sample.application;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.sample.application.service.Cart;

public class CheesrSession extends WebSession {
	private Cart cart = new Cart();

	public CheesrSession(Request request) {
		super(request);
	}

	public Cart getCart() {
		return cart;
	}
}
