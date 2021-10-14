package com.rinit.gui.model.viewModel;

import com.rinit.gui.event.IEventContext;

public class CommandViewModel implements IEventContext {
	
	private String command;
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return this.command;
	}
	
}
