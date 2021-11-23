package com.rinit.gui.dev.drivers.validator;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.drivers.validator.driver.ValidatorDriver;
import com.rinit.gui.model.ModelFacade;

public class ValidatorCliDriverLogic {

	private IFileService fileService;
	
	private FileDTO readingFile;
	private ValidatorDriver file = new ValidatorDriver();
	
	public ValidatorCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		this.fileService = modelFacade.getRinitClientModel().getClient().getFileService();
		this.readingFile = readingFile;
		this.file.fromDTO(this.readingFile);
	}

	public ValidatorSubmitData getInitialData() {
		ValidatorSubmitData initialData = new ValidatorSubmitData();
		if (this.file.getRequiredHttpCode() == 0) 
			initialData.requiredHttpCode = 404;
		else {
			initialData.requiredHttpCode = this.file.getRequiredHttpCode();
			initialData.requiredContent = this.file.getRequiredContent();
		}
		return initialData;
		
	}
	
	public void submit(ValidatorSubmitData submitData) {
		this.updateFile(submitData);
		this.saveFile();
	}
	
	private void saveFile() {
		try {
			this.fileService.saveFile(this.file);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private void updateFile(ValidatorSubmitData submitData) {
		this.file.setRequiredHttpCode(submitData.requiredHttpCode);
		this.file.setRequiredContent(submitData.requiredContent);
	}
	
}
