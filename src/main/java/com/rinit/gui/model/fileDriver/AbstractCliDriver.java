package com.rinit.gui.model.fileDriver;

import java.awt.Dimension;
import java.io.Serializable;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.model.ModelFacade;

public abstract class AbstractCliDriver implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5950807876370155767L;
	
	protected FileDTO readingFile;
	protected ModelFacade modelFacade;
	
	public AbstractCliDriver() {}
	
	public AbstractCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		this.readingFile = readingFile;
		this.modelFacade = modelFacade;
	}

	public abstract String getName();
	public abstract AbstractCliDriverView getView();
	public abstract boolean isPopup();
	public abstract boolean isDirable();
	public abstract Dimension getPopUpSize();
	public abstract Class<? extends AbstractDriver> getDriver();
	
}
