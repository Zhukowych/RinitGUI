package com.rinit.gui.dev.drivers.apitesttestsdir;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class ApiTestTestsDirectoryCliDriver extends AbstractCliDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9102939600550654137L;
	
	public static final String NAME = "apitest.tests";
	
	public ApiTestTestsDirectoryCliDriver() {}
	
	public ApiTestTestsDirectoryCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public AbstractCliDriverView getView() {
		return null;
	}

	@Override
	public boolean isPopup() {
		return false;
	}

	@Override
	public boolean isDirable() {
		return true;
	}

	@Override
	public Dimension getPopUpSize() {
		return null;
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return null;
	}

}
