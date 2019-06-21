package com.rom.app.solr.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(multicoreSupport = true,basePackages = "com.rom.app")
@ComponentScan(basePackages={"com.rom.app"})
public class SolrConfiguration {

	@Value( "${app.local.solr.home}")
	private String solrHome;
	
	@Bean
	SolrServer solrServer() throws SolrServerException, IOException {
		//String solrHome = "/Users/i307242/assessment/solr/sample-spring-boot-data-solr-embedded/target/classes/solr-home";
				//getClass().getClassLoader().getResource("solr-home").getPath();
		System.out.println("solrHome:"+solrHome);
		CoreContainer container = CoreContainer.createAndLoad(solrHome, new File(solrHome + "/solr.xml"));

		EmbeddedSolrServer embeddedSolrServer = new EmbeddedSolrServer(container, "romcore");
		embeddedSolrServer.deleteByQuery("*:*");
		embeddedSolrServer.commit();
		return embeddedSolrServer;
	}

	
	
	
	@Bean
	public SolrTemplate solrTemplate(SolrServer solrServer) throws Exception {
		return new SolrTemplate(solrServer());
	}
}
