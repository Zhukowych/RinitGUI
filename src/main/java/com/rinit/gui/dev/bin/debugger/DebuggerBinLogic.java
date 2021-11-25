package com.rinit.gui.dev.bin.debugger;

import com.rinit.gui.dev.bin.debugger.bin.Debugger;
import com.rinit.gui.model.ModelFacade;

public class DebuggerBinLogic {

	private ModelFacade modelFacade;
	
	public DebuggerBinLogic(ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	} 
	
	public void runTest(RunData runData) {
		Debugger debugger = new Debugger(modelFacade);
		debugger.setRequestReportCallBack(runData.requestReportCallBack);
		debugger.setRunName(runData.runName);
		Thread debugThread = new Thread(debugger);
		debugThread.start();
	}
	
}
