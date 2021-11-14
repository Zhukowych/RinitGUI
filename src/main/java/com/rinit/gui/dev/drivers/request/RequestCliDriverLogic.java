package com.rinit.gui.dev.drivers.request;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.drivers.request.driver.RequestDriver;
import com.rinit.gui.model.ModelFacade;

public class RequestCliDriverLogic {

	private FileDTO readingFile;
	private ModelFacade modelFacade;
	private RequestDriver request = new RequestDriver();
	
	public RequestCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
		this.readingFile = readingFile;
		this.request.fromDTO(readingFile);
	}

	public RequestSubmitData getInitialData() {
		if (!this.readingFile.getContent().isEmpty()) {
			RequestSubmitData submitData = new RequestSubmitData(this.request.getProtocol(), this.request.getAdress(), this.request.getPath());
			return submitData;
		} else {
			return new RequestSubmitData("", "", "");
		}
	}
	
	public void submit(RequestSubmitData submitData) {
		RequestDriver file = this.createRequestFile(submitData);
		this.saveRequestFile(file);
	}
	
	private RequestDriver createRequestFile(RequestSubmitData submitData) {
		RequestDriver file = new RequestDriver();
		file.fromDTO(this.readingFile);
		file.setProtocol(submitData.protocol);
		file.setAdress(submitData.adress);
		file.setPath(submitData.path);
		
		return file;
	}
	
	private void saveRequestFile(RequestDriver file) {
		IFileService fileService = this.modelFacade.getRinitClientModel().getClient().getFileService();
		try {
			fileService.saveFile(file);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
