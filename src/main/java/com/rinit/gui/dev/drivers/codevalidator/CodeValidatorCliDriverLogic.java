package com.rinit.gui.dev.drivers.codevalidator;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.drivers.codevalidator.dirver.CodeValidatorDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriverLogic;

public class CodeValidatorCliDriverLogic extends AbstractCliDriverLogic{

	private CodeValidatorDriver file = new CodeValidatorDriver();
	private IFileService fileService;
	
	public CodeValidatorCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.fileService = this.modelFacade.getRinitClientModel().getClient().getFileService();
		this.file.fromDTO(readingFile);
	}

	public CodeValidatorDriver getInitialData() {
		return this.file;
	}
	
	public void save(CodeValidatorDriver codeValidator) {
		this.file.setValidatorClassPath(codeValidator.getValidatorClassPath());
		this.file.setValidatroClassName(codeValidator.getValidatroClassName());
		try {
			this.fileService.saveFile(this.file);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
