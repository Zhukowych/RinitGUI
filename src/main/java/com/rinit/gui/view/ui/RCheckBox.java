package com.rinit.gui.view.ui;

import javax.swing.JCheckBox;
import com.rinit.gui.view.Colors;

public class RCheckBox extends JCheckBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5142787206396501241L;

	public RCheckBox(String label) {
		super(label); 
		this.setBackground(Colors.POPUP_BACKGROUND);
	}
	
}
