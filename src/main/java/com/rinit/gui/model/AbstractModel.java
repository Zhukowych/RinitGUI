package com.rinit.gui.model;

import com.rinit.gui.event.IEventHandler;

public class AbstractModel {
	
	protected IEventHandler eventHandler;
	
	public AbstractModel(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}
	
}
