package com.rinit.gui.dev.drivers.apitestconfig;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.apitestconfig.driver.ApiTestConfiigDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class ApiTestConfigCliDriver extends AbstractCliDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457139368825875806L;

	public static final String NAME = "apitest.config";

	private ApiTestConfigCliDriverLogic logic;
	private ApiTestConfigCliDriverView view;
	
	public ApiTestConfigCliDriver () {}
	
	public ApiTestConfigCliDriver (FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new ApiTestConfigCliDriverLogic(readingFile, modelFacade);
		this.view = new ApiTestConfigCliDriverView(this.logic);
	}
	
	@Override
	public String getName() {
		return ApiTestConfigCliDriver.NAME;
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
		return new Dimension(500, 600);
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return ApiTestConfiigDriver.class;
	}

}
