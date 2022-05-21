package com.rinit.gui.model.fileDriver;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.model.ModelFacade;

public class AbstractCliDriverLogic {
	
	protected FileDTO readingFile;
	protected ModelFacade modelFacade;
	 
	public AbstractCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		this.readingFile = readingFile;
		this.modelFacade = modelFacade;
	}
	
}
