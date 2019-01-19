package com.jcdecaux.recruiting.developpers.service.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.service.rest.dto.DeveloperDTO;

@Path("/developers")
@Service
public interface IdevelopersResource {

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	Response createDevelopers(List<DeveloperDTO> developers);
		
	@PUT
	@Path("/{id}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	Response updateDevelopers(@PathParam("id") Integer id, DeveloperDTO developer);
	
	
	@PUT
	@Path("{idDeveloper}/languages/{languageName}/{languageVersion}")
	@Produces({ "application/json" })
	Response associateLanguages(@PathParam("idDeveloper") Integer idDeveloper,@PathParam("languageName") String languageName,
									  @PathParam("languageVersion") String languageVersion);
	
	
	@GET
	@Path("/languages/")
	@Produces({ "application/json" })
	Response getDevelopers(@QueryParam("languageName") String languageName);

	@GET
	@Path("/{idDeveloper}")
	@Produces({ "application/json" })
	Response viewDeveloper(@PathParam("idDeveloper") Integer idDeveloper);
	
}
