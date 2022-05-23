package com.rinit.gui.dev.bin.apitest.logic;

import com.rinit.gui.AbstractCliBinLogic;
import com.rinit.gui.model.ModelFacade;

public class RunTestsActionLogic extends AbstractCliBinLogic {

	public RunTestsActionLogic(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.modelFacade.getBinModel().execute("debug");
	}
	
}
