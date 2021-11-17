package com.rinit.gui.dev.drivers.debugreport;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.dev.drivers.debugreport.driver.DebugReportDriver;
import com.rinit.gui.model.ModelFacade;

public class DebugReportCliDriverLogic {
	
	private FileDTO readingFile;
	private ModelFacade modelFacade;
	private DebugReportDriver driver = new DebugReportDriver();
	
	public DebugReportCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		this.readingFile = readingFile;
		this.modelFacade = modelFacade;
		this.driver.fromDTO(this.readingFile);
	}
	
	public DebugReportDriver getInitialData() {
		return this.driver;
	}
	
	
	
	
}
