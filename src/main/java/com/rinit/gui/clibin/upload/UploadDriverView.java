package com.rinit.gui.clibin.upload;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import com.rinit.gui.utils.JFilePicker;

public class UploadDriverView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5462867873823932519L;

    private GroupLayout layout;
	
    private JLabel extentionLabel = new JLabel("Extention");
    private JTextField extention = new JTextField();
    
    private JLabel classPathLabel = new JLabel("Class path");
    private JTextField classPath = new JTextField();

    private JFilePicker jarFile = new JFilePicker("Jar file path", "Browse");
    
    private JButton submitButton = new JButton("Save");
    
	private UploadDriverLogic logic;
	
	public UploadDriverView(UploadDriverLogic logic) {
		this.logic = logic;

		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setLayout(this.layout);

		this.constructGUI();
		this.bindListeners();
		
	}
	
	public void constructGUI() {
		this.jarFile.setMode(JFilePicker.MODE_SAVE);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.extentionLabel)
						.addComponent(this.extention))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.classPathLabel)
						.addComponent(this.classPath))
				.addComponent(jarFile)
				.addComponent(this.submitButton));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.extentionLabel)
						.addComponent(this.extention))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.classPathLabel)
						.addComponent(this.classPath))
				.addComponent(this.jarFile)
				.addComponent(this.submitButton));
	}

	private void bindListeners() {
		this.submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		
		});
	}
	
	private void submit() {
		UploadDriverSubmitData submitData = new UploadDriverSubmitData(this.extention.getText(), this.classPath.getText(), this.jarFile.getSelectedFilePath());
		this.logic.uploadDriver(submitData);
	}
	
}
