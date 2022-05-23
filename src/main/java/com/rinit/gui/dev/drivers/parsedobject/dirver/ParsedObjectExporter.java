package com.rinit.gui.dev.drivers.parsedobject.dirver;

import java.util.Map.Entry;

import com.rinit.gui.utils.XMLBuilder;

public class ParsedObjectExporter {
	
	private ParsedObjectDriver file;
	
	public ParsedObjectExporter(ParsedObjectDriver file) {
		this.file = file;	
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		
		StringBuilder valuesBuilder = new StringBuilder();
		
		for (Entry<String, String> entry : this.file.getValues().entrySet()) {
			valuesBuilder.append(builder.addTag("value", builder.addGroup(
				builder.addTag("valueType", entry.getKey()),
				builder.addTag("valueContent", entry.getValue())
			)));
		}		
		
		return builder.addTag("parsedObject", builder.addGroup(
			builder.addTag("key", this.file.getKey()),
			builder.addTag(
				"values", 
				valuesBuilder.toString()
			)
		));
	}
	
}
