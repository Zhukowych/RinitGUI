package com.rinit.gui.dev.drivers.debugreport.driver;

import java.util.ArrayList;
import java.util.List;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;

public class DebugReportDriver extends AbstractDriver {

	private static final String NAME = "debug.report";
	private List<ReportItem> reportItems = new ArrayList<ReportItem>();
	
	@Override
	protected void buildFromDTO() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String buildContent() {
		this.setExtention(NAME);
		DebugReportExporter exporter = new DebugReportExporter(this);
		return exporter.export();
	}

	public void addReportItem(ReportItem item) {
		this.reportItems.add(item);
	}
	
	public List<ReportItem> getReportItem(){
		return this.reportItems;
	}
	
}
