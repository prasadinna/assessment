package com.rom.app.solr.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rom.app.order.management.OrderCreateListener;
import com.rom.app.order.management.OrderGenerator;
import com.rom.app.order.management.model.OrderDetailSolrModel;
import com.rom.app.order.management.model.OrderDetails;
import com.rom.app.solr.search.service.SolrServicROM;
@Component
public class IndexOrderListener implements OrderCreateListener {

	@Autowired
	private SolrServicROM service;
	
	private OrderGenerator ordergenerator;
	
	@Autowired
	public void setOrdergenerator(OrderGenerator ordergenerator ){
		this.ordergenerator= ordergenerator;
		ordergenerator.registerListener(this);
		System.out.println("onApplicationEvent called event registered.......");
	}
	
	
	
	
	
	/*@PostConstruct
	public void register(){
		System.out.println("register the listener.......");
		ordergenerator.registerListener(this);
		System.out.println("register the listener complete.......");
	}*/
	
	@Override
	public void recieveOrder(List<OrderDetails> orderList) {
		Runnable indexTask = new Runnable(){
			@Override
			public void run() {
				service.indexDocuments(orderList);
				
			}
			
		};
		Thread indexer = new Thread(indexTask);
		indexer.start();

	}
	

}
