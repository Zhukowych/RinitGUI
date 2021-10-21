package com.rinit.gui.model.fileDriver;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.AbstractModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.cliDrivers.DefaultCliDriver;

public class FileDriverModel extends AbstractModel {

	private Map<String, Class<? extends AbstractCliFileDriver>> defaultCliFileDriver = new HashMap<String, Class<? extends AbstractCliFileDriver>>();
	
	public FileDriverModel(IEventHandler eventHandler) {
		super(eventHandler);
		this.addDefaultCliFileDriver();
	}

	public AbstractCliFileDriver getCliFileDriverForExtention(FileDTO readingFile, String extention) throws NoSuchMethodException, SecurityException{
		Class<? extends AbstractCliFileDriver> driver = this.defaultCliFileDriver.get(extention);
		if (driver == null)
			driver = this.defaultCliFileDriver.get(DefaultCliDriver.NAME);
		
		Constructor<? extends AbstractCliFileDriver> cons  = driver.getConstructor(FileDTO.class, ModelFacade.class);
		Object _readingFile = (Object)this.
	}

	private void addDefaultCliFileDriver() {
		this.defaultCliFileDriver.put(DefaultCliDriver.NAME, DefaultCliDriver.class);
	}

}
