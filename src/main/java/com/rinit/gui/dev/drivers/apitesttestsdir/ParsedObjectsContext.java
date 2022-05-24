package com.rinit.gui.dev.drivers.apitesttestsdir;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rinit.gui.dev.drivers.parsedobject.dirver.ParsedObjectDriver;

public class ParsedObjectsContext {
	
	private Map<String, ParsedObjectDriver> parsedObjects = new HashMap<String, ParsedObjectDriver>();

	public Map<String, ParsedObjectDriver> getParsedObjects() {
		return parsedObjects;
	}

	public void setParsedObjectDriver(Map<String, ParsedObjectDriver> parsedObjectDriver) {
		this.parsedObjects = parsedObjectDriver;
	}
	
	public void addParsedObject(ParsedObjectDriver parsedObject) {
		this.parsedObjects.put(parsedObject.getKey(), parsedObject);
	}

	public void addAll(List<ParsedObjectDriver> parsedObjects) {
		for (ParsedObjectDriver parsedObject : parsedObjects){
			this.addParsedObject(parsedObject);
		}
	} 
	
	public ParsedObjectDriver getParsedObject(String key) {
		return this.parsedObjects.get(key);
	}
	
}
