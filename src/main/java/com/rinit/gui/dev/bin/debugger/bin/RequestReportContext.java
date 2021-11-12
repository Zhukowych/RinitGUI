package com.rinit.gui.dev.bin.debugger.bin;

public class RequestReportContext {
	
	private RequestReportCallBack requestReportCallBack;
	
	public RequestReportContext(RequestReportCallBack requestReportCallBack) {
		this.requestReportCallBack = requestReportCallBack;
	}
	
	public void addReport() {
		requestReportCallBack.addReport(new ShortReport(null, null, null, null));
	}
	
}
