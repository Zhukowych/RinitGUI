package com.rinit.gui.model;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.Mode;

public class CommandLineModel extends AbstractModel {

	public CommandLineModel(IEventHandler eventHandler) {
		super(eventHandler);
	}
	
	public void switchToInputMode() {
		this.eventHandler.setKeyMode(Mode.INSERT);
		this.eventHandler.performEvent(Event.SWITCH_TO_INPUT_MODE, this, null);
	}
	
	public void switchToDefaultMode() {
		this.eventHandler.setKeyMode(Mode.DEFAULT);
		this.eventHandler.performEvent(Event.SWITCH_TO_DEFAULT_MODE, this, null);
	}

}
