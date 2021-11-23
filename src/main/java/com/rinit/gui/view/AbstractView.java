package com.rinit.gui.view;

import javax.swing.JPanel;

import com.rinit.gui.event.IEventHandler;

public abstract class AbstractView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -953529493335343411L;
	
	protected IEventHandler eventHandler;
	
	public AbstractView(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
		this.subscribeForEvents();
	}
	
	protected abstract void subscribeForEvents();
	
}
