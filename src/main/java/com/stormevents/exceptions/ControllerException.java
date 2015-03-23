/*
 * Copyright Motorola 2014. 
 * 
 * Description: This exception should be thrown if any exception happens in any controller classes.
 * 
 * Author: jsilva@motorola.com
 * 
 * Revision History
 * Author (CoreID)           Descrition  									  Date 
 * =================         ============================================     ===========
 * jsilva       			 Initial Creation								  12/18/2014
 */
package com.stormevents.exceptions;

/**
 * This exception should be thrown if any exception happens in any controller classes.
 * 
 * @author johann
 *
 */
public class ControllerException extends StormEventsException {

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

	public ControllerException() {
	}

}
