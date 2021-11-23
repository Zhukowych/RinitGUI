package com.rinit.gui.controller;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.ModelFacade;

public abstract class AbstractController {

	protected IEventHandler eventHandler;
	
	public AbstractController(IEventHandler eventHandler, ModelFacade modelFacade) {
		this.eventHandler = eventHandler;
		this.subscribeForEvents();
	}
	
	protected abstract void subscribeForEvents(); 
	
}
