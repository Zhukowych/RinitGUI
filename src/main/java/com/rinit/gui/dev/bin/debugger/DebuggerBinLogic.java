package com.rinit.gui.dev.bin.debugger;

import com.rinit.gui.dev.bin.debugger.bin.Debugger;
import com.rinit.gui.dev.bin.debugger.bin.RequestReportCallBack;
import com.rinit.gui.model.ModelFacade;

public class DebuggerBinLogic {

	private ModelFacade modelFacade;
	
	public DebuggerBinLogic(ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	} 
	
	public void runTest(RequestReportCallBack requestReportCallBack) {
		Debugger debugger = new Debugger(modelFacade);
		debugger.setRequestReportCallBack(requestReportCallBack);
		debugger.run();
	}
	
}
