  package com.rinit.gui.view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.rinit.gui.view.ui.HelpBarElement;

public class HelpBarView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3376412077704053070L;
	
	private static final int PREFERED_HEIGHT = 25;
	private Dimension preferedDimension = new Dimension((int)this.getMaximumSize().getWidth(), PREFERED_HEIGHT);
	
	public HelpBarView() {
		this.setMaximumSize(preferedDimension);
		this.setPreferredSize(preferedDimension);
		
		this.setLayout(new GridLayout(1, 12));
		this.add(new HelpBarElement("F1", "Upload"));
		this.add(new HelpBarElement("F2", "New"));
		this.add(new HelpBarElement("F3", "Edit"));
		this.add(new HelpBarElement("F4", "Edit Content"));
		this.add(new HelpBarElement("F5", "Copy"));
		this.add(new HelpBarElement("F6", "RenMove"));
		this.add(new HelpBarElement("F7", "Mkdir"));
		this.add(new HelpBarElement("F8", "Delete"));
		this.add(new HelpBarElement("F9", ""));
		this.add(new HelpBarElement("F10", ""));
		this.add(new HelpBarElement("F11", ""));
		this.add(new HelpBarElement("F12", ""));

		
		this.setBackground(Color.BLACK);

	}
	
}
