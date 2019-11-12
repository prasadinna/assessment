package com.sample.application.page;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;

import com.sample.application.CheesrApplication;
import com.sample.application.CheesrSession;
import com.sample.application.model.Cheese;
import com.sample.application.service.Cart;

public abstract class CheesrPage extends WebPage {
	public CheesrSession getCheesrSession() {
		return (CheesrSession) getSession();
	}

	public Cart getCart() {
		return getCheesrSession().getCart();
	}

	public List<Cheese> getCheeses() {
		return CheesrApplication.get().getCheeses();
	}
}