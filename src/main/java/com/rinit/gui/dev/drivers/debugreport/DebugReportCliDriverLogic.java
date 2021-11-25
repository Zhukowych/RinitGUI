package com.rinit.gui.dev.drivers.debugreport;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;
import com.rinit.gui.dev.drivers.debugreport.driver.DebugReportDriver;
import com.rinit.gui.model.BinModel;
import com.rinit.gui.model.ModelFacade;

public class DebugReportCliDriverLogic {
	
	private FileDTO readingFile;
	private BinModel binModel;
	private DebugReportDriver driver = new DebugReportDriver();
	
	public DebugReportCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		this.readingFile = readingFile;
		this.driver.fromDTO(this.readingFile);
		this.binModel = modelFacade.getBinModel();
	}
	
	public DebugReportDriver getInitialData() {
		return this.driver;
	}
	
	public void openFile(ReportItem item) {
		binModel.execute(String.format("read %s %s", item.elementPath,  item.elementName));
	}
	
}
