package com.rinit.gui.dev.drivers.validator.driver;

import com.rinit.gui.utils.XMLBuilder;

public class ValidatorDriverExporter {
	
	private ValidatorDriver file;
	
	public ValidatorDriverExporter(ValidatorDriver file) {
		this.file = file;
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("requestValidator", 
				builder.addGroup(
							builder.addTag("requiredHttpCode", Integer.toString(this.file.getRequiredHttpCode()))
						));
	}
	
}
