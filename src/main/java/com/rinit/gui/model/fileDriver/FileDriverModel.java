package com.rinit.gui.model.fileDriver;

import java.lang.reflect.Constructor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.rinit.debugger.server.core.Extentions;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.debugger.server.file.library.LibraryClassNotFoundException;
import com.rinit.debugger.server.file.library.LibraryDriver;
import com.rinit.debugger.server.services.interfaces.IFileDriverService;
import com.rinit.debugger.server.utils.CollectionUtils;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.exceptions.DriverNotFoundException;
import com.rinit.gui.model.AbstractModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.cliDrivers.DefaultCliDriver;
import com.rinit.gui.model.fileDriver.dev.DevDrivers;

public class FileDriverModel extends AbstractModel {

	private ModelFacade modelFacade;
	private Map<String, Class<? extends AbstractCliDriver>> cliFileDriver = new HashMap<String, Class<? extends AbstractCliDriver>>();
	private Map<String, Class<? extends AbstractDriver>> fileDrivers = new HashMap<String, Class<? extends AbstractDriver>>();
	
	
	public FileDriverModel(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler);
		this.modelFacade = modelFacade;
		this.addDefaultCliFileDriver();
		this.addFileDrivers();
	}
	
	public Map<String, Class<? extends AbstractDriver>> getFileDrivers() {
		return fileDrivers;
	}
	
	public AbstractDriver getDriver(FileDTO readingFile) throws DriverNotFoundException {
		Class<? extends AbstractDriver> driverClass = this.fileDrivers.get(readingFile.getExtention());
		if (driverClass == null)
			throw new DriverNotFoundException(String.format("There is no driver with name %s", readingFile.getExtention()));
		AbstractDriver driver = null;
		try {
			driver = driverClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException	| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (driver == null)
			throw new DriverNotFoundException(String.format("Can't create driver with name with name %s", readingFile.getExtention()));
		
		driver.fromDTO(readingFile);
		return driver;
	}

	public AbstractCliDriver getCliFileDriverForExtention(FileDTO readingFile) {
		Class<? extends AbstractCliDriver> driver = this.cliFileDriver.get(readingFile.getExtention());
		if (driver == null)
			driver = this.cliFileDriver.get(DefaultCliDriver.NAME);
		Constructor<? extends AbstractCliDriver> cons = null;
		try {
			cons = driver.getConstructor(FileDTO.class, ModelFacade.class);
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
		Object _readingFile = (Object)readingFile;
		Object _modelFacade = (Object)this.modelFacade;
		try {
			return cons.newInstance(_readingFile, _modelFacade);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AbstractDriver getDriverForFile(FileDTO file) throws DriverNotFoundException {
		Class<? extends AbstractDriver> driverClass = this.fileDrivers.get(file.getExtention());
		if (driverClass == null)
			throw new DriverNotFoundException("Driver not found");
		try {
			AbstractDriver driver = driverClass.getDeclaredConstructor().newInstance();
			driver.fromDTO(file);
			return driver;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AbstractDriver getDriverInstance(String extention) {
		Class<? extends AbstractDriver> driverClass = this.fileDrivers.get(extention);
		try {
			driverClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getDriversNames(){
		return CollectionUtils.setToList(this.fileDrivers.keySet());
	}

	public boolean isExtentionDirable(String extention) {
		if (extention.equals(Extentions.DIRECTORY))
			return true;
		
		Class<? extends AbstractCliDriver> driverClass = this.cliFileDriver.get(extention);
		if (driverClass == null)
			return false;
		AbstractCliDriver cliDriver = null;
		try {
			cliDriver = driverClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (cliDriver == null) 
			return false;
		
		return cliDriver.isDirable();
	}
	
	public void updateDrivers() {
		this.cliFileDriver.clear();
		this.fileDrivers.clear();
		this.addDefaultCliFileDriver();
	}
	
	private void addDefaultCliFileDriver() {
		DevDrivers devDrivers = new DevDrivers();
		this.cliFileDriver.putAll(this.getRemoteDrivers());
		this.cliFileDriver.putAll(devDrivers.getCliFileDrivers());
		this.cliFileDriver.put(DefaultCliDriver.NAME, DefaultCliDriver.class);
	}
	
	private void addFileDrivers() {
		for (Entry<String, Class<? extends AbstractCliDriver>> entry : cliFileDriver.entrySet()) {
			this.fileDrivers.put(entry.getKey(), this.getCliDriverInstence(entry.getValue()).getDriver());
		}
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Class<? extends AbstractCliDriver>> getRemoteDrivers(){
		Map<String, Class<? extends AbstractCliDriver>> remoteCliDrivers = new HashMap<String, Class<? extends AbstractCliDriver>>();
		IFileDriverService fileDriverServiceClient = this.modelFacade.getRinitClientModel().getClient().getFileDriverService();
		Map<String, LibraryDriver> driversLibraries = null;
		try {
			driversLibraries = fileDriverServiceClient.getFileDrivers();
		} catch (Exception  e1) {e1.printStackTrace();}
		if (driversLibraries == null)
			return remoteCliDrivers;
		for (Entry<String, LibraryDriver> entry : driversLibraries.entrySet()) {
			try {
				remoteCliDrivers.put(entry.getKey(), (Class<? extends AbstractCliDriver>) entry.getValue().getClassWithName(entry.getKey()));
			} catch (LibraryClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return remoteCliDrivers;
	}

	private AbstractCliDriver getCliDriverInstence(Class<? extends AbstractCliDriver> driverClass) {
		try {
			return driverClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
