package com.rinit.gui.model.fileDriver;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.model.ModelFacade;

public abstract class AbstractCliFileDriver {
	
	protected FileDTO readingFile;
	protected ModelFacade modelFacade;
	
	public AbstractCliFileDriver(FileDTO readingFile, ModelFacade modelFacade) {
		this.readingFile = readingFile;
		this.modelFacade = modelFacade;
	}

	public abstract AbstractCliFileDriverView getView();
	
}
