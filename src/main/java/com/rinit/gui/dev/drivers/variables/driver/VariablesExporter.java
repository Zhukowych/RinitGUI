package com.rinit.gui.dev.drivers.variables.driver;

import java.util.Map.Entry;

import com.rinit.gui.utils.XMLBuilder;

public class VariablesExporter {
	
	private VariablesDriver file;
	
	public VariablesExporter(VariablesDriver file) {
		this.file = file;
	}

	public String export() {
		XMLBuilder builder = new XMLBuilder();
		
		StringBuilder valuesBuilder = new StringBuilder();
		for (Entry<String, String> entry : this.file.getValues().entrySet()) {
			valuesBuilder.append(builder.addTag("value", builder.addGroup(
				builder.addTag("variableName", entry.getKey()),
				builder.addTag("varianleValue", entry.getValue())
			)));
		}
		
		return builder.addTag("variables", builder.addTag("values", valuesBuilder.toString()));
	}
	
}
