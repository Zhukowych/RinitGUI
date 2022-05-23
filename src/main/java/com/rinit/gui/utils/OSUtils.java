package com.rinit.gui.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class OSUtils {

	public static List<Path> getAllSubDirsOf(String initialPath){
		List<Path> dirs = null;
		try {
			dirs = Files.walk(Paths.get(initialPath), 1).filter(Files::isDirectory).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	    return dirs;
	}
	
}
