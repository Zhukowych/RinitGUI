package com.rinit.gui.view.ui;

import java.awt.Font;

import javax.swing.JLabel;

public class CLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4128454731324251950L;
	
	public CLabel(String text) {
		super(text);
		this.setFont(new Font("Consolal", Font.PLAIN, 13));
	}
	
}
