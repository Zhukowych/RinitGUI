package com.rinit.gui.exceptions;

public class LogicException extends Exception {

	public LogicException(String message) {
		super(message);
	}
	
	public LogicException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
