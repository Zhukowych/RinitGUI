package com.rinit.gui.dev.bin.debugger.bin.context;

import java.util.HashMap;
import java.util.Map;

public class RunContext {
	
	private Map<Class<?>, Object> context = new HashMap<Class<?>, Object>();
	
	public<T> T getContext(Class<T> contextClass) {
		return (T) this.context.get(contextClass);
	}
	
	public void addContext(Object context) {
		this.context.put(context.getClass(), context);
	}
	
}
