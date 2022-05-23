package com.rinit.gui.model;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

import com.rinit.debugger.server.core.Extentions;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.panels.PanelsModel;
import com.rinit.gui.utils.ReflectionUtils;

public class FileOperationModel extends AbstractModel {

	
	private ModelFacade modelFacade;
	private IFileService fileService;
	private PanelsModel panelsModel;

	
	public FileOperationModel(IEventHandler eventHandler, ModelFacade modelFacade) {
		super(eventHandler);
		this.modelFacade = modelFacade;
		this.fileService = this.modelFacade.getRinitClientModel().getClient().getFileService();
		this.panelsModel = this.modelFacade.getPanelsModel();	
	}
	
	public void mkFile(AbstractDriver file) throws FileAlreadyExistsException {
		if (this.fileService.isFileExists(file.toDTO())) 
			throw new FileAlreadyExistsException(file.toDTO().getFullName());

		try {
			this.fileService.saveFile(file);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.panelsModel.reUpdatePanels();	
	}
	
	public void deleteFile(AbstractDriver file) {
		this.fileService.deleteFile(file.toDTO());
	}
	
	public void mkDir(String dirName) throws FileAlreadyExistsException {
		this.mkDir(dirName, this.panelsModel.getCurrentPath());
	}
	
	public void mkDir(String dirName, String dirPath) throws FileAlreadyExistsException {
		FileDTO dirDto = FileDTO.builder().name(dirName).extention(Extentions.DIRECTORY).path(dirPath).build();
		
		if (this.fileService.isFileExists(dirDto))
			throw new FileAlreadyExistsException(dirDto.getFullName());
		
		try {
			this.fileService.createFile(dirDto);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.panelsModel.reUpdatePanels();	
	}
	
	public boolean isDirExists(String dir) {
		String dirName = dir.split("/")[dir.split("/").length-1];
	    String path = dir.replace(dirName + "/", "");
		FileDTO dirDTO = FileDTO.builder().name(dirName).path(path).extention(Extentions.DIRECTORY).build();
		
		return this.fileService.isFileExists(dirDTO);
	}
	
	public FileDTO getFilWithPathAndName(String path, String name) {
		return this.fileService.getFileByPathAndName(path, name).get(0);
	}
	
	public <T extends AbstractDriver> List<T> getAllFilesByDirExtention(String dirPath, String extention, Class<T> driverClass) {
		List<T> drivers = new ArrayList<T>();
		List<FileDTO> dtos = this.fileService.getFilesByPathAndExtention(dirPath, extention);
		for (FileDTO dto : dtos) {
			T driver = ReflectionUtils.createInstanceOf(driverClass);
			driver.fromDTO(dto);
			drivers.add(driver);
		}
		return drivers;
	}
	
	public String joinPaths(String...pathes) {
		String joinedPath = "";
		for (String path : pathes) {
			if (!path.endsWith("/"))
				path += "/";
			joinedPath += path;
		}
		return joinedPath;
	}

}
