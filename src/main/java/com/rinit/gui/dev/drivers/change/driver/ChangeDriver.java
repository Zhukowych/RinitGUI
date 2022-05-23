package com.rinit.gui.dev.drivers.change.driver;

import com.rinit.debugger.server.file.AbstractDriver;

public class ChangeDriver extends AbstractDriver {
	
	public static final String EXTENTION = "change";
	
	private String message;
	
	private String changedObjectPath;
	
	private String previousValue;
	private String currentValue;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getChangedObjectPath() {
		return changedObjectPath;
	}
	
	public void setChangedObjectPath(String changedObjectPath) {
		this.changedObjectPath = changedObjectPath;
	}
	
	public String getCurrentValue() {
		return currentValue;
	}
	
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public String getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}

	@Override
	protected void buildFromDTO() {
		ChangeImporter importer = new ChangeImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		this.setName(String.format("change_%d", System.currentTimeMillis()));
		this.setExtention(EXTENTION);
		ChangeExporter exporter = new ChangeExporter(this);
		return exporter.export();
	}
	
}
