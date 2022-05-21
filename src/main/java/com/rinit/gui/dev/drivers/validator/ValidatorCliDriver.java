package com.rinit.gui.dev.drivers.validator;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.validator.driver.ValidatorDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class ValidatorCliDriver extends AbstractCliDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5750860909195605890L;

	private static final String NAME = "request.validator";
	
	private ValidatorCliDriverLogic logic;
	private AbstractCliDriverView view;
	
	public ValidatorCliDriver() {}
	
	public ValidatorCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new ValidatorCliDriverLogic(readingFile, modelFacade);
		this.view = new ValidatorCliDriverView(logic);
	}
	
	@Override
	public String getName() {
		return NAME;
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
		return false;
	}

	@Override
	public Dimension getPopUpSize() {
		return new Dimension(600, 400);
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return ValidatorDriver.class;
	}

}
