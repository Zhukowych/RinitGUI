package com.rinit.gui.dev.bin.debugger.bin.report;

import com.rinit.gui.utils.XMLBuilder;

public class ReportItem {
	
	public String elementName;
	public String elementType;
	public String shortReport;
	public String fullReport;
	public String time;
	
	public ReportItem() {}
	
	public ReportItem(String elementName, String elementType, String shortReport, String fullReport, String time) {
		this.elementName = elementName;
		this.elementType = elementType;
		this.shortReport = shortReport;
		this.fullReport = fullReport;
		this.time = time;
	}
	
	public String toString() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("reportItem", 
				builder.addGroup(
						builder.addTag("name", this.elementName),
						builder.addTag("type", this.elementType),
						builder.addTag("shortReport", this.shortReport),
						builder.addTag("fullReport", this.fullReport),
						builder.addTag("time", this.time)
					)
				);
	}
		
}
