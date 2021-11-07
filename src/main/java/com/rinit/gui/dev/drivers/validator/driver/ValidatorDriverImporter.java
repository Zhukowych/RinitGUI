package com.rinit.gui.dev.drivers.validator.driver;

import com.rinit.debugger.server.utils.XMLReader;

public class ValidatorDriverImporter {

	private ValidatorDriver file;
	private XMLReader reader;
	
	public ValidatorDriverImporter(ValidatorDriver file) {
		this.file = file;
		this.reader = new XMLReader(this.file.getContent());
	}
	
	public void parse() {
		this.file.setRequiredHttpCode(Integer.parseInt(this.reader.getTagValueByName("requiredHttpCode", "requestValidator")));
	}

}
