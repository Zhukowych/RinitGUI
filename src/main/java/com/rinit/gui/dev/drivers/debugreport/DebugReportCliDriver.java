package com.rinit.gui.dev.drivers.debugreport;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.debugreport.driver.DebugReportDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class DebugReportCliDriver extends AbstractCliFileDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7422912982441614624L;

	private static final String NAME = "debug.report";
	
	private DebugReportCliDriverLogic logic;
	private DebugReportCliDriverView view;
	
	public DebugReportCliDriver() {}
	
	public DebugReportCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new DebugReportCliDriverLogic(readingFile, modelFacade);
		this.view = new DebugReportCliDriverView(logic);
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
		return false;
	}

	@Override
	public boolean isDirable() {
		return false;
	}

	@Override
	public Dimension getPopUpSize() {
		return null;
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return DebugReportDriver.class;
	}

}
