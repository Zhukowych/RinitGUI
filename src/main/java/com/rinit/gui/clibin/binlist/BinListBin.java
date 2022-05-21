package com.rinit.gui.clibin.binlist;

import java.awt.Dimension;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class BinListBin extends AbstractCliBin {

	public static final String NAME = "binlist";

	private AbstractCliBinView view;
	private BinListLogic logic;
	
	public BinListBin() {}
	
	public BinListBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.logic = new BinListLogic(modelFacade);
		this.view = new BinListView(logic);
	}

	@Override
	public String getName() {
		return BinListBin.NAME;
	}

	@Override
	public KeyStroke getKeyBinding() {
		return KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0);
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
