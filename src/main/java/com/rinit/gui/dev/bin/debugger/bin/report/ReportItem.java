package com.rinit.gui.dev.bin.debugger.bin.report;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.utils.XMLBuilder;

public class ReportItem {
	
	public String elementName;
	public String elementPath;
	public String elementType;
	public String shortReport = "";
	public String fullReport = "";
	public String time;
	
	public static final String PASSED = "PASSED";
	public static final String FAILED = "FAILED";
	
	
	public static ReportItem createDefaultReport(AbstractDriver file) {
		ReportItem reportItem = new ReportItem();
		reportItem.elementName = file.getName();
		reportItem.elementPath = file.getPath();
		reportItem.elementType = file.getExtention();
		reportItem.shortReport = "PASSED";
		reportItem.fullReport = "PASSED";
		reportItem.time = "0 ms";
		return reportItem;
	}
	
	public ReportItem() {}
	
	public ReportItem(String elementName, String elementPath, String elementType, String shortReport, String fullReport, String time) {
		this.elementName = elementName;
		this.elementPath = elementPath;
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
						builder.addTag("path", this.elementPath),
						builder.addTag("type", this.elementType),
						builder.addTag("shortReport", this.shortReport),
						builder.addTag("fullReport", this.fullReport),
						builder.addTag("time", this.time)
					)
				);
	}
	
	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementPath() {
		return elementPath;
	}

	public void setElementPath(String elementPath) {
		this.elementPath = elementPath;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getShortReport() {
		return shortReport;
	}

	public void setShortReport(String shortReport) {
		this.shortReport = shortReport;
	}

	public String getFullReport() {
		return fullReport;
	}

	public void setFullReport(String fullReport) {
		this.fullReport = fullReport;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String[] toRow() {
		return new String[] {this.elementName, this.elementType, this.shortReport, this.time};
	}
	
}
