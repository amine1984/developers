package com.jcdecaux.recruiting.developpers.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.stereotype.Component;


@Component
@ApplicationPath("/developers-api/")
public class JaxrsApplication extends Application{
	
	

}
