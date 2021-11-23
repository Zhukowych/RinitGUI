package com.rinit.gui.dev.drivers.validator;

public class ValidatorSubmitData {
	
	public int requiredHttpCode;
	public String requiredContent;
	
	public ValidatorSubmitData() {}
	
	public ValidatorSubmitData(int requiredHttpCode, String requiredContent) {
		this.requiredHttpCode = requiredHttpCode;
		this.requiredContent = requiredContent;
	}
}
