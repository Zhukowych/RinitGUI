package com.rinit.gui.model.bin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.upload.UploadBin;
import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.AbstractModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.viewModel.CliBinViewModel;

public class BinModel extends AbstractModel{
	
	private ModelFacade modelFacade;
	private Map<String, Class<? extends AbstractCliBin>> bins = new HashMap<String, Class<? extends AbstractCliBin>>();
	
	public BinModel(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler);
		this.modelFacade = modelFacade;
		this.addDefaultBin();
	}
	
	public void execute(String command) {
		AbstractCliBin bin = this.getBin(this.getBinName(command), this.getParameters(command));
		CliBinViewModel viewModel = new CliBinViewModel();
		viewModel.setView(bin.getView());
		this.eventHandler.performEvent(Event.OPEN_TAB, this, viewModel);
	}
	
	private AbstractCliBin getBin(String binName, String[] params) {
		Class<? extends AbstractCliBin> bin = this.bins.get(binName);
		try {
			Constructor<? extends AbstractCliBin> cons  = bin.getConstructor(String[].class, ModelFacade.class);
			Object params_ = (Object)params;
			Object modelFacade = (Object)this.modelFacade;
			return cons.newInstance(params_, modelFacade);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void addDefaultBin() {
		bins.put(UploadBin.NAME, UploadBin.class);
	}

	private String getBinName(String command) {
		return command.split("\\s+")[0];
	}
	
	private String[] getParameters(String command) {
		String[] parts = command.split("\\s+");
		return Arrays.copyOfRange(parts, 1, parts.length);
	}
}
