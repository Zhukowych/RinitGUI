package com.rinit.gui.dev.drivers.realization.driver;

import com.rinit.debugger.server.utils.XMLReader;

public class RealizationDriverImporter {
	
	private XMLReader reader;
	private RealizationDriver file;
	
	
	public RealizationDriverImporter(RealizationDriver file) {
		this.file = file;
		this.reader = new XMLReader(this.file.getContent());
	}

	public void parse() {
		if (!this.reader.isOk())
			return;
		this.file.setRealizationExtention(this.reader.getTagValueByName("extention", "realization"));
	}
	
}
