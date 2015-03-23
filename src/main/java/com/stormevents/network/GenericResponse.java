/*
 * Copyright Motorola 2014. 
 * 
 * Description: Class that generates a generic response.
 * 
 * Author: eliasq@motorola.com
 * 
 * Revision History
 * Author (CoreID)           Descrition  									 					 Date 
 * =================         ====================================================        	  ===========
 * eliasq       			 Initial Creation								  				  Jun 1, 2014
 */
package com.stormevents.network;

/**
 * Class that generates a generic response.
 * 
 * @author ricardo
 *
 */
public class GenericResponse {
	
	int code;
	
	Object content;
	
	public GenericResponse(){
		super();
	}

	public GenericResponse(int code, Object content){
		super();
		this.code = code;
		this.content = content;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
