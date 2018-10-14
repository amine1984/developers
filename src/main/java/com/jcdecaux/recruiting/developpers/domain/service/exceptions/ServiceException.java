package com.jcdecaux.recruiting.developpers.domain.service.exceptions;

public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -208623438617193024L;
	
	private String message;
	public ServiceException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
