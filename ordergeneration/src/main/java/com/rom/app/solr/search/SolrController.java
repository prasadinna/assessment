package com.rom.app.solr.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rom.app.order.management.OrderGenerator;
import com.rom.app.order.management.model.GeneratorStatistics;
import com.rom.app.order.management.model.OrderDetailSolrModel;
import com.rom.app.solr.search.service.SolrServicROM;

@RestController
@RequestMapping("/solr")
public class SolrController {

	@Autowired
	private SolrServicROM service;
	
	@Autowired
	private OrderGenerator ordergenerator;
	
	@RequestMapping(value = "/repo/startorder/{number}", method = RequestMethod.GET)
	public GeneratorStatistics startOrders(@PathVariable int number) {
		return ordergenerator.startOrderGeneration(number);
	}

	
	@RequestMapping(value = "/repo/query/{field}/{value}", method = RequestMethod.GET)
	public List<OrderDetailSolrModel> saveByRepo(@PathVariable String field, @PathVariable String value) {
		return service.findByContent(value);
	}

	
	
}
