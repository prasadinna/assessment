package com.rom.app.solr.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rom.app.order.management.model.OrderDetailSolrModel;
import com.rom.app.order.management.model.OrderDetails;
import com.rom.app.solr.search.repository.ModelSolrRepository;

@Service
public class SolrServicROM {
	@Autowired
	private ModelSolrRepository modelRepository;
	
	public void indexDocuments(List<OrderDetails> orderList){
		List<OrderDetailSolrModel> solrdocList = new ArrayList(orderList.size());
		for(OrderDetails order:orderList){
			OrderDetailSolrModel doc = new OrderDetailSolrModel(order);
			solrdocList.add(doc);
		}
		modelRepository.save(solrdocList);
	}
	
	public List<OrderDetailSolrModel> findByContent(String query){
		return modelRepository.queryByContent(query);
	}
}
