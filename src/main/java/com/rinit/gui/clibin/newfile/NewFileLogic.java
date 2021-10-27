package com.rinit.gui.clibin.newfile;

import javax.swing.JDialog;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.panels.PanelsModel;

public class NewFileLogic {
	
	private PanelsModel panelsModel;
	private RinitClient client;
	private IFileService fileServiceClient;
	private JDialog popUp;
	
	public NewFileLogic(ModelFacade modelFacade) {
		this.panelsModel = modelFacade.getPanelsModel();
		this.client = modelFacade.getRinitClientModel().getClient();
		this.fileServiceClient = this.client.getFileService();
	}

	public String getPathWhereAddFile() {
		return this.panelsModel.getCurrentPath();
	}
	
	public void submit(String fileName, String fileExtention) throws LogicException {
		if (fileName.isEmpty() || fileExtention.isEmpty())
			throw new LogicException("file name or file extention isn't stated");
		FileDTO fileToCreate = FileDTO.builder().name(fileName).extention(fileExtention).path(this.getPathWhereAddFile()).build();
		try {
			this.fileServiceClient.createFile(fileToCreate);
		} catch (ServiceException e) {
			throw new LogicException(e.getMessage());
		}
		this.panelsModel.reUpdatePanels();
	}
	
}
