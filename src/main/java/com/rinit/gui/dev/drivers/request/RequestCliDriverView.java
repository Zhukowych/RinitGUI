package com.rinit.gui.dev.drivers.request;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;

import com.rinit.gui.model.fileDriver.AbstractCliDriverView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;


public class RequestCliDriverView extends AbstractCliDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5641411517255492143L;

    private GroupLayout layout;

    private CLabel protocolLabel = new CLabel("Protocol");
    private RInput protocol = new RInput();
    
    private CLabel adressLabel = new CLabel("Adress");
    private RInput adress = new RInput();
    
    private CLabel pathLabel = new CLabel("Path");
    private RInput path = new RInput();

    private JButton submitButton = new JButton("Save");
    
    private RequestCliDriverLogic logic;
    
	public RequestCliDriverView(RequestCliDriverLogic logic) {
		this.logic = logic;
		
		this.layout = new GroupLayout(this);
		this.setLayout(this.layout);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setInitialData();
		this.constructGUI();
		this.bindListeners();
	}
	
	private void setInitialData() {
		RequestSubmitData initialData = this.logic.getInitialData();
		this.protocol.setText(initialData.protocol);
		this.adress.setText(initialData.adress);
		this.path.setText(initialData.path);
	}
	
	private void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.protocolLabel)
						.addComponent(this.protocol))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.adressLabel)
						.addComponent(this.adress))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.pathLabel)
						.addComponent(this.path))				
				.addGroup(GroupLayout.Alignment.CENTER, this.layout.createSequentialGroup()
						.addComponent(this.submitButton)));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.protocolLabel)
						.addComponent(this.protocol))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.adressLabel)
						.addComponent(this.adress))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.pathLabel)
						.addComponent(this.path))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.submitButton)));
		
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
		RequestSubmitData submitData = new RequestSubmitData(this.protocol.getText(), this.adress.getText(), this.path.getText());
		this.logic.submit(submitData);
		this.popUp.dispose();
	}
	
}
