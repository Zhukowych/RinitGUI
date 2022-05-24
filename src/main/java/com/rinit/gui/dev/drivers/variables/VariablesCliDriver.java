package com.rinit.gui.dev.drivers.variables;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.variables.driver.VariablesDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class VariablesCliDriver extends AbstractCliDriver {

	public static final String NAME = VariablesDriver.EXTENTION;
	
	private VariablesCliDriverView view;
	private VariablesCliDriverLogic logic;
	
	public VariablesCliDriver() {}
	
	public VariablesCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		this.logic = new VariablesCliDriverLogic(readingFile, modelFacade);
		this.view = new VariablesCliDriverView(logic);
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
		return VariablesDriver.class;
	}

}
