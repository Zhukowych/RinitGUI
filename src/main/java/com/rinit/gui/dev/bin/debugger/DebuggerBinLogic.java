package com.rinit.gui.dev.bin.debugger;

import com.rinit.gui.dev.bin.debugger.bin.Debugger;
import com.rinit.gui.dev.drivers.debugreport.DebugReportCliDriverLogic;
import com.rinit.gui.model.ModelFacade;

public class DebuggerBinLogic {

	private Thread debugThread;
	private ModelFacade modelFacade;
	
	public DebuggerBinLogic(ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	} 
	
	public void runTest(RunData runData) {
		Debugger debugger = new Debugger(modelFacade);
		debugger.setRequestReportCallBack(runData.requestReportCallBack);
		debugger.setRunName(runData.runName);
		this.debugThread = new Thread(debugger);
		this.debugThread.start();
	}
	
	public void stopTest() {
		this.debugThread.interrupt();
	}
	
	public DebugReportCliDriverLogic createDebugReportLogic() {
		return new DebugReportCliDriverLogic(null, modelFacade);
	}
}
