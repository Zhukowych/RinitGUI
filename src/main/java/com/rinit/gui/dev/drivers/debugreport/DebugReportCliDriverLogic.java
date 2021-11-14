package com.rinit.gui.dev.drivers.debugreport;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.model.ModelFacade;

public class DebugReportCliDriverLogic {
	
	private FileDTO readingFile;
	private ModelFacade modelFacade;
	
	public DebugReportCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		this.readingFile = readingFile;
		this.modelFacade = modelFacade;
	}
}
