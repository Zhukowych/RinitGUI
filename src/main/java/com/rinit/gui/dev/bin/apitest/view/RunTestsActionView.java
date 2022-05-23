package com.rinit.gui.dev.bin.apitest.view;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.dev.bin.apitest.logic.RunTestsActionLogic;

public class RunTestsActionView extends AbstractCliBinView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3585924631351796818L;
	
	private RunTestsActionLogic logic;
	
	public RunTestsActionView(RunTestsActionLogic logic) {
		this.logic = logic;
	}
	
}
