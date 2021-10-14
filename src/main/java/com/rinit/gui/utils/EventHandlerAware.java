package com.rinit.gui.utils;

import com.rinit.gui.event.IEventHandler;

public abstract class EventHandlerAware {

	protected IEventHandler eventHandler;
	
	public EventHandlerAware(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

}
