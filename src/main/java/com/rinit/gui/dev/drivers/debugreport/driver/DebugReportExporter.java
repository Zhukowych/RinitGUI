package com.rinit.gui.dev.drivers.debugreport.driver;

import com.rinit.gui.utils.XMLBuilder;

public class DebugReportExporter {
	
	private DebugReportDriver file;
	
	public DebugReportExporter(DebugReportDriver file) {
		this.file = file;
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("report", builder.concat(this.file.getReportItems()));
	}
	
}
