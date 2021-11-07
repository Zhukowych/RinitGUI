package com.rinit.gui.model.fileDriver;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class AbstractCliFileDriverView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5452010726520691846L;

	protected JDialog popUp;
	
	public void setPopUp(JDialog popUp) {
		this.popUp = popUp;
	}
	
}
