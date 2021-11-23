package com.rinit.gui.exceptions;

public class LogicException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6035628984786394785L;

	public LogicException(String message) {
		super(message);
	}
	
	public LogicException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
