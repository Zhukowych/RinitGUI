package com.rinit.gui.setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Properties;

public class SetUpPropertiesReader {
	
	public static final String INITIAL_PATH_KEY = "panels.initial.path";
	
	private String propertiesPath;
	private Properties properties = new Properties();
	
	public SetUpPropertiesReader() {
		String absolutePath = FileSystems.getDefault().getPath(" /").normalize().toAbsolutePath().toString().strip();
		this.propertiesPath = absolutePath + "rinitclient.properties";
		try {
			this.properties.load(new FileInputStream(this.propertiesPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readProperties() {
		System.out.println(this.properties.getProperty(INITIAL_PATH_KEY));
		FilePanelConfig.INITIAL_PATH = this.properties.getProperty(INITIAL_PATH_KEY);
	}
}
