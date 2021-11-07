package com.rinit.gui.dev.drivers.validator;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class ValidatorCliDriver extends AbstractCliFileDriver {

	private static final String NAME = "request.validator";
	
	private ValidatorCliDriverLogic logic;
	private AbstractCliFileDriverView view;
	
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
	public AbstractCliFileDriverView getView() {
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

}
