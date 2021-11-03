package com.rinit.gui.dev.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import com.rinit.gui.clibin.AbstractCliBin;

public class DevCliBins {
	
	private Map<String, Class<? extends AbstractCliBin>> devBins = new HashMap<String, Class<? extends AbstractCliBin>>();
	
	public DevCliBins() {
		this.locateDevBins();
	}
	
	public Map<String, Class<? extends AbstractCliBin>> getDevBins() {
		return this.devBins;
	}
	
	private void locateDevBins() {
		Reflections reflections = new Reflections("com.rinit.gui.dev.bin", new SubTypesScanner(false));
		for(Class<? extends DevCliBin> devBinClass : reflections.getSubTypesOf(DevCliBin.class)) {
			DevCliBin cliBin = this.getCliBinInstance(devBinClass);
			this.devBins.put(cliBin.getBinName(), cliBin.getBinClass());
		}
	}
	
	private DevCliBin getCliBinInstance(Class<? extends DevCliBin> devCliBinClass) {
		try {
			return devCliBinClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}
