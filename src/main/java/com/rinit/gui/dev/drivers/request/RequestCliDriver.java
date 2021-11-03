package com.rinit.gui.dev.drivers.request;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class RequestCliDriver extends AbstractCliFileDriver {

	private static final String NAME = "request";

	public RequestCliDriver() {}
	
	public RequestCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
	}
	
	@Override
	public String getName() {
		return RequestCliDriver.NAME;
	}

	@Override
	public AbstractCliFileDriverView getView() {
		return new RequestCliDriverView();
	}

	@Override
	public boolean isPopup() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Dimension getPopUpSize() {
		return new Dimension(600, 400);
	}

}
