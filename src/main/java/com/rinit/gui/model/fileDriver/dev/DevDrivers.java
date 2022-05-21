package com.rinit.gui.model.fileDriver.dev;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;

public class DevDrivers {
	
	private Map<String, Class<? extends AbstractCliDriver>> cliFileDrivers = new HashMap<String, Class<? extends AbstractCliDriver>>();
	private Map<String, Class<? extends AbstractDriver>> fileDrivers = new HashMap<String, Class<? extends AbstractDriver>>();

	public DevDrivers() {
		this.createDevCliDrivers();
		this.createDevDrivers();
	}
	
	public Map<String, Class<? extends AbstractCliDriver>> getCliFileDrivers(){
		return this.cliFileDrivers;
	}
	
	public Map<String, Class<? extends AbstractDriver>> getFileDrivers() {
		return fileDrivers;
	}

	public void createDevDrivers() {
		Reflections reflections = new Reflections("com.rinit.gui.dev.drivers", new SubTypesScanner(false));
		for(Class<? extends AbstractDriver> driverClass : reflections.getSubTypesOf(AbstractDriver.class)) {
			AbstractDriver driver = this.getDriverClass(driverClass);
			this.fileDrivers.put(driver.getName(), driverClass);
		}
	}
	
	private void createDevCliDrivers() {
		Reflections reflections = new Reflections("com.rinit.gui.dev.drivers", new SubTypesScanner(false));
		for(Class<? extends AbstractCliDriver> cliFileDriverClass : reflections.getSubTypesOf(AbstractCliDriver.class)) {
			AbstractCliDriver cliFileDriver = this.getCliFileDriverClass(cliFileDriverClass);
			this.cliFileDrivers.put(cliFileDriver.getName(), cliFileDriverClass);
		}
	}
	
	public AbstractDriver getDriverClass(Class<? extends AbstractDriver> driverClass) {
		try {
			return driverClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AbstractCliDriver getCliFileDriverClass(Class<? extends AbstractCliDriver> cliFileDriverClass) {
		try {
			return cliFileDriverClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
