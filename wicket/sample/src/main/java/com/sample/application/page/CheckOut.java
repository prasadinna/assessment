package com.sample.application.page;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.sample.application.model.Address;
import com.sample.application.model.Cheese;
import com.sample.application.service.Cart;

public class CheckOut extends CheesrPage {
	public CheckOut() {
		add(new FeedbackPanel("feedback"));
		Form form = new Form("form");
		add(form);
		Address address = getCart().getBillingAddress();
		form.add(new TextField("name",
		                 new PropertyModel(address, "name")).setRequired(true));
		form.add(new TextField("street",
		                 new PropertyModel(address, "street")).setRequired(true));
		form.add(new TextField("zipcode",
		                 new PropertyModel(address, "zipCode")).setRequired(true));
		form.add(new TextField("city",
		                 new PropertyModel(address, "city")).setRequired(true));
		
		form.add(new Link("cancel") {
	           @Override
	           public void onClick() {
	               setResponsePage(Index.class);
	          }

			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				// TODO Auto-generated method stub
				return null;
			}
	       });
	       form.add(new Button("order") {
	    	   @Override
	    		public void onSubmit() {
	    		    Cart cart = getCart();
	    		    // charge customers’ credit card
	    		    // ship cheeses to our customer
	    		    // clean out shopping cart
	    		    cart.getCheeses().clear();
	    		    // return to front page
	    		    setResponsePage(Index.class);
	    		}
	}); 
	
	     //
	       add(
					
	    			 new ListView("cart",new PropertyModel
	    	                 (this, "cart.cheeses")){

	    						@Override
	    						protected void populateItem(ListItem item) {
	    							Cheese cheese = (Cheese) item.getModelObject();
	    							item.add(new Label("name", cheese.getName()));
	    							item.add(new Label("price", "$" + cheese.getPrice()));
	    							item.add(new Link("remove", item.getModel()) {

	    								@Override
	    								public MarkupContainer setDefaultModel(IModel model) {
	    									// TODO Auto-generated method stub
	    									return null;
	    								}

	    								@Override
	    								public void onClick() {
	    									Cheese selected = (Cheese) getModelObject();
	    									getCart().getCheeses().remove(selected);
	    								}
	    								
	    							});
	    						}
	    				 
	    			 }
	    			);
	    			add(new Label("total",new Model() {
	    			       @Override
	    			       public Serializable getObject() {
	    			            NumberFormat nf = NumberFormat.getCurrencyInstance();
	    			            return nf.format(getCart().getTotal());
	    			 } }));
	       
	       //
	
	}
	
	
}
