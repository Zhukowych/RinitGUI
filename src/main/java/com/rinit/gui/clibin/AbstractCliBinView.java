package com.rinit.gui.clibin;

import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class AbstractCliBinView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7357265608348558139L;

	protected JDialog popUp;
	
	public void setPopUp(JDialog popUp) {
		this.popUp = popUp;
	}
	
}
