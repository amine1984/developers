package com.jcdecaux.recruiting.developpers.service.rest.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jcdecaux.recruiting.developpers.domain.service.IdevelopersService;
import com.jcdecaux.recruiting.developpers.domain.service.exceptions.ServiceException;
import com.jcdecaux.recruiting.developpers.domain.service.messages.ErrorMessages;
import com.jcdecaux.recruiting.developpers.service.rest.FunctionalException;
import com.jcdecaux.recruiting.developpers.service.rest.IdevelopersResource;
import com.jcdecaux.recruiting.developpers.service.rest.dto.DeveloperDTO;

@Component
public class DevelopersResourceImpl implements IdevelopersResource{

	@Autowired
	private IdevelopersService developersService;

	@Context
	UriInfo uri;


	@Override
	public Response createDevelopers(List<DeveloperDTO> developers) {
		if (CollectionUtils.isEmpty(developers)) {
			throw new FunctionalException(ErrorMessages.CHECK_PARAMETERS_EMPTY_ERROR,Status.BAD_REQUEST);
		}
		List<Integer> ids = developersService.createDevelopers(developers);

 		List<URI> uris = new ArrayList<>();
		ids.forEach(id -> {
			UriBuilder uriBuilder = UriBuilder.fromUri(uri.getRequestUri());
			uris.add(uriBuilder.path(String.valueOf(id)).build());
		});
		return Response.status(Status.CREATED).entity(uris).build();
	}

	@Override
	public Response updateDevelopers(Integer id, DeveloperDTO developer) {
		try {
			return Response.ok(developersService.updateDevelopers(id, developer)).build();
		} catch (ServiceException e) {
			 throw new FunctionalException(e.getMessage(),Status.BAD_REQUEST);
		}
		
	}

	@Override
	public Response searchDevelopers(String developerName) {

		List<DeveloperDTO> developers = developersService.searchDevelopers(developerName);

		if (StringUtils.isEmpty(developers)){ return Response.noContent().build();}

		return Response.ok(developersService.searchDevelopers(developerName)).build();
	}

	@Override
	public Response getDevelopers(String languageName) {
		if(StringUtils.isEmpty(languageName)){
			
			throw new FunctionalException(ErrorMessages.CHECK_PARAMETERS_EMPTY_ERROR,Status.BAD_REQUEST);
		}
		List<DeveloperDTO> developers = developersService.getDevelopers(languageName);
		if (CollectionUtils.isEmpty(developers)){
			return Response.noContent().build();
		}
		return Response.ok(developers).build();
	}

	@Override
	public Response viewDeveloper(Integer idDeveloper) {
		Optional<DeveloperDTO> developerDTO = developersService.viewDeveloper(idDeveloper);
		if (developerDTO.isPresent()){
			return Response.ok(developerDTO.get()).build();
		}
		return Response.noContent().build();
	}

	@Override
	public Response associateLanguages(Integer idDeveloper, String  languageName, String langugageVersion) {
		try {
			developersService.associateLanguages(idDeveloper, languageName,langugageVersion);	
		} catch (ServiceException e) {
			throw new FunctionalException(e.getMessage(),Status.BAD_REQUEST);
		}
		
		return Response.ok().build();
	}

	
}
