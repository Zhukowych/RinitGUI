package com.rinit.gui.dev.bin.debugger.bin.context;

import java.util.HashMap;
import java.util.Map;

import com.rinit.debugger.server.file.AbstractDriver;

public class AbstractionContext {
	
	private Map<String, AbstractDriver> realizations = new HashMap<String, AbstractDriver>();
	
	public void addRealization(String name, AbstractDriver realization) {
		this.realizations.put(name, realization);
	}
	
	public AbstractDriver getRealization(String name) {
		return this.realizations.get(name);
	}
	
}
