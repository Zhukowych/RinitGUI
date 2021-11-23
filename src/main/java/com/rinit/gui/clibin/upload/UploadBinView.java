package com.rinit.gui.clibin.upload;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rinit.gui.utils.JFilePicker;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.RInput;

public class UploadBinView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 241182396622052852L;

    private GroupLayout layout;
	
    private JLabel nameLabel = new JLabel("Name");
    private RInput name = new RInput();
    
    private JLabel classPathLabel = new JLabel("Class path");
    private RInput classPath = new RInput();

    private JFilePicker jarFile = new JFilePicker("Jar file path", "Browse");
    
    private JButton submitButton = new JButton("Save");
    
	@SuppressWarnings("unused")
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
		this.setBackground(Colors.POPUP_BACKGROUND);
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
