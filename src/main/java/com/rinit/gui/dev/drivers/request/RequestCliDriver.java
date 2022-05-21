package com.rinit.gui.dev.drivers.request;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.request.driver.RequestDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class RequestCliDriver extends AbstractCliDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4641719955338568196L;

	private static final String NAME = "request";

	private RequestCliDriverLogic logic;
	private AbstractCliDriverView view;
	
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
	public AbstractCliDriverView getView() {
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
