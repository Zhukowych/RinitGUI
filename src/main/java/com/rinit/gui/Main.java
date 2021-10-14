package com.rinit.gui;

import javax.swing.JPanel;

import com.rinit.gui.controller.ControllerFacade;
import com.rinit.gui.event.EventHandler;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.view.MainView;

public class Main {

	public static void main(String[] args) {
		EventHandler eventHandler = new EventHandler();
		MainView window = new MainView(eventHandler);	
		ModelFacade modelFacade = new ModelFacade(eventHandler);
		ControllerFacade controllerFacade = new ControllerFacade(eventHandler, modelFacade);
		eventHandler.bindKeyEvents(window.getRootPane());
		window.open();
	}

}
