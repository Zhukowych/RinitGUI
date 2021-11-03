package com.rinit.gui.dev.bin.debuggerBin;

import java.awt.Dimension;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class DebuggerBin extends AbstractCliBin {

	public DebuggerBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
	}

	@Override
	public AbstractCliBinView getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPopUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dimension getPopUpSize() {
		// TODO Auto-generated method stub
		return null;
	}

}
