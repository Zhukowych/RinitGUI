package com.rinit.gui.view;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpBarView extends JPanel {
	
	private static final int PREFERED_HEIGHT = 20;
	private Dimension preferedDimension = new Dimension((int)this.getMaximumSize().getWidth(), PREFERED_HEIGHT);
	
	public HelpBarView() {
		this.setMaximumSize(preferedDimension);
		this.setPreferredSize(preferedDimension);
		this.add(new JLabel("13123123"));
	}
	
}
