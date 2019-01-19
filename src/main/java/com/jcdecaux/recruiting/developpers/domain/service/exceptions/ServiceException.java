package com.jcdecaux.recruiting.developpers.domain.service.exceptions;

public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -208623438617193024L;

	private final String message;

	public ServiceException(String message) {
		this.message= message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
