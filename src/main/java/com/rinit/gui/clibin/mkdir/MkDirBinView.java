package com.rinit.gui.clibin.mkdir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import com.rinit.gui.clibin.AbstractCliBinView;

public class MkDirBinView extends AbstractCliBinView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7302116095073403054L;

    private GroupLayout layout;
    
    private JLabel dirNameLabel = new JLabel("Dir name:");
    private JTextField dirName = new JTextField();
    private JButton submitButton = new JButton("Save");
    
	private MkDirBinLogic logic;
	
	public MkDirBinView(MkDirBinLogic logic) {
		this.logic = logic;
		
		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setLayout(this.layout);
	
		this.constructGUI();
		this.bindListeners();
		
	}
	
	private void constructGUI() {
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.dirNameLabel)
						.addComponent(this.dirName))
				.addComponent(this.submitButton));

		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.dirNameLabel)
						.addComponent(this.dirName))
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
		this.logic.submit(this.dirName.getText());
		this.popUp.dispose();
	}
	
}
