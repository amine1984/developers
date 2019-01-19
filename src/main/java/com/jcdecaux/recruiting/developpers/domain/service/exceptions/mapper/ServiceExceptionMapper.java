package com.jcdecaux.recruiting.developpers.domain.service.exceptions.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.jcdecaux.recruiting.developpers.service.rest.ErrorReponse;
import com.jcdecaux.recruiting.developpers.service.rest.FunctionalException;

@Provider
@Component
public class ServiceExceptionMapper implements ExceptionMapper<FunctionalException>{

	@Override
	public Response toResponse(FunctionalException exception) {

		ErrorReponse errorReponse = new ErrorReponse(); 
		errorReponse.setErrorCode(exception.getStatus().getStatusCode());
		errorReponse.setErrorMessage(exception.getDescription());
		return Response.status(exception.getStatus()).entity(errorReponse).build();
	}

}
