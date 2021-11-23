package com.rinit.gui.view.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import com.rinit.gui.view.Colors;

public class RInput extends JTextField {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7326503389190726837L;

	public RInput() {
		this.setBackground(Colors.INPUT_BACKGROUND);
		Font font = new Font("Consolal", Font.BOLD, 12);
		this.setForeground(Colors.POPUP_BACKGROUND);
		this.setCaretColor(Color.YELLOW);
		this.setFont(font);
	}
	
}
