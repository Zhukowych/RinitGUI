package com.rinit.gui.model;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.panels.LeftPanelModel;
import com.rinit.gui.model.panels.PanelsModel;
import com.rinit.gui.model.panels.RightPanelModel;


public class ModelFacade {
	
	private PanelsModel panelsModel;	
	private CommandLineModel commandLineModel;
	
	public ModelFacade(IEventHandler eventHandler) {
		this.panelsModel = new PanelsModel(eventHandler);
		this.commandLineModel = new CommandLineModel(eventHandler);
	}
	
	public PanelsModel getPanelsModel() {
		return this.panelsModel;
	}
	
	public CommandLineModel getCommandLineModel() {
		return this.commandLineModel;
	}
}
