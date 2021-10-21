package com.rinit.gui.model.fileDriver.cliDrivers;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.exceptions.LogicException;

public class DefaultFileDriverLogic {

	private FileDTO readingFile;
	private IFileService fileServiceClient;

	public FileDTO getReadingFile() {
		return this.readingFile;
	}

	public void setReadingFile(FileDTO readingFile) {
		this.readingFile = readingFile;
	}
	
	public String getContent() {
		return this.readingFile.getContent();
	}
	
	public void setFileServiceClient(IFileService fileServiceClient) {
		this.fileServiceClient = fileServiceClient;
	}
	
	public void save(String content) throws LogicException {
		this.readingFile.cwrite(content);
		try {
			this.readingFile = this.fileServiceClient.saveFile(this.readingFile);
		} catch (ServiceException e) {
			throw new LogicException(e.getMessage());
		}
	}


}
