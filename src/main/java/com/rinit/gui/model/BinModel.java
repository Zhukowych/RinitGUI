package com.rinit.gui.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.newfile.NewFileBin;
import com.rinit.gui.clibin.read.ReadBin;
import com.rinit.gui.clibin.upload.UploadBin;
import com.rinit.gui.dev.core.DevCliBins;
import com.rinit.gui.event.IEventHandler;

public class BinModel extends AbstractModel{
	
	private ModelFacade modelFacade;
	private TabsModel tabsModel;
	private Map<String, Class<? extends AbstractCliBin>> bins = new HashMap<String, Class<? extends AbstractCliBin>>();
	
	public BinModel(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler);
		this.modelFacade = modelFacade;
		this.tabsModel = this.modelFacade.getTabsModel();
		this.addDefaultBin();
	}
	
	public void execute(String command) {
		AbstractCliBin bin = this.getBin(this.getBinName(command), this.getParameters(command));
		this.tabsModel.addTab(bin);
	}
	
	private AbstractCliBin getBin(String binName, String[] params) {
		Class<? extends AbstractCliBin> bin = this.bins.get(binName);
		try {
			Constructor<? extends AbstractCliBin> cons  = bin.getConstructor(String[].class, ModelFacade.class);
			Object params_ = (Object)params;
			Object modelFacade = (Object)this.modelFacade;
			Object fd = cons.newInstance(params_, modelFacade);
			return (AbstractCliBin) fd;
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
		DevCliBins devCliBins = new DevCliBins();
		this.bins.putAll(devCliBins.getDevBins());
		this.bins.put(UploadBin.NAME, UploadBin.class);
		this.bins.put(ReadBin.NAME, ReadBin.class);
		this.bins.put(NewFileBin.NAME, NewFileBin.class);
	}

	private String getBinName(String command) {
		return command.split("\\s+")[0];
	}
	
	private String[] getParameters(String command) {
		String[] parts = command.split("\\s+");
		return Arrays.copyOfRange(parts, 1, parts.length);
	}
}
