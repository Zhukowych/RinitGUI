package com.rinit.gui.exceptions;

public class DriverNotFoundException extends Exception  {

	public DriverNotFoundException(String message) {
		super(message);
	}
	
	public DriverNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
	 
}
