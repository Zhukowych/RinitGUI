package com.rinit.gui.dev.bin.debugger.bin;

import java.util.HashMap;
import java.util.Map;

public class RunContext {
	
	private Map<Class<?>, Object> context = new HashMap<Class<?>, Object>();
	
	public Object getContext(Class<?> contextClass) {
		return this.context.get(contextClass);
	}
	
	public void addContext(Object context) {
		this.context.put(context.getClass(), context);
	}
	
}
