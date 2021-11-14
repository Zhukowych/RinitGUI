package com.rinit.gui.model.viewModel;

import com.rinit.gui.event.IEventContext;

public class CurrentPathViewMode implements IEventContext {
	
	public String currentPath;

	public CurrentPathViewMode(String currentPath) {
		this.currentPath = currentPath;
	}
	
}
