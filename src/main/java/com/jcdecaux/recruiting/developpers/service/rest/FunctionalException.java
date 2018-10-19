package com.jcdecaux.recruiting.developpers.service.rest;

import javax.ws.rs.core.Response.Status;

public class FunctionalException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6396797119658345586L;
	
	private String id;
	private String descrption;
	private Status status;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescrption() {
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public FunctionalException(String id, String descrption, Status status) {
		super();
		this.id = id;
		this.descrption = descrption;
		this.status = status;
	}
	
	public FunctionalException(String descrption, Status status) {
		super();
		this.descrption = descrption;
		this.status = status;
	}
	

}
