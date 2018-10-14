package com.jcdecaux.recruiting.developpers.service.rest.impl;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcdecaux.recruiting.developpers.domain.service.IlanguagesService;
import com.jcdecaux.recruiting.developpers.service.rest.IlanguagesResource;
import com.jcdecaux.recruiting.developpers.service.rest.dto.LanguageDTO;

@Component
public class LanguagesResourceImpl implements IlanguagesResource{

	@Autowired
	private IlanguagesService languagesSerives;
	@Override
	public Response createLanguages(List<LanguageDTO> languages) {
		if (languages == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.ok(languagesSerives.createLanguages(languages)).build();
	}

}
