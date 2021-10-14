package com.rinit.gui.model.panels;

import java.util.ArrayList;
import java.util.List;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.mock.TestFileClient;
import com.rinit.gui.model.viewModel.FilesListViewModel;
import com.rinit.gui.model.viewModel.SelectionViewModel;

public class PanelsModel {
	
	private IEventHandler eventHandler;
	
	private Panel currentPanel;

	private AbstractPanelModel selectedPanelModel;
	
	private AbstractPanelModel leftPanelModel;
	private AbstractPanelModel rightPanelModel;
	
	
	public PanelsModel(IEventHandler eventHandler){	
		this.eventHandler = eventHandler;
		this.leftPanelModel = new LeftPanelModel(eventHandler);
		this.rightPanelModel = new RightPanelModel(eventHandler);
	}
	
	public void setPanel(Panel goToPanel) {
		this.currentPanel = goToPanel;
		this.eventHandler.performEvent(Event.CLEAR_SELECTION, this, null);
		if (goToPanel == Panel.LEFT) {
			this.selectedPanelModel = this.leftPanelModel;
		}else {
			this.selectedPanelModel = this.rightPanelModel;
		}
		this.selectedPanelModel.switchTo();
	}
	
	public AbstractPanelModel getSelectedPanelModel() {
		return this.selectedPanelModel;
	}
	
}
