package com.rinit.gui.model.panels;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventHandler;

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
		this.setPanel(Panel.RIGHT);
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

	public String getCurrentPath() {
		return this.getSelectedPanelModel().getCurrentPath();
	}
	
	public void reUpdatePanels() {
		if (this.leftPanelModel.getCurrentPath().equals(this.rightPanelModel.getCurrentPath())) {
			this.leftPanelModel.reUpdateFiles();
			this.rightPanelModel.reUpdateFiles();
		} else {
			this.selectedPanelModel.reUpdateFiles();
		}
	}
	
}
