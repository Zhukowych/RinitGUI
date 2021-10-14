package com.rinit.gui.controller;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.event.Mode;
import com.rinit.gui.model.CommandLineModel;
import com.rinit.gui.model.ModelFacade;

public class CommandLineController extends AbstractController {

	private CommandLineModel commandLineModel;
	
	public CommandLineController(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler, modelFacade);
		this.commandLineModel = modelFacade.getCommandLineModel();
	}

	public void goToCommandLine() {
		this.commandLineModel.switchToInputMode();
	}
	
	@Override
	protected void subscribeForEvents() {
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				goToCommandLine();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), Mode.DEFAULT);
		
		

	}
	
}
