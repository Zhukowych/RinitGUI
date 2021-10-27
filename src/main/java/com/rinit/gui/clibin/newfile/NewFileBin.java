package com.rinit.gui.clibin.newfile;

import java.awt.Dimension;

import javax.swing.JDialog;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class NewFileBin extends AbstractCliBin {

	public static final String NAME = "new";

	private NewFileLogic logic;
	
	public NewFileBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.logic = new NewFileLogic(modelFacade);
	}

	@Override
	public AbstractCliBinView getView() {
		return new NewFileBinView(this.logic);
	}
	
	@Override
	public boolean isPopUp() {
		return true;
	}

	@Override
	public Dimension getPopUpSize() {
		return new Dimension(600, 200);
	}

}
