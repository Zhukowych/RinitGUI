package com.rinit.gui.dev.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.rinit.debugger.server.file.library.LibraryDriver;

public class DevLibrariesLocator {
	
	private Map<String, LibraryDriver> devLibs = new HashMap<String, LibraryDriver>();

	public DevLibrariesLocator() {
		this.locateDevLibs();
	}

	public Map<String, LibraryDriver> getDevLibs() {
		return devLibs;
	}
	
	public void locateDevLibs() {
		Reflections reflections = new Reflections("com.rinit.gui.dev.lib", new SubTypesScanner(false));
		for(Class<? extends DevLibrary> devLibClass : reflections.getSubTypesOf(DevLibrary.class)) {
			DevLibrary devLibrary = this.createDevLibraryInstance(devLibClass);
			LibraryDriver library = devLibrary.toLibraryDriver();
			this.devLibs.put(library.getPath(), library);
		}	
	}
	
	public DevLibrary createDevLibraryInstance(Class<? extends DevLibrary> devLibClass) {
		try {
			return devLibClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
