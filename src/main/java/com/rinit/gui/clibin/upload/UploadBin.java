package com.rinit.gui.clibin.upload;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class UploadBin extends AbstractCliBin {

	public static final String NAME = "upload";
	
	private AbstractCliBinView view;

	public UploadBin() {}
	
	public UploadBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.view = new UploadView(modelFacade);
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public KeyStroke getKeyBinding() {
		return KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
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
