package com.rinit.gui.dev.bin.apitest;

import java.awt.Dimension;

import javax.swing.KeyStroke;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class ApiTestBin extends AbstractCliBin {

	public static final String NAME = "apitest";
	
	private AbstractCliBinView view;
	private ApiTestArgumentParser argumentParser;
	
	public ApiTestBin() {}
	
	public ApiTestBin(String[] params, ModelFacade modelFacade) {
		this.argumentParser = new ApiTestArgumentParser(params, modelFacade);
		this.view = this.argumentParser.createViewForSucnParams();
	}
	
	@Override
	public String getName() {
		return ApiTestBin.NAME;
	}

	@Override
	public KeyStroke getKeyBinding() {
		return null;
	}

	@Override
	public AbstractCliBinView getView() {
		return this.view;
	}

	@Override
	public boolean isPopUp() {
		return this.argumentParser.isPopupFouSuchParams();
	}

	@Override
	public Dimension getPopUpSize() {
		return this.argumentParser.getPopupSizeForSuchParams();
	}
	
	@Override
	public boolean visible() {
		return this.argumentParser.isVisibeForSuchParams();
	}

}
