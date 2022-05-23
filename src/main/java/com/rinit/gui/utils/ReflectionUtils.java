package com.rinit.gui.utils;

import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {
	
	public static<T> T createInstanceOf(Class<T> clazz) {
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
