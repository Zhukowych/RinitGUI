package com.rinit.gui.dev.drivers.abstraction;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.abstraction.driver.AbstractionDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class AbstractionCliDriver extends AbstractCliFileDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3180379181925408263L;
	
	private static final String NAME = "abstraction";
	
	public AbstractionCliDriver() {}
	
	public AbstractionCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public AbstractCliFileDriverView getView() {
		return new AbstractionCliDriverView();
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
		return AbstractionDriver.class;
	}

}
