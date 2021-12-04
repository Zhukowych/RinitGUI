 package com.rinit.gui.clibin;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.KeyStroke;
import com.rinit.gui.model.ModelFacade;

public abstract class AbstractCliBin {
	
	protected String[] params;
	protected ModelFacade modelFacade;
	protected JDialog popUp;
	
	public AbstractCliBin() {}
	
	public AbstractCliBin(String[] params, ModelFacade modelFacade) {
		this.params = params;
		this.modelFacade =  modelFacade;
	}
	
	public abstract String getName();
	public abstract KeyStroke getKeyBinding();
	public abstract AbstractCliBinView getView();
	public abstract boolean isPopUp();
	public abstract Dimension getPopUpSize();
	public void run() {
		
	}
	
	public boolean visible() {
		return true;
	}
	
}
