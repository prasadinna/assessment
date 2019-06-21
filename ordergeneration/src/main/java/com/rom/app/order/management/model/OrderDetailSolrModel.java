package com.rom.app.order.management.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@SolrDocument
public class OrderDetailSolrModel {
	@Id
	@Indexed(name = "id", type = "long")
	private long id;
	
	@Indexed(name = "name", type = "string")
	private String name;

	@Indexed(name = "description", type = "string")
	private String description;
	
	public OrderDetailSolrModel(){}
	public OrderDetailSolrModel(OrderDetails order){
		this.id = order.getId();
		this.name = order.getName();
		StringBuffer desc = new StringBuffer();
		for(Product prd:order.getProductList()){
			desc = desc.append(prd.getProductId()).append(" from: ").
					append(prd.getFromLocation()).append(" to: ").
					append(prd.getToLocation());
		}
		this.description = desc.toString();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
