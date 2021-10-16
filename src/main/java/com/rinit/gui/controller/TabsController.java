package com.rinit.gui.controller;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.TabsModel;

public class TabsController extends AbstractController {

	private TabsModel tabsModel;
	
	public TabsController(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler, modelFacade);
		this.tabsModel = modelFacade.getTabsModel();
	}

	private void closeTab() {
		
	}
	
	@Override
	protected void subscribeForEvents() {
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				closeTab();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
	}

}
