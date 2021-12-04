package com.rinit.gui.setup;

import org.slf4j.LoggerFactory;

import com.github.weisj.darklaf.LafManager;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class Configurer {
	
	public Configurer() {
		
	}
	
	public void config() {
		this.configureLogging();
		this.configureLookAndFeel();
		this.configureFromProperties();
	}
	
	public void configureLogging() {
		Logger reflectionsLogger = (Logger) LoggerFactory.getLogger("org.reflections");
		Logger restTemplateLogger = (Logger) LoggerFactory.getLogger("org.springframework");
		reflectionsLogger.setLevel(Level.OFF);
		restTemplateLogger.setLevel(Level.OFF);
	}

	public void configureLookAndFeel() {
		LafManager.install();
	}
	
	public void configureFromProperties() {
		SetUpPropertiesReader reader = new SetUpPropertiesReader();
		reader.readProperties();
	}
}
