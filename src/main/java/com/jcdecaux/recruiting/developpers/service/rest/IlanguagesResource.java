package com.jcdecaux.recruiting.developpers.service.rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;

@Path("/languages")
@Service
public interface IlanguagesResource {
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	Response createLanguages(List<LanguageDTO> languages);

	@GET
	@Path("/{idLanguage}")
	@Produces("application/json")
	Response viewLanguage(@PathParam("idLanguage") Integer idLanguage);

}
