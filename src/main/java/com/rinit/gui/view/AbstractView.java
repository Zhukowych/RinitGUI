package com.rinit.gui.view;

import javax.swing.JPanel;

import com.rinit.gui.event.IEventHandler;

public abstract class AbstractView extends JPanel {
	
	protected IEventHandler eventHandler;
	
	public AbstractView(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}
	
}
