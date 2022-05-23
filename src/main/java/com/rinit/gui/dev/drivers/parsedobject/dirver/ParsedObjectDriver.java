package com.rinit.gui.dev.drivers.parsedobject.dirver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rinit.debugger.server.file.AbstractDriver;

public class ParsedObjectDriver extends AbstractDriver {

	public static final String EXTENTION = "parsedobject";
	
	private String key;
	
	private Map<String, String> values = new HashMap<String, String>();
	
	public ParsedObjectDriver() {}
	
	public ParsedObjectDriver(String key) {
		this.key = key;
	}
	
 	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public void addValue(String valueType, String value) {
		this.values.put(valueType, value);		
	}
	
	@Override
	protected void buildFromDTO() {
		ParsedObjectImporter importer = new ParsedObjectImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		this.setName(key);
		this.setExtention(EXTENTION);
		ParsedObjectExporter exporter = new ParsedObjectExporter(this);
		return exporter.export();
	}
	
	public String toString() {
		return String.format("ParsedObjectDriver[key='%s', values=%s]", this.key, this.values);
	}
	
}
