package com.rinit.gui.view.ui;

import java.awt.Color;


import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.rinit.gui.view.Colors;

public class BorderedPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3759075436290865957L;

	public BorderedPanel(String title) {
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), title, TitledBorder.CENTER, TitledBorder.TOP));
		this.setBackground(Colors.POPUP_BACKGROUND);
	}	
}
