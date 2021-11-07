package com.rinit.gui.dev.drivers.requestParams;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class RequestParamsCliDriver extends AbstractCliFileDriver {

	private static final String NAME = "request.params";
	
	private RequestParamsCliDriverLogic logic;
	private AbstractCliFileDriverView view;

	public RequestParamsCliDriver() {}
	
	public RequestParamsCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new RequestParamsCliDriverLogic(this.readingFile, this.modelFacade);
		this.view = new RequestParamsCliDriverView(this.logic);
	}
	
	@Override
	public String getName() {
		return RequestParamsCliDriver.NAME;
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
	public boolean isDirable() {
		return true;
	}

	@Override
	public Dimension getPopUpSize() {
		return new Dimension(600, 400);
	}

}
