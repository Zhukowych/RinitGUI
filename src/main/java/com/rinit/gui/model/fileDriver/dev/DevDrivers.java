package com.rinit.gui.model.fileDriver.dev;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;

public class DevDrivers {
	
	private Map<String, Class<? extends AbstractCliFileDriver>> cliFileDrivers = new HashMap<String, Class<? extends AbstractCliFileDriver>>();

	public DevDrivers() {
		this.createDevDrivers();
	}
	
	public Map<String, Class<? extends AbstractCliFileDriver>> getCliFileDrivers(){
		return this.cliFileDrivers;
	}
	
	private void createDevDrivers() {
		Reflections reflections = new Reflections("com.rinit.gui.dev.drivers", new SubTypesScanner(false));
		for(Class<? extends AbstractCliFileDriver> cliFileDriverClass : reflections.getSubTypesOf(AbstractCliFileDriver.class)) {
			AbstractCliFileDriver cliFileDriver = this.getCliFileDriverClass(cliFileDriverClass);
			this.cliFileDrivers.put(cliFileDriver.getName(), cliFileDriverClass);
		}
	}
	
	public AbstractCliFileDriver getCliFileDriverClass(Class<? extends AbstractCliFileDriver> cliFileDriverClass) {
		try {
			return cliFileDriverClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
