package com.rinit.gui.dev.drivers.debugreport;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class DebugReportCliDriverView extends AbstractCliFileDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8018074753308538186L;

	private DebugReportCliDriverLogic logic;
	
	public DebugReportCliDriverView(DebugReportCliDriverLogic logic) {
		this.logic = logic;
	}
	
	
	
}
