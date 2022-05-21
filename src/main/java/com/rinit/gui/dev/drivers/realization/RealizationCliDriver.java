package com.rinit.gui.dev.drivers.realization;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.realization.driver.RealizationDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class RealizationCliDriver extends AbstractCliDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1379303548964647165L;
	
	public static final String NAME = "realization";
	
	private RealizationCliDriverView view;
	private RealizationCliDriverLogic logic;
	
	public RealizationCliDriver() {}
	
	public RealizationCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new RealizationCliDriverLogic(readingFile, modelFacade);
		this.view = new RealizationCliDriverView(this.logic);
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
		return RealizationDriver.class;
	}

}
