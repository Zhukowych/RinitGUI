package com.rinit.gui.model.panels;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventHandler;

public class RightPanelModel extends AbstractPanelModel{

	public RightPanelModel(IEventHandler eventHandler) {
		super(eventHandler);
	}
	
	@Override
	protected void updateViewSelection() {
		this.eventHandler.performEvent(Event.GO_RIGHT_PANEL, this, this.fileList);
	}
	
	@Override
	protected void updateView() {
		this.eventHandler.performEvent(Event.RIGHT_PANEL_UPDATE_FILES, this, this.fileList);
	}

}
