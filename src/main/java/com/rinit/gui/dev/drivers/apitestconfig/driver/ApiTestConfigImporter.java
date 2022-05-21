package com.rinit.gui.dev.drivers.apitestconfig.driver;

import com.rinit.debugger.server.utils.XMLReader;

public class ApiTestConfigImporter {
	
	private ApiTestConfiigDriver file;
	private XMLReader reader;
	
	public ApiTestConfigImporter(ApiTestConfiigDriver file) {
		this.file = file;
		this.reader = new XMLReader(file.getContent());	
	}
	
	public void parse() {
		if (!this.reader.isOk())
			return;
		this.file.setQueryFindFolderPath(this.reader.getTagValueByName("queryFindFolderPath", "apitestconfig"));
		this.file.setQueryFileName(this.reader.getTagValueByName("queryFileName", "apitestconfig"));
		this.file.setVariablesFileName(this.reader.getTagValueByName("variablesFileName", "apitestconfig"));
	}
	
}
