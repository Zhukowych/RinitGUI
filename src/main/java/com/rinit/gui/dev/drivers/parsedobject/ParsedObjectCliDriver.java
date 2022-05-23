package com.rinit.gui.dev.drivers.parsedobject;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.parsedobject.dirver.ParsedObjectDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class ParsedObjectCliDriver extends AbstractCliDriver {
	
	public static final String NAME = "parsedobject";
	
	public ParsedObjectCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);

	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public AbstractCliDriverView getView() {
		// TODO Auto-generated method stub
		return null;
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
		return new Dimension(400, 600);
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return ParsedObjectDriver.class;
	}

}
