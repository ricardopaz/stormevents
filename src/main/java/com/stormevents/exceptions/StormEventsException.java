package com.stormevents.exceptions;

public class StormEventsException extends Exception {

	public StormEventsException(String message, Throwable cause) {
		super(message, cause);
	}

	public StormEventsException(String message) {
		super(message);
	}
	
	public StormEventsException(Throwable cause) {
		super(cause);
	}

	public StormEventsException() {
	}
}
