package com.rinit.gui;

import java.awt.event.WindowAdapter;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.rinit.gui.controller.ControllerFacade;
import com.rinit.gui.event.EventHandler;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.view.MainView;
import com.rinit.gui.view.SetupView;

public class Main {
	
	public static String host;

	public static void main(String[] args) {
		Main.setLookAndFeel();
		SetupView setUp = new SetupView();
		
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
	
	public static void setLookAndFeel() {
		try {
	        UIManager.setLookAndFeel(
	                UIManager.getSystemLookAndFeelClassName());
	    }catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	}

}
