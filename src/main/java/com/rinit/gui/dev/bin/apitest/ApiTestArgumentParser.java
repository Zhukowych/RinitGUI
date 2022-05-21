package com.rinit.gui.dev.bin.apitest;

import java.awt.Dimension;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class ApiTestArgumentParser {
	
	private String[] params;
	private ModelFacade modelFacade;
	
	private ApiTestAction action;
	
	public ApiTestArgumentParser(String[] params, ModelFacade modelFacade) {
		this.params = params;
		this.modelFacade = modelFacade;
		
		this.parseParams();
	}
	
	public AbstractCliBinView createViewForSucnParams() {
		return this.action.createViewInstance(this.params, this.modelFacade);
	}
	
	public boolean isPopupFouSuchParams() {
		return this.action.isPopup();
	}
	
	public Dimension getPopupSizeForSuchParams() {
		return this.action.getPopupSize();
	}
	
	public boolean isVisibeForSuchParams() {
		return this.action.isVisible();	
	}
	
	private void parseParams() {
		this.action = ApiTestAction.getActionByName(this.params[0]);
	}
	
}
