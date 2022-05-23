package com.rinit.gui.dev.drivers.apitestconfig.driver;

import com.rinit.debugger.server.utils.XMLReader;
import com.rinit.gui.utils.XMLBuilder;

public class FileToParse {
	
	private String name;
	private String fileNameRegex;
	private String keyRegex;
	private String valueRegex;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFileNameRegex() {
		return fileNameRegex;
	}
	
	public void setFileNameRegex(String fileName) {
		this.fileNameRegex = fileName;
	}
	
	public String getKeyRegex() {
		return keyRegex;
	}
	
	public void setKeyRegex(String keyRegex) {
		this.keyRegex = keyRegex;
	}
	
	public String getValueRegex() {
		return valueRegex;
	}
	
	public void setValueRegex(String valueRegex) {
		this.valueRegex = valueRegex;
	}
	
	public String toString() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("fileToParse", builder.addGroup(
					builder.addTag("name", this.name),
					builder.addTag("fileNameRegex", this.fileNameRegex),
					builder.addTag("keyRegex", this.keyRegex),
					builder.addTag("valueRegex", this.valueRegex)
				));	
	}
	
	public void deserialize(String xml) {
		XMLReader reader = new XMLReader(xml);
		if (!reader.isOk())
			return;
		this.name = reader.getTagValueByName("name", "fileToParse");
		this.fileNameRegex = reader.getTagValueByName("fileNameRegex", "fileToParse");
		this.keyRegex = reader.getTagValueByName("keyRegex", "fileToParse");
		this.valueRegex = reader.getTagValueByName("valueRegex", "fileToParse");		
	}
	
}
