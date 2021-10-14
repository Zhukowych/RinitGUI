package com.rinit.gui.model;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.bin.BinModel;
import com.rinit.gui.model.panels.PanelsModel;


public class ModelFacade {
	
	private PanelsModel panelsModel;	
	private CommandLineModel commandLineModel;
	private BinModel binModel;
	
	public ModelFacade(IEventHandler eventHandler) {
		this.panelsModel = new PanelsModel(eventHandler);
		this.commandLineModel = new CommandLineModel(eventHandler);
		this.binModel = new BinModel(eventHandler);
	}
	
	public PanelsModel getPanelsModel() {
		return this.panelsModel;
	}
	
	public CommandLineModel getCommandLineModel() {
		return this.commandLineModel;
	}

	public BinModel getBinModel() {
		return this.binModel;
	}
	
}
