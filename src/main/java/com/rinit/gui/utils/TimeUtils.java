package com.rinit.gui.utils;

public class TimeUtils {
	
	public static long nanoToMiliSeconds(long nanoseconds) {
		return Math.round(nanoseconds / 1000000);
	}
	
	public static String toMsString(long miliseconds) {
		return String.format("%d ms", miliseconds);
	}
}
