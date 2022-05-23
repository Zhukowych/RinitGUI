package com.rinit.gui.dev.core;

import java.util.Map;
import java.util.Map.Entry;

import com.rinit.debugger.server.file.library.LibraryDriver;

public abstract class DevLibrary {
	
	public abstract String getLibraryName();
	
	public abstract Map<String, Class<?>> getLibraryClasses();
	
	public LibraryDriver toLibraryDriver() {
		LibraryDriver library = new LibraryDriver();
		library.setName(this.getLibraryName());
		library.setPath(String.format("dev/%s", this.getLibraryName()));
		for (Entry<String, Class<?>> entry : this.getLibraryClasses().entrySet()) {
			library.addClass(entry.getKey(), entry.getValue());
		}
		return library;
	}
	
}
