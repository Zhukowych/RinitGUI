package com.rinit.gui;

import com.rinit.gui.model.ModelFacade;

public class AbstractCliBinLogic {
	
	protected String[] params;
	protected ModelFacade modelFacade;
	
	public AbstractCliBinLogic(String[] params, ModelFacade modelFacade) {
		this.params = params;
		this.modelFacade = modelFacade;
	}
	
}
