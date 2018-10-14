package com.jcdecaux.recruiting.developpers.service.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;

@Path("/languages")
@Service
public interface IlanguagesResource {
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createLanguages(List<LanguageDTO> languages);

}
