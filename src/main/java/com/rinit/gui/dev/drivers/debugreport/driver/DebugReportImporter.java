package com.rinit.gui.dev.drivers.debugreport.driver;

import com.rinit.debugger.server.utils.XMLReader;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;

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
		String[][] table = reader.getTableData("reportItem", new String[] {"name", "path",  "type", "shortReport", "fullReport", "time"});
		for(String[] row : table) {
			ReportItem reportItem = new ReportItem(row[0], row[1], row[2], row[3], row[4], row[5]);
			this.file.addReportItem(reportItem);
		}
	}
	
}
;