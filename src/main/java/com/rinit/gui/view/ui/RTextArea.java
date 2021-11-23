package com.rinit.gui.view.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

import com.rinit.gui.view.Colors;

public class RTextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7744369744676457708L;

	public RTextArea() {
		super();
		this.setBackground(Colors.INPUT_BACKGROUND);
		Font font = new Font("Consolal", Font.BOLD, 12);
		this.setForeground(Colors.POPUP_BACKGROUND);
		this.setCaretColor(Color.YELLOW);
		this.setFont(font);
	}
	
}
