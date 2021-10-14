package com.rinit.gui.controller;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.event.Mode;
import com.rinit.gui.model.CommandLineModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.bin.BinModel;
import com.rinit.gui.model.viewModel.CommandViewModel;

public class CommandLineController extends AbstractController {

	private CommandLineModel commandLineModel;
	private BinModel binModel;
	
	public CommandLineController(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler, modelFacade);
		this.commandLineModel = modelFacade.getCommandLineModel();
	}

	private void goToCommandLine() {
		this.commandLineModel.switchToInputMode();
	}
	
	private void goOutCommandLine() {
		this.commandLineModel.switchToDefaultMode();
	} 
	
	private void executeCommand(IEventContext eventInfo) {
		CommandViewModel viewModel = (CommandViewModel)eventInfo;
		this.binModel.execute(viewModel.getCommand());
	}
	
	@Override
	protected void subscribeForEvents() {
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				goToCommandLine();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), Mode.DEFAULT);
		
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				goOutCommandLine();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), Mode.INSERT);
		
		this.eventHandler.subscribe(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				executeCommand(eventInfo);
			}
		
		}, Event.SUBMIT_COMMAND);

	}
	
}
