package com.sample.application;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebInitializer implements ServletContextInitializer {

  @Override
  public void onStartup(ServletContext context) throws ServletException {
    FilterRegistration filter = context.addFilter("wicket-filter", WicketFilter.class);
    filter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
    filter.setInitParameter("applicationBean", "cheesrApplication");
    filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
    filter.addMappingForUrlPatterns(null, false, "/*");
      filter.setInitParameter("configuration", "development");
  }

}