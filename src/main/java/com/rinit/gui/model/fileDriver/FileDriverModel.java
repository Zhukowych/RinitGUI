package com.rinit.gui.model.fileDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.AbstractModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.cliDrivers.DefaultCliDriver;
import com.rinit.gui.model.fileDriver.dev.DevDrivers;

public class FileDriverModel extends AbstractModel {

	private ModelFacade modelFacade;
	private Map<String, Class<? extends AbstractCliFileDriver>> defaultCliFileDriver = new HashMap<String, Class<? extends AbstractCliFileDriver>>();
	
	public FileDriverModel(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler);
		this.modelFacade = modelFacade;
		this.addDefaultCliFileDriver();
	}

	public AbstractCliFileDriver getCliFileDriverForExtention(FileDTO readingFile) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<? extends AbstractCliFileDriver> driver = this.defaultCliFileDriver.get(readingFile.getExtention());
		if (driver == null)
			driver = this.defaultCliFileDriver.get(DefaultCliDriver.NAME);
		Constructor<? extends AbstractCliFileDriver> cons  = driver.getConstructor(FileDTO.class, ModelFacade.class);
		Object _readingFile = (Object)readingFile;
		Object _modelFacade = (Object)this.modelFacade;
		return cons.newInstance(_readingFile, _modelFacade);
	}

	private void addDefaultCliFileDriver() {
		DevDrivers devDrivers = new DevDrivers();
		this.defaultCliFileDriver.putAll(devDrivers.getCliFileDrivers());
		this.defaultCliFileDriver.put(DefaultCliDriver.NAME, DefaultCliDriver.class);
	}

}
