package com.rinit.gui.dev.drivers.validator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class ValidatorCliDriverView extends AbstractCliFileDriverView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8384155141977181567L;

    private GroupLayout layout;
    
    private JLabel requiedHttpCodeLabel = new JLabel("Required code:");
    private JTextField requiedHttpCode = new JTextField();
    private JButton submitButton = new JButton("Save");
    
    private ValidatorCliDriverLogic logic;
	
	public ValidatorCliDriverView(ValidatorCliDriverLogic logic) {
		this.logic = logic;
		
		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setLayout(this.layout);
		
		this.constructGUI();
		this.bindListeners();
		this.setInitialData();
		
	}
	
	public void setInitialData() {
		
	}
	
	
	public void constructGUI() {
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.requiedHttpCodeLabel)
						.addComponent(this.requiedHttpCode))
				.addComponent(this.submitButton));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.requiedHttpCodeLabel)
						.addComponent(this.requiedHttpCode))
				.addComponent(this.submitButton));
	} 
	
	public void bindListeners() {
		this.submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}
			
		});
	}
	
	private void submit() {
		ValidatorSubmitData submitData = new ValidatorSubmitData(Integer.parseInt(this.requiedHttpCode.getText()));
		this.logic.submit(submitData);
		this.popUp.dispose();
	}
	
}
