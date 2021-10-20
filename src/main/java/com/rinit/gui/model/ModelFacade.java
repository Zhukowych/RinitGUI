package com.rinit.gui.model;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.panels.PanelsModel;


public class ModelFacade {
	
	private BinModel binModel;
	private TabsModel tabsModel;
	private PanelsModel panelsModel;	
	private CommandLineModel commandLineModel;
	private RinitClientModel rinitClientModel;
	
	public ModelFacade(IEventHandler eventHandler) {
		this.binModel = new BinModel(eventHandler, this);
		this.tabsModel = new TabsModel(eventHandler);
		this.panelsModel = new PanelsModel(eventHandler);
		this.commandLineModel = new CommandLineModel(eventHandler);
		this.rinitClientModel = new RinitClientModel(eventHandler);
	}

	public BinModel getBinModel() {
		return this.binModel;
	}

	public TabsModel getTabsModel() {
		return tabsModel;
	}
	
	public PanelsModel getPanelsModel() {
		return this.panelsModel;
	}
	
	public CommandLineModel getCommandLineModel() {
		return this.commandLineModel;
	}

	public RinitClientModel getRinitClientModel() {
		return rinitClientModel;
	}
	
}
