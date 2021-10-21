package com.rinit.gui.model.fileDriver.cliDrivers;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class DefaultCliDriver extends AbstractCliFileDriver {

	public static final String NAME = "default";

	private DefaultFileDriverLogic logic;
	
	public DefaultCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new DefaultFileDriverLogic();
		this.logic.setReadingFile(readingFile);
		this.logic.setFileServiceClient(this.modelFacade.getRinitClientModel().getClient().getFileService());
	}

	@Override
	public AbstractCliFileDriverView getView() {
		return new DefaultCliDriverView(this.logic);
	}

}
