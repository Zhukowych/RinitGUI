package com.rinit.gui.dev.bin.apitest.view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.dev.bin.apitest.logic.ChangesActionLogic;
import com.rinit.gui.utils.TableView;

public class ChangesActionView extends AbstractCliBinView {

    private GroupLayout layout;
	
    private TableView changesTable = new TableView(new String[] {"Object name", "Message", "Change type"});
    
	private ChangesActionLogic logic;
	
	public ChangesActionView(ChangesActionLogic logic) {
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
				.addComponent(this.changesTable));

		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addComponent(this.changesTable));

		
	}
	
	private void bindListeners() {
		
	}
	
}
