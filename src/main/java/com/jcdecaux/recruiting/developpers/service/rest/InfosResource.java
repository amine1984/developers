package com.jcdecaux.recruiting.developpers.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

@Path("/infos")
@Service
public interface InfosResource {

	@GET
	String get();
	

}
