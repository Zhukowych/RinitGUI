package com.rinit.gui.model.fileDriver.cliDrivers;

import java.awt.Component;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class DefaultCliDriverView extends AbstractCliFileDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7680523958363719248L;

	private JTextArea textArea = new JTextArea();
	private JButton saveButton = new JButton("Save");
	
	private DefaultFileDriverLogic logic;
	
	public DefaultCliDriverView(DefaultFileDriverLogic logic) {
		this.logic = logic;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.constructGUI();
		this.bindForListeners();
	}
	
	private void constructGUI() {
		this.textArea.setText(logic.getContent());
		this.textArea.setFont(new Font("Serif", Font.PLAIN, 16));
		this.saveButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.textArea.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.add(new JScrollPane(this.textArea));
		this.add(this.saveButton);
	}
	
	private void bindForListeners() {
		this.saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
	}
	
	private void save() {
		try {
			this.logic.save(this.textArea.getText());
		} catch (LogicException e) {
			e.printStackTrace();
		}
	}
	
}
