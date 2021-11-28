package com.rinit.gui.clibin.mkdir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.RInput;

public class MkDirBinView extends AbstractCliBinView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7302116095073403054L;

    private GroupLayout layout;
    
    private JLabel dirNameLabel = new JLabel("Dir name:");
    private RInput dirName = new RInput();
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
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.dirNameLabel)
						.addComponent(this.dirName))
				.addGroup(GroupLayout.Alignment.CENTER, 
						this.layout.createSequentialGroup()
							.addComponent(this.submitButton))
				);

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
