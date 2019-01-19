package com.jcdecaux.recruiting.developpers.service.rest;

import javax.ws.rs.core.Response.Status;

public class FunctionalException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6396797119658345586L;

	private final String description;
	private final Status status;

	public String getDescription() {
		return description;
	}
	public Status getStatus() {
		return status;
	}
	public FunctionalException(String description, Status status) {
		super();
		this.description = description;
		this.status = status;
	}


}
