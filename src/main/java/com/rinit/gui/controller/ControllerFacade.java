package com.rinit.gui.controller;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.ModelFacade;

public class ControllerFacade {
	
	@SuppressWarnings("unused")
	private ModelFacade modelFacade;
	
	@SuppressWarnings("unused")
	private PanelsController panelsController;
	
	@SuppressWarnings("unused")
	private CommandLineController commandLineController;
	
	@SuppressWarnings("unused")
	private TabsController tabsController;
	
	
	public ControllerFacade(IEventHandler eventHandler, ModelFacade modelFacade) {
		this.panelsController = new PanelsController(eventHandler,  modelFacade);
		this.commandLineController = new CommandLineController(eventHandler, modelFacade);
		this.tabsController = new TabsController(eventHandler, modelFacade);
	}
	
}
