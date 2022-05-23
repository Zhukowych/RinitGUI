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
		this.file.setRootParseFolderPath(this.reader.getTagValueByName("rootParseFolderPath", "apitestconfig"));
		for (String innerXml : this.reader.innerXmls("fileToParse")){
			FileToParse fileToParse = new FileToParse();
			fileToParse.deserialize(innerXml);
			this.file.addFileToParse(fileToParse);
		}
		
	}
	
}
