package com.rinit.gui.dev.drivers.request;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.request.driver.RequestDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class RequestCliDriver extends AbstractCliFileDriver {

	private static final String NAME = "request";

	private RequestCliDriverLogic logic;
	private AbstractCliFileDriverView view;
	
	public RequestCliDriver() {}
	
	public RequestCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new RequestCliDriverLogic(readingFile, modelFacade);
		this.view = new RequestCliDriverView(this.logic);
	}
	
	@Override
	public String getName() {
		return RequestCliDriver.NAME;
	}

	@Override
	public AbstractCliFileDriverView getView() {
		return this.view;
	}

	@Override
	public boolean isPopup() {
		return true;
	}

	@Override
	public Dimension getPopUpSize() {
		return new Dimension(600, 400);
	}

	@Override
	public boolean isDirable() {
		return true;
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return RequestDriver.class;
	}

}
