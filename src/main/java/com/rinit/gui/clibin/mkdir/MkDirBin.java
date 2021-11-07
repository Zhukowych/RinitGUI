package com.rinit.gui.clibin.mkdir;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class MkDirBin extends AbstractCliBin {

	public static final String NAME = "mkdir";
	
	private MkDirBinLogic logic;
	private AbstractCliBinView view;
	
	public MkDirBin() {}
	
	public MkDirBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.logic = new MkDirBinLogic(this.modelFacade);
		this.view = new MkDirBinView(this.logic);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public KeyStroke getKeyBinding() {
		return KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);
	}

	@Override
	public AbstractCliBinView getView() {
		return this.view;
	}

	@Override
	public boolean isPopUp() {
		return true;
	}

	@Override
	public Dimension getPopUpSize() {
		return new Dimension(600, 400);
	}

}
