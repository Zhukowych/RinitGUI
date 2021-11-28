package com.rinit.gui.view.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.rinit.gui.view.Colors;

public class HelpBarElement extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3542404647000389815L;

	private String keyName;
	private String commandName;
	
	private CLabel keyNameLabel;
	
	private JPanel commandNamePanel = new JPanel();
	private CLabel commandNameLabel;
	
	
	public HelpBarElement(String keyName, String commandName) {
		this.keyName = keyName;
		this.commandName = commandName;
		
		
		this.constructGUI();
	}
	
	private void constructGUI() {
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 5));
		this.setBackground(Color.BLACK);
		
		this.setKeyLabel();
		this.setCommandNamePanel();
		this.addComponents();
	}
	
	public void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
		
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1.0;
		this.add(this.keyNameLabel, gbc);
			
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 0, 2, 0);
		this.add(this.commandNamePanel, gbc);
	}
	
	public void setKeyLabel() {
		this.keyNameLabel = new CLabel(this.keyName);
		this.keyNameLabel.setForeground(Color.WHITE);	
	}
	
	public void setCommandNamePanel() {
		this.commandNamePanel.setBackground(Colors.INPUT_BACKGROUND);
		this.commandNamePanel.setLayout(new BoxLayout(this.commandNamePanel, BoxLayout.PAGE_AXIS));
		this.commandNameLabel = new CLabel(this.commandName);
		this.commandNamePanel.add(this.commandNameLabel);
		this.commandNameLabel.setFont(new Font("Consolal", Font.ITALIC, 14));
		this.commandNamePanel.setBorder(BorderFactory.createEmptyBorder(1, 3, 1, 3));
	}
	
}
