package com.rinit.gui.dev.drivers.request;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.html.FormSubmitEvent;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class RequestCliDriverView extends AbstractCliFileDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5641411517255492143L;

    private GroupLayout layout;

    private JLabel protocolLabel = new JLabel("Protocol");
    private JTextField protocol = new JTextField();
    
    private JLabel adressLabel = new JLabel("Adress");
    private JTextField adress = new JTextField();
    
    private JLabel pathLabel = new JLabel("Path");
    private JTextField path = new JTextField();

    private JButton submitButton = new JButton("Save");
    
    // 
    
    private RequestCliDriverLogic logic;
    
	public RequestCliDriverView(RequestCliDriverLogic logic) {
		this.logic = logic;
		
		this.layout = new GroupLayout(this);
		this.setLayout(this.layout);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setInitialData();
		this.costructGUI();
		this.bindListeners();
	}
	
	private void setInitialData() {
		RequestSubmitData initialData = this.logic.getInitialData();
		this.protocol.setText(initialData.protocol);
		this.adress.setText(initialData.adress);
		this.path.setText(initialData.path);
	}
	
	private void costructGUI() {
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
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.submitButton))
				);
		
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
						.addComponent(this.submitButton))
				);
		
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
