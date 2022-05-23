package com.rinit.gui.dev.drivers.graphql;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.drivers.graphql.driver.GraphqlDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriverLogic;

public class GraphqlCliDriverLogic extends AbstractCliDriverLogic {

	private GraphqlDriver file;
	private IFileService fileService;
	
	public GraphqlCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		
		this.fileService = this.modelFacade.getRinitClientModel().getClient().getFileService();
		
		this.file = new GraphqlDriver();
		this.file.fromDTO(readingFile);
	}
	
	public GraphqlDriver getReadingFile() {
		return this.file;
	}
	
	public void save(GraphqlDriver fileToSave) {
		this.file.setApiUrl(fileToSave.getApiUrl());
		this.file.setQueryName(fileToSave.getQueryName());
		try {
			this.fileService.saveFile(this.file);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
