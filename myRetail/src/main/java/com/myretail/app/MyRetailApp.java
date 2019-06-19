package com.myretail.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Hello world!
 *
 */
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages={"com.myretail.app"})
public class MyRetailApp 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(MyRetailApp.class, args);
    }
}
