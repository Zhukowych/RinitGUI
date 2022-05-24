package com.rinit.gui.dev.drivers.variables;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.drivers.variables.driver.VariablesDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriverLogic;

public class VariablesCliDriverLogic extends AbstractCliDriverLogic {

	private IFileService fileService;
	private VariablesDriver variables = new VariablesDriver();
	
	public VariablesCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.fileService = this.modelFacade.getRinitClientModel().getClient().getFileService();
		this.variables.fromDTO(readingFile);
	}
	
	public VariablesDriver getInitialData() {
		return this.variables;
	}
	
	public void save(VariablesDriver updatedVariables) {
		this.variables.setValues(updatedVariables.getValues());
		try {
			this.fileService.saveFile(this.variables);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
