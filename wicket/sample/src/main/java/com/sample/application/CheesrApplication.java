package com.sample.application;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.crypt.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.sample.application.model.Cheese;
import com.sample.application.page.Index;

@Component
@EnableAutoConfiguration
@ComponentScan
public class CheesrApplication extends WebApplication {
	private List<Cheese> cheeses = Arrays.asList(
	           new Cheese("Gouda", "Gouda is a yellowish Dutch[...]", 1.65),
	           new Cheese("Edam", "Edam (Dutch Edammer) is a D[...]", 1.05),
	           new Cheese("Maasdam", "Maasdam cheese is a Dutc[...]", 2.35),
	           new Cheese("Brie", "Brie is a soft cows' milk c[...]", 3.15),
	           new Cheese("Buxton Blue", "Buxton Blue cheese i[...]", 0.99),
	           new Cheese("Parmesan", "Parmesan is a grana, a [...]", 1.99),
	           new Cheese("Cheddar", "Cheddar cheese is a hard[...]", 2.95),
	           new Cheese("Roquefort", "Roquefort is a ewe's-m[...]", 1.67),
	           new Cheese("Boursin", "Boursin Cheese is a soft[...]", 1.33),
	           new Cheese("Camembert", "Camembert is a soft, c[...]", 1.69),
	           new Cheese("Emmental", "Emmental is a yellow, m[...]", 2.39),
	           new Cheese("Reblochon", "Reblochon is a French [...]", 2.99));
	
	 @Autowired
	  private ApplicationContext applicationContext;

	  @Override
	  public Class<? extends WebPage> getHomePage() {
	    return Index.class;
	  }

	  public static void main(String[] args) {
	    SpringApplication.run(CheesrApplication.class, args);
	  }

	  @Override
	  public void init() {
	    super.init();
	    getRequestCycleSettings().setResponseRequestEncoding(CharEncoding.UTF_8);
	    getMarkupSettings().setDefaultMarkupEncoding(CharEncoding.UTF_8);

	    getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
	    mountPage("/index", Index.class);
	  }
	  
	  public static CheesrApplication get() {
	        return (CheesrApplication) Application.get();
	}

	  public List<Cheese> getCheeses() {
	        return Collections.unmodifiableList(cheeses);
	}
	  @Override
      public Session newSession(Request request, Response response) {
          return new CheesrSession(request);
      }
}
