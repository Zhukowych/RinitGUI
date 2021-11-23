package com.rinit.gui.exceptions;

public class DriverNotFoundException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -860434462932664605L;

	public DriverNotFoundException(String message) {
		super(message);
	}
	
	public DriverNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
	 
}
