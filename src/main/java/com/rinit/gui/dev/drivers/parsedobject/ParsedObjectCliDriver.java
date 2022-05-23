package com.rinit.gui.dev.drivers.parsedobject;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.parsedobject.dirver.ParsedObjectDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class ParsedObjectCliDriver extends AbstractCliDriver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5373021623088238856L;

	public static final String NAME = "parsedobject";
	
	private ParsedObjectCliDriverView view;
	private ParsedObjectCliDriverLogic logic;
	
	public ParsedObjectCliDriver() {}
	
	public ParsedObjectCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new ParsedObjectCliDriverLogic(readingFile, modelFacade);
		this.view = new ParsedObjectCliDriverView(this.logic);
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
		return new Dimension(800, 600);
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return ParsedObjectDriver.class;
	}

}
