package com.rinit.gui.event;

import javax.swing.KeyStroke;

public interface IEventHandler {
	
	public void subscribe(IListener listener, Event event);
	public void subscribeForKeyEvent(IListener listener, KeyStroke stroke, Mode mode);
	public void setKeyMode(Mode mode);
	
	public void performEvent(Event event, Object parent, IEventContext context);
	
}
