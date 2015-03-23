/*
 * Copyright Motorola 2014. 
 * 
 * Description: Class that represents that nothing wrong occurred on the request.
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
 * Class that represents that nothing wrong occurred on the request.
 * 
 * @author ricardo
 *
 */
public class OK extends GenericResponse {

	public OK() {
		super(200, "");
	}

	public OK(Object content) {
		super(200, content);
	}

}
