package com.rinit.gui.dev.bin.apitest.view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.dev.bin.apitest.logic.InitActionLogic;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;

public class InitActionView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403096774819390381L;

    private GroupLayout layout;
    
    private CLabel messageLabel = new CLabel("");
	
	private InitActionLogic logic;
	
	public InitActionView(InitActionLogic logic) {
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
		this.messageLabel.setText(this.logic.getMessaage());
				
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
										.addGroup(GroupLayout.Alignment.CENTER, this.layout.createSequentialGroup()
												.addComponent(this.messageLabel)));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
									.addGroup(this.layout.createSequentialGroup()
											.addComponent(this.messageLabel)));
		
	}
	
	private void bindListeners() {
		
	}

}
