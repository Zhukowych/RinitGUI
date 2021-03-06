package com.rinit.gui.dev.drivers.apitestconfig.driver;

import com.rinit.gui.utils.XMLBuilder;

public class ApiTestConfigExporter {
	
	private ApiTestConfiigDriver file;
	
	public ApiTestConfigExporter(ApiTestConfiigDriver file) {
		this.file = file;	
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("apitestconfig",  builder.addGroup(
					builder.addTag("rootParseFolderPath", this.file.getRootParseFolderPath()),
					builder.addTag("filesToParse", builder.concat(this.file.getFilesToParse()))
				));
	}
	
}
