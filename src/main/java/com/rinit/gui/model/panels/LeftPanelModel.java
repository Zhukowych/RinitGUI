package com.rinit.gui.model.panels;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventHandler;

public class LeftPanelModel extends AbstractPanelModel {

	public LeftPanelModel(IEventHandler eventHandler) {
		super(eventHandler);
	}

	@Override
	protected void updateViewSelection() {
		this.eventHandler.performEvent(Event.GO_LEFT_PANEL, this, this.fileList);
	}

	@Override
	protected void updateView() {
		this.eventHandler.performEvent(Event.LEFT_PANEL_UPDATE_FILES, this, this.fileList);
	}
	
}
