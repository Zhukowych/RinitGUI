package com.rinit.gui.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpBarView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3376412077704053070L;
	
	private static final int PREFERED_HEIGHT = 20;
	private Dimension preferedDimension = new Dimension((int)this.getMaximumSize().getWidth(), PREFERED_HEIGHT);
	
	public HelpBarView() {
		this.setMaximumSize(preferedDimension);
		this.setPreferredSize(preferedDimension);
		this.add(new JLabel("13123123"));
		this.setBackground(Color.BLACK);
	}
	
}
