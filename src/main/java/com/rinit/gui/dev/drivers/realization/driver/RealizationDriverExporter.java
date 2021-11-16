package com.rinit.gui.dev.drivers.realization.driver;

import com.rinit.gui.utils.XMLBuilder;

public class RealizationDriverExporter {
	
	private RealizationDriver file;
	
	public RealizationDriverExporter(RealizationDriver file) {
		this.file = file;
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("realization", builder.addGroup(
								builder.addTag("extention", this.file.getRealizationExtention())
							));
	}
	
}
