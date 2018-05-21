
package com.omega.api;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.omega.api.config.JerseyConfig;

@SpringBootApplication(scanBasePackages = {"com.omega.api"})
public class PnlCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PnlCalculatorApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean<ServletContainer> jerseyServlet() {
		ServletRegistrationBean<ServletContainer> registration = new ServletRegistrationBean<ServletContainer>( new ServletContainer(), "/pnl/*");
		registration.addInitParameter( ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName() );
		return registration;
	}
	
}
