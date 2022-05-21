package com.rinit.gui.dev.drivers.requestParams;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.requestParams.dirver.RequestParamsDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class RequestParamsCliDriver extends AbstractCliDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5743667048596982665L;

	private static final String NAME = "request.params";
	
	private RequestParamsCliDriverLogic logic;
	private AbstractCliDriverView view;

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
	public AbstractCliDriverView getView() {
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

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return RequestParamsDriver.class;
	}

}
