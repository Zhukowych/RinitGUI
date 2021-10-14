package com.rinit.gui.controller;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.ModelFacade;

public class ControllerFacade {
	
	private ModelFacade modelFacade;
	private PanelsController panelsController;
	private CommandLineController commandLineController;
	
	public ControllerFacade(IEventHandler eventHandler, ModelFacade modelFacade) {
		this.panelsController = new PanelsController(eventHandler,  modelFacade);
		this.commandLineController = new CommandLineController(eventHandler, modelFacade);
	}
	
}
