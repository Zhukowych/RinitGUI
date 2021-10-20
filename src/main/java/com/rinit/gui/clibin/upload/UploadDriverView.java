package com.rinit.gui.clibin.upload;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.utils.JFilePicker;

public class UploadDriverView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6608068197425790073L;

	private GroupLayout layout; 

    private JTextField driverName = new JTextField();
    private JTextField driverClassPath = new JTextField();
    private JTextField driverCliClassPath = new JTextField();
    private JFilePicker filePicker = new JFilePicker("Pick a file", "Browse...");
	private JButton submitButton = new JButton("Submit");

	
	public UploadDriverView() {
		this.layout = new GroupLayout(this);
	    this.layout.setAutoCreateContainerGaps(true);
		this.setLayout(this.layout);
	    
		this.constructGUI();
		this.bindListeners();
	}
	
	private void constructGUI() {
		
		this.filePicker.setMode(JFilePicker.MODE_SAVE);
		this.submitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
	    this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		.addGroup(this.layout.createSequentialGroup()
	    				.addComponent(this.driverName))
	    		.addGroup(this.layout.createSequentialGroup()
	    				.addComponent(this.driverClassPath))
	    		.addGroup(this.layout.createSequentialGroup()
	    				.addComponent(this.driverCliClassPath))
	    		.addGroup(this.layout.createSequentialGroup()
	    				.addComponent(this.filePicker))
	    		.addGroup(this.layout.createSequentialGroup()
	    				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    				.addComponent(this.submitButton))
	    );
    
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.driverName))
				.addGap(2)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.driverClassPath))
				.addGap(2)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.driverCliClassPath))
				.addGap(2)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.filePicker))
				.addGap(2)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.submitButton))
		);
		
		
	}

	private void bindListeners() {
		this.submitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		
		});
	}
	
	private void submit() {
		try {
			UploadBinLogic.uploadDriver(this.driverName.getText(), this.driverClassPath.getText(), this.driverCliClassPath.getText(), this.filePicker.getSelectedFilePath());
		} catch (LogicException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
}
