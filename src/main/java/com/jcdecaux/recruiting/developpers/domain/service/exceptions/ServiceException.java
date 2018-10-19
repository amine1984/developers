package com.jcdecaux.recruiting.developpers.domain.service.exceptions;

public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -208623438617193024L;
	
	private String id;
	private String message;
	
	public ServiceException(String message){
		super();
		this.message = message;
	}
	public ServiceException(String message,String id) {
		
		super();
		this.message = message;
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
