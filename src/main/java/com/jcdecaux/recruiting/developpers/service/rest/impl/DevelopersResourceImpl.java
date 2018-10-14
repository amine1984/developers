package com.jcdecaux.recruiting.developpers.service.rest.impl;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcdecaux.recruiting.developpers.domain.service.IdevelopersService;
import com.jcdecaux.recruiting.developpers.domain.service.exceptions.ServiceException;
import com.jcdecaux.recruiting.developpers.service.rest.IdevelopersResource;
import com.jcdecaux.recruiting.developpers.service.rest.dto.DeveloperDTO;

@Component
public class DevelopersResourceImpl implements IdevelopersResource{

	@Autowired
	private IdevelopersService developersService;
	
	@Override
	public Response createDevelopers(List<DeveloperDTO> developers) {
		if (developers == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok(developersService.createDevelopers(developers)).build();
	}

	@Override
	public Response updateDevelopers(Integer id, DeveloperDTO developer) {
		return Response.ok(developersService.updateDevelopers(id, developer)).build();
	}

	@Override
	public Response getDevelopers(String languageName) {
		if(languageName==null){
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok(developersService.getDevelopers(languageName)).build();
	}

	@Override
	public Response associateLanguages(Integer idDeveloper, String  languageName, String langugageVersion) {
		try {
			developersService.associateLanguages(idDeveloper, languageName,langugageVersion);	
		} catch (ServiceException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		return Response.ok().build();
	}

	
}
