package com.rinit.gui.clibin.upload;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import com.rinit.gui.utils.JFilePicker;

public class UploadBinView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 241182396622052852L;

    private GroupLayout layout;
	
    private JLabel nameLabel = new JLabel("Name");
    private JTextField name = new JTextField();
    
    private JLabel classPathLabel = new JLabel("Class path");
    private JTextField classPath = new JTextField();

    private JFilePicker jarFile = new JFilePicker("Jar file path", "Browse");
    
    private JButton submitButton = new JButton("Save");
    
	private UploadBinLogic logic;
	
	public UploadBinView(UploadBinLogic logic) {
		this.logic = logic;
		
		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setLayout(this.layout);

		this.constructGUI();
		
	}

	public void constructGUI() {
		this.jarFile.setMode(JFilePicker.MODE_SAVE);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.nameLabel)
						.addComponent(this.name))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.classPathLabel)
						.addComponent(this.classPath))
				.addComponent(jarFile)
				.addComponent(this.submitButton));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.nameLabel)
						.addComponent(this.name))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.classPathLabel)
						.addComponent(this.classPath))
				.addComponent(this.jarFile)
				.addComponent(this.submitButton));
		
	}
	
}
