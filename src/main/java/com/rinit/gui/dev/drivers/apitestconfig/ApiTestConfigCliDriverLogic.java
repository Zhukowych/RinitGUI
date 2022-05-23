package com.rinit.gui.dev.drivers.apitestconfig;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.drivers.apitestconfig.driver.ApiTestConfiigDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriverLogic;

public class ApiTestConfigCliDriverLogic extends AbstractCliDriverLogic {
	
	private IFileService fileService;
	private ApiTestConfiigDriver apiTestConfig;
	
	public ApiTestConfigCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.fileService = this.modelFacade.getRinitClientModel().getClient().getFileService();
	}
	
	public ApiTestConfiigDriver getInitialData() {
		this.apiTestConfig = new ApiTestConfiigDriver();
		this.apiTestConfig.fromDTO(readingFile);
		return this.apiTestConfig;
	}
	
	public void submit(ApiTestConfiigDriver submitData) {
		this.apiTestConfig.setRootParseFolderPath(submitData.getRootParseFolderPath());
		this.apiTestConfig.setFilesToParse(submitData.getFilesToParse());
		try {
			this.fileService.saveFile(this.apiTestConfig);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
}
