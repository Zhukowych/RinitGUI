package com.rinit.gui.dev.bin.debugger;

import java.awt.Dimension;

import javax.swing.KeyStroke;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class DebuggerBin extends AbstractCliBin {
 
	public static final String NAME = "debug";

	private DebuggerBinLogic logic;
	private DebuggerBinView view;
	
	public DebuggerBin() {}
	
	public DebuggerBin(String[] params, ModelFacade modelFacade) {
		this.logic = new DebuggerBinLogic(modelFacade);
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public KeyStroke getKeyBinding() {
		return null;
	}

	@Override
	public AbstractCliBinView getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPopUp() {
		return false;
	}

	@Override
	public Dimension getPopUpSize() {
		return null;
	}

}
