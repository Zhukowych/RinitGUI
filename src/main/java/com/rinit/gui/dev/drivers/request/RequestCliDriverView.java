package com.rinit.gui.dev.drivers.request;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class RequestCliDriverView extends AbstractCliFileDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5641411517255492143L;

    private GroupLayout layout;
    
    private JLabel adressLabel = new JLabel("Adress");
    private JTextField adress = new JTextField();
    
    private JLabel pathLabel = new JLabel("Path");
    private JTextField path = new JTextField();

    
	public RequestCliDriverView() {
		this.layout = new GroupLayout(this);
		this.setLayout(this.layout);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);

		this.costructGUI();
	}
	
	private void costructGUI() {
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						this.layout.createSequentialGroup()
						.addComponent(this.adressLabel)
						.addComponent(this.adress)
						)
				.addGroup(
						this.layout.createSequentialGroup()
						.addComponent(this.pathLabel)
						.addComponent(this.path)
						)				
				);
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.adressLabel)
						.addComponent(this.adress))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.pathLabel)
						.addComponent(this.path))
				);
	}
}
