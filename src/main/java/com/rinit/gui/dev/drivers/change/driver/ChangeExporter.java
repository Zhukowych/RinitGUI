package com.rinit.gui.dev.drivers.change.driver;

import com.rinit.gui.utils.XMLBuilder;

public class ChangeExporter {
	
	private ChangeDriver file;
	
	public ChangeExporter(ChangeDriver file) {
		this.file = file;
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("change", builder.addGroup(
			builder.addTag("message", this.file.getMessage()),
			builder.addTag("changedObjectPath", this.file.getChangedObjectPath()),
			builder.addTag("previousValue", this.file.getPreviousValue()),
			builder.addTag("currentValue", this.file.getCurrentValue())
		));
	}
	
}
