package com.rinit.gui.clibin.upload;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.utils.JFilePicker;

public class UploadLibView extends AbstractCliBinView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2511117546533498312L;

	private JLabel title = new JLabel("Add library");
	
	private JFilePicker filePicker = new JFilePicker("Pick a file", "Browse...");
	private ClassListView classListView = new ClassListView();
	
	public UploadLibView() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.constructGUI();
	}
	
	private void constructGUI() {
		this.title.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.filePicker.setMode(JFilePicker.MODE_SAVE);
		this.filePicker.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.classListView.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(this.title);
		this.add(this.filePicker);
		this.add(this.classListView);
	}
	
}
