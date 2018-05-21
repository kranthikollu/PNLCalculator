package com.omega.api.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.omega.api.controller.PnlController;

public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
		register(RequestContextFilter.class);
		register(PnlController.class);
	}

}
