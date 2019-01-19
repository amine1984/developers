package com.jcdecaux.recruiting.developpers.service.rest.impl;

import com.jcdecaux.recruiting.developpers.domain.service.IlanguagesService;
import com.jcdecaux.recruiting.developpers.service.rest.IlanguagesResource;
import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class LanguagesResourceImpl implements IlanguagesResource{

	@Context
	UriInfo uri;

	@Autowired
	private IlanguagesService languagesServices;

	@Override
	public Response createLanguages(List<LanguageDTO> languages) {
		if (CollectionUtils.isEmpty(languages)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		List<URI> uris = new ArrayList<>();
		List<Integer> idsLanguages = languagesServices.createLanguages(languages);
		idsLanguages.forEach(idLanguage -> {
			UriBuilder uriBuilder = UriBuilder.fromUri(uri.getRequestUri());
			uris.add(uriBuilder.path(String.valueOf(idLanguage)).build());
		});
		return Response.status(Status.CREATED).entity(uris).build();
	}

	@Override
	public Response viewLanguage(Integer idLanguage) {
		return Response.ok(languagesServices.viewLanguage(idLanguage)).build();
	}

}
