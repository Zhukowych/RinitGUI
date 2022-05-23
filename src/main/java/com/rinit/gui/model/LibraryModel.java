package com.rinit.gui.model;

import java.util.HashMap;
import java.util.Map;

import com.rinit.debugger.server.file.library.LibraryDriver;
import com.rinit.gui.dev.core.DevLibrariesLocator;
import com.rinit.gui.event.IEventHandler;

public class LibraryModel extends AbstractModel {
		
	private Map<String, LibraryDriver> libraries = new HashMap<String, LibraryDriver>();
	
	public LibraryModel(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler);
		this.addDefaultLibraries();
	}

	public Map<String, LibraryDriver> getLibraries() {
		return libraries;
	}
	
	private void addDefaultLibraries() {
		DevLibrariesLocator locator = new DevLibrariesLocator();
		this.libraries.putAll(locator.getDevLibs());		
	}	
	
}
