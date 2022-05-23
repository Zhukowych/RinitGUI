package com.rinit.gui.dev.drivers.parsedobject;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.drivers.parsedobject.dirver.ParsedObjectDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriverLogic;

public class ParsedObjectCliDriverLogic extends AbstractCliDriverLogic {

	private ParsedObjectDriver file;
	private IFileService fileService;
	
	
	public ParsedObjectCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.file = new ParsedObjectDriver();
		this.fileService = this.modelFacade.getRinitClientModel().getClient().getFileService();
		this.file.fromDTO(readingFile);
	}

	public ParsedObjectDriver getInitialData() {
		return this.file;
	}
	
	public void submit() {
		try {
			this.fileService.saveFile(this.file);
		} catch (ServiceException e) {e.printStackTrace();}
	}
	
}
