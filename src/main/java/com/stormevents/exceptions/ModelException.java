/*
 * Copyright Motorola 2014. 
 * 
 * Description: This exception should be thrown if any exception happens in model classes
 * 
 * Author: eliasq@motorola.com
 * 
 * Revision History
 * Author (CoreID)           Descrition  									  Date 
 * =================         ============================================     ===========
 * eliasq       			 Initial Creation								  Aug 12, 2014
 */
package com.stormevents.exceptions;


/**
 * This exception should be thrown if any exception happens in model classes
 * 
 * @author eliasq
 */
public class ModelException extends StormEventsException {

	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModelException(String message) {
		super(message);
	}

	public ModelException(Throwable cause) {
		super(cause);
	}

}
