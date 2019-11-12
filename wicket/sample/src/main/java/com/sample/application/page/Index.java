package com.sample.application.page;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.sample.application.model.Cheese;
import com.sample.application.service.IBarService;

public class Index extends CheesrPage {


	@SpringBean
	private IBarService barService;

	public Index() {
		
		PageableListView cheeses = new PageableListView("cheeses", getCheeses(), 5) {
			@Override
			protected void populateItem(ListItem item) {
				Cheese cheese = (Cheese) item.getModelObject();
				item.add(new Label("name", cheese.getName()));
				item.add(new Label("description", cheese.getDescription()));
				item.add(new Label("price", "$" + cheese.getPrice()));
				item.add(new Link("add", item.getModel()) {
					@Override
					public void onClick() {
						Cheese selected = (Cheese) getModelObject();
						getCart().getCheeses().add(selected);
					}

					@Override
					public MarkupContainer setDefaultModel(IModel model) {
						// TODO Auto-generated method stub
						return null;
					}
				});
			}
		};
		add(cheeses);
		add(new PagingNavigator("navigator", cheeses));
		/*add(
				new ListView("cheeses", getCheeses()) {

					@Override
					protected void populateItem(ListItem item) {
						Cheese cheese = (Cheese) item.getModelObject();
						item.add(new Label("name", cheese.getName()));
						item.add(new Label("description",cheese.getDescription()));
						item.add(new Label("price", "$" + cheese.getPrice()));
						item.add(new Link("add", item.getModel()) {
							@Override
							public void onClick() {
								Cheese selected = (Cheese) getModelObject();
								getCart().getCheeses().add(selected);
							}

							@Override
							public MarkupContainer setDefaultModel(IModel model) {
								// TODO Auto-generated method stub
								return null;
							}
						});
					}
					
				}
				
			); */
		
		add(new ShoppingCartPanel("shoppingcart", getCart()));
		/*add(new Label("total",new Model() {
		       @Override
		       public Serializable getObject() {
		            NumberFormat nf = NumberFormat.getCurrencyInstance();
		            return nf.format(getCart().getTotal());
		 } }));*/
		
		add(new Link("checkout"){

			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void onClick() {
				setResponsePage(new CheckOut());
				
			}
			
			@Override
	        public boolean isVisible() {
				return !getCart().getCheeses().isEmpty();
			}
		});
	}

}