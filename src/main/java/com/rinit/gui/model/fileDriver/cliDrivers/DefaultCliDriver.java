package com.rinit.gui.model.fileDriver.cliDrivers;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;

public class DefaultCliDriver extends AbstractCliFileDriver {

	public static final String NAME = "default";

	public DefaultCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		System.out.println(this.readingFile.getContent());
	}

}
