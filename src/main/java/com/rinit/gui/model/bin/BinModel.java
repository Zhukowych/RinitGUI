package com.rinit.gui.model.bin;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.upload.UploadBin;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.AbstractModel;

public class BinModel extends AbstractModel{
	
	private Map<String, Class<? extends AbstractCliBin>> bins = new HashMap<String, Class<? extends AbstractCliBin>>();
	
	public BinModel(IEventHandler eventHandler) {
		super(eventHandler);
		this.addDefaultBin();
	}
	
	public void execute(String command) {
		
	}
	
	private AbstractCliBin getBin(String binName) {
		Class<? extends AbstractCliBin> bin = this.bins.get(binName);
		try {
			AbstractCliBin binObject = bin.getDeclaredConstructor().newInstance();
			return binObject;
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

}
