package com.rinit.gui.dev.drivers.codevalidator;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.codevalidator.dirver.CodeValidatorDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class CodeValidatorCliDriver extends AbstractCliDriver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6872737647577791040L;

	public static final String NAME = "codevalidator";
	
	private CodeValidatorCliDriverView view;
	private CodeValidatorCliDriverLogic logic;
	
	public CodeValidatorCliDriver() {}
	
	public CodeValidatorCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		
		this.logic = new CodeValidatorCliDriverLogic(readingFile, modelFacade);
		this.view = new CodeValidatorCliDriverView(this.logic);
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
		return new Dimension(400, 600);
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return CodeValidatorDriver.class;
	}

}
