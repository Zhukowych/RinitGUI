package com.rinit.gui.dev.drivers.debugreport.driver;

import com.rinit.debugger.server.utils.XMLReader;

public class DebugReportImporter {
	
	private DebugReportDriver file;
	private XMLReader reader;
	
	public DebugReportImporter(DebugReportDriver file) {
		this.file = file;
		this.reader = new XMLReader(file.getContent());
	}
	
	public void parse() {
		if(!this.reader.isOk())
			return;
		String[][] table = reader.getTableData("report", new String[] {"name", "type", "shortReport", "fullReport", "time"});

	}
	
}
;