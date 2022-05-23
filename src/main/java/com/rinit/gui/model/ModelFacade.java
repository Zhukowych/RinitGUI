package com.rinit.gui.model;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.fileDriver.FileDriverModel;
import com.rinit.gui.model.panels.PanelsModel;


public class ModelFacade {
	
	private BinModel binModel;
	private LibraryModel libraryModel;
	private TabsModel tabsModel;
	private PanelsModel panelsModel;	
	private CommandLineModel commandLineModel;
	private FileDriverModel fileDriverModel;
	private RinitClientModel rinitClientModel;
	private FileOperationModel fileOperationModel;
	
	public ModelFacade(IEventHandler eventHandler) {
		this.rinitClientModel = new RinitClientModel(eventHandler); // must be first
		this.tabsModel = new TabsModel(eventHandler); // must be before bin model
		this.binModel = new BinModel(eventHandler, this);
		this.libraryModel = new LibraryModel(eventHandler, this);
		this.panelsModel = new PanelsModel(eventHandler);
		this.commandLineModel = new CommandLineModel(eventHandler);
		this.fileDriverModel = new FileDriverModel(eventHandler, this);
		this.fileOperationModel = new FileOperationModel(eventHandler, this);
	}

	public BinModel getBinModel() {
		return this.binModel;
	}

	public TabsModel getTabsModel() {
		return this.tabsModel;
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

	public FileDriverModel getFileDriverModel() {
		return fileDriverModel;
	}

	public FileOperationModel getFileOperationModel() {
		return fileOperationModel;
	}
		
}
