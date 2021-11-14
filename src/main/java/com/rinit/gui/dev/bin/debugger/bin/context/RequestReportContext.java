package com.rinit.gui.dev.bin.debugger.bin.context;

import com.rinit.gui.dev.bin.debugger.bin.RequestReportCallBack;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;
import com.rinit.gui.dev.drivers.debugreport.driver.DebugReportDriver;

public class RequestReportContext {
	
	private DebugReportDriver report;
	private RequestReportCallBack requestReportCallBack;
	
	public RequestReportContext(RequestReportCallBack requestReportCallBack) {
		this.requestReportCallBack = requestReportCallBack;
		this.report = new DebugReportDriver();
	}
	
	public DebugReportDriver getReportItem() {
		return this.report;
	}
	
	public void addReport(ReportItem item) {
		this.requestReportCallBack.addReport(item);
		this.report.addReportItem(item);
	}

}
