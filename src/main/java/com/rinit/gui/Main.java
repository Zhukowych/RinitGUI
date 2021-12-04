package com.rinit.gui;

import java.awt.event.WindowAdapter;


import com.rinit.gui.controller.ControllerFacade;
import com.rinit.gui.event.EventHandler;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.setup.Configurer;
import com.rinit.gui.setup.SetupView;
import com.rinit.gui.view.MainView;


public class Main {
	
	public static String host;

	public static void main(String[] args) {
		SetupView setUp = new SetupView();
		Configurer configurer = new Configurer();
		configurer.config();
		
		setUp.addWindowListener(new WindowAdapter(){
		    @Override
		    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
		    	Main.run(setUp.getSelectedHost());
		    }
		});
		setUp.setVisible(true);

	}
	
	@SuppressWarnings("unused")
	public static void run(String serverHost) {
		Main.host = serverHost;
		EventHandler eventHandler = new EventHandler();
		MainView window = new MainView(eventHandler);	
		ModelFacade modelFacade = new ModelFacade(eventHandler);
		ControllerFacade controllerFacade = new ControllerFacade(eventHandler, modelFacade);
		eventHandler.bindKeyEvents(window.getRootPane());
		window.open();
	}



}
