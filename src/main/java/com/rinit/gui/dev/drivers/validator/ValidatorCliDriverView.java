package com.rinit.gui.dev.drivers.validator;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;
import com.rinit.gui.view.ui.RTextArea;


public class ValidatorCliDriverView extends AbstractCliFileDriverView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8384155141977181567L;

    private GroupLayout layout;
    
    private CLabel requiedHttpCodeLabel = new CLabel("Required code:");
    private RInput requiedHttpCode = new RInput();
    
    private CLabel requiedContentLabel = new CLabel("Required content:");
    private RTextArea requiredContent = new RTextArea();
    
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
		ValidatorSubmitData initiData = this.logic.getInitialData();
		this.requiedHttpCode.setText(Integer.toString(initiData.requiredHttpCode));
		this.requiredContent.setText(initiData.requiredContent);
	}
	
	
	public void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		this.requiredContent.setRows(10);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.requiedHttpCodeLabel)
						.addComponent(this.requiedHttpCode))
				.addComponent(this.requiedContentLabel)
				.addComponent(this.requiredContent)
				.addComponent(submitButton, GroupLayout.Alignment.TRAILING));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.requiedHttpCodeLabel)
						.addComponent(this.requiedHttpCode))
				.addComponent(this.requiedContentLabel)
				.addComponent(this.requiredContent)
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
		ValidatorSubmitData submitData = new ValidatorSubmitData(Integer.parseInt(this.requiedHttpCode.getText()), this.requiredContent.getText());
		this.logic.submit(submitData);
		this.popUp.dispose();
	}
	
}
