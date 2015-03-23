/*
 * Copyright Motorola 2014. 
 * 
 * Description: Class that represents that something wrong occurred on the request.
 * 
 * Author: eliasq@motorola.com
 * 
 * Revision History
 * Author (CoreID)           Descrition  									 					 Date 
 * =================         ====================================================        	  ===========
 * eliasq       			 Initial Creation								  				  Jun 1, 2014
 * eliasq					 Added a mapping for SALESFORCE_REQUEST_ERROR error.			  Dec 29, 2014
 */
package com.stormevents.network;


/**
 * Class that represents that something wrong occurred on the request.
 * 
 * @author ricardo
 *
 */
public class Error extends GenericResponse {
	
	
	
	public Error() {
	}

	public Error(Object content) {
		super(400, content);
	}
	
	public Error(int code, Object content) {
		super(code, content);
	}
	
	
	/**
	 * A builder to create a exception response instances based on any exception. The backend is able to
	 * handle some known exception such as salesforce authentication exception and so on. When this kind of
	 * problem occurs, the backend needs to throw this error for a client.  So, this method recevied a exception
	 * from backend and converts to a respose to be sent to client.
	 * 
	 * If the exception is not mapped, an instance of Error class is returned with exception message inside.
	 * 
	 * @param exception
	 * @return
	 */
	public static Error build(Exception exception){
		return null;
		//return message by code error
	}
	

}
