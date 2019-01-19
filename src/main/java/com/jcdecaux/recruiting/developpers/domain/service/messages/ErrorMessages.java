package com.jcdecaux.recruiting.developpers.domain.service.messages;

public class ErrorMessages {

	public  static final String UPDATE_BAD_PARAMETERS_MESSAGE = "Developer to update not found";
	
	public  static final String ASSOCIATE_BAD_PARAMETERS_MESSAGE = "Developer id or Language not found";
	public static final String  CHECK_PARAMETERS_EMPTY_ERROR = "mandatory parameters call missed";
	
	private ErrorMessages() {
	    throw new IllegalStateException("Utility class");
	  }

}
