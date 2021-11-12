package com.rinit.gui.clibin.upload;

import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class UploadView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 145113227093179408L;
	
	private JTabbedPane tabs = new JTabbedPane();
	
	private UploadBinView binView;
	private UploadDriverView driverView;
	
	private UploadBinLogic binLogic;
	private UploadDriverLogic driverLogic;
	
	
	public UploadView(ModelFacade modelFacade) {
		this.binLogic = new UploadBinLogic(modelFacade);
		this.driverLogic = new UploadDriverLogic(modelFacade);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.constructGUI();
	}
	
	private void constructGUI() {
		this.binView = new UploadBinView(this.binLogic);
		this.driverView = new UploadDriverView(this.driverLogic);
		
		this.tabs.addTab("Driver", this.driverView);
		this.tabs.addTab("Bin", this.binView);
		
		this.add(this.tabs);
	}
	
}
