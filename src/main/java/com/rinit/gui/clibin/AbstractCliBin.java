package com.rinit.gui.clibin;

import com.rinit.gui.model.ModelFacade;

public abstract class AbstractCliBin {
	
	protected String[] params;
	protected ModelFacade modelFacade;
	
	public AbstractCliBin(String[] params, ModelFacade modelFacade) {
		this.params = params;
		this.modelFacade =  modelFacade;
	}
	
	public abstract AbstractCliBinView getView();
	
}
