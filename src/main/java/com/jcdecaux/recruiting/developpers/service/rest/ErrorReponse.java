package com.jcdecaux.recruiting.developpers.service.rest;

public class ErrorReponse {
	
	private int errorCode;
	private String errorMessage;
	public int getErrorCode() {
		return errorCode;
	}
	
	public ErrorReponse(){
		
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ErrorReponse(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
	

}
