package com.stormevents.util;

import java.util.logging.Level;
import java.util.logging.Logger;



public class StormEventsLogger {
	
	private final Logger logger;
	private static boolean isDebug = true;
	
	public static StormEventsLogger getLogger(Class clazz){
		return new StormEventsLogger(clazz, isDebug);
	}
	
	private StormEventsLogger(Class clazz, boolean isDebug){
		logger = Logger.getLogger(clazz.getName());
		this.isDebug = isDebug;
	}
	
	public void info(String message){
		logger.info(message);
	}

	public void debug(String message){
		if (isDebug){
			logger.info("[DEBUG] - " + message);	
		}
	}
	
	public void error(String message){
		logger.severe(message);
	}	
	
	public void error(String message, Object object){
		logger.log(Level.SEVERE, message, object);
	}
	
	public void error(String message, StackTraceElement[] trace){
		StringBuffer error = new StringBuffer();
		error.append(message);
		for (StackTraceElement stackTraceElement : trace) {
			error.append(stackTraceElement.toString() + "\r\n");
		}
		
		error(error.toString());
	}
	
	public void warning(String message){
		logger.warning(message);
	}	
}
