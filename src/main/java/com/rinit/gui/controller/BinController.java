package com.rinit.gui.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map.Entry;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.event.Mode;
import com.rinit.gui.model.BinModel;
import com.rinit.gui.model.ModelFacade;

public class BinController {
	
	private IEventHandler eventHandler;
	private BinModel binModel;
	
	public BinController(IEventHandler eventHandler, ModelFacade modelFacade) {
		this.binModel = modelFacade.getBinModel();
		this.eventHandler = eventHandler;

		this.bindListeners();
	}
	
	public void bindListeners() {
		for (Entry<String, Class<? extends AbstractCliBin>> entry : this.binModel.getBins().entrySet()) {
			this.bindBinForKeyBoard(entry.getValue());
		}
	}
	
	private void bindBinForKeyBoard(Class<? extends AbstractCliBin> binClass) {
		AbstractCliBin bin = null;
		try {
			bin = binClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} 
		if (bin != null) {
			this.subsctibeForKeyEvent(bin);
		}
	}
	
	private void subsctibeForKeyEvent(AbstractCliBin bin) {
		if (bin.getKeyBinding() == null)
			return;

		this.eventHandler.subscribeForKeyEvent(new IListener() {
			
			@Override
			public void eventPerformed(IEventContext eventInfo) {
				binModel.execute(bin.getName());
			}
		
		}, bin.getKeyBinding(), Mode.DEFAULT);
	}
	
}
