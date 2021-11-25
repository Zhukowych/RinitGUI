package com.rinit.gui.dev.bin.debugger;

import com.rinit.gui.dev.bin.debugger.bin.interfaces.RequestReportCallBack;

public class RunData {

	public String runName;
	public RequestReportCallBack requestReportCallBack;
	
	public RunData(RequestReportCallBack requestReportCallBack, String runName) {
		this.runName = runName;
		this.requestReportCallBack = requestReportCallBack;
	}
	
}

