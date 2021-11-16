package com.rinit.gui.clibin.newfile;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.panels.PanelsModel;

public class NewFileLogic {
	
	public enum LogicType {
		NEW, EDIT
	}
	
	private LogicType type;
	private PanelsModel panelsModel;
	private RinitClient client;
	private IFileService fileServiceClient;
	
	public NewFileLogic(ModelFacade modelFacade, LogicType type) {
		this.panelsModel = modelFacade.getPanelsModel();
		this.client = modelFacade.getRinitClientModel().getClient();
		this.fileServiceClient = this.client.getFileService();
		this.type = type;
	}

	public NewFileSubmitData getInitialData() {
		if (this.type == LogicType.NEW) {
			return null;
		} else {
			FileDTO editingFile = this.getEditingFile();
			return new NewFileSubmitData(editingFile.getName(), editingFile.getExtention(), editingFile.getPosition());
		}
	}
	
	public String getPathWhereAddFile() {
		return this.panelsModel.getCurrentPath();
	}
	
	public void submit(NewFileSubmitData submitData) throws LogicException {
		if (submitData.fileName.isEmpty() || submitData.fileExtention.isEmpty())
			throw new LogicException("file name or file extention isn't stated");
		FileDTO fileToUpdate = this.getFileToUpdate(submitData);
		this.updateEditingFile(fileToUpdate);
		this.panelsModel.reUpdatePanels();
	}
	
	private void updateEditingFile(FileDTO fileToUpdate) throws LogicException {
		if (this.type == LogicType.NEW) {
			this.createFile(fileToUpdate);
		} else {
			this.saveFile(fileToUpdate);
		}
	}
	
	private FileDTO getFileToUpdate(NewFileSubmitData submitData) {
		if (this.type == LogicType.NEW) {
			return FileDTO.builder().name(submitData.fileName).extention(submitData.fileExtention).path(this.getPathWhereAddFile()).position(submitData.filePosition).build();	
		} else {
			FileDTO editingFile = this.getEditingFile();
			editingFile.setName(submitData.fileName);
			editingFile.setExtention(submitData.fileExtention);
			editingFile.setPosition(submitData.filePosition);
			return editingFile;
		}

	}
	
	private void saveFile(FileDTO fileToUpdate) throws LogicException {
		try {
			this.fileServiceClient.saveFile(fileToUpdate);
		} catch (ServiceException e) {
			throw new LogicException(e.getMessage());
		}
	}
	
	private void createFile(FileDTO fileToUpdate) throws LogicException {
		try {
			this.fileServiceClient.createFile(fileToUpdate);
		} catch (ServiceException e) {
			throw new LogicException(e.getMessage());
		}
	}
	
	private FileDTO getEditingFile() {
		return this.panelsModel.getSelectedPanelModel().getSelectedFile();
	}
	
}
