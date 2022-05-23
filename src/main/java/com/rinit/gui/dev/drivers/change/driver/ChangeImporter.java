package com.rinit.gui.dev.drivers.change.driver;

import com.rinit.debugger.server.utils.XMLReader;


public class ChangeImporter {

	private ChangeDriver file;
	private XMLReader reader;
	
	public ChangeImporter(ChangeDriver file) {
		this.file = file;
		this.reader = new XMLReader(file.getContent());	
	}
	
	public void parse() {
		if (!this.reader.isOk())
			return;
		
		this.file.setMessage(this.reader.getTagValueByName("message", "change"));
		this.file.setChangedObjectPath(this.reader.getTagValueByName("changedObjectPath", "change"));
		this.file.setPreviousValue(this.reader.getTagValueByName("previousValue", "change"));
		this.file.setCurrentValue(this.reader.getTagValueByName("currentValue", "change"));
	
	}
	
}
