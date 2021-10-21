package com.rinit.gui.clibin.read;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;

public class ReadBin extends AbstractCliBin  {

	public static final String NAME = "read";

	private String filePath;
	private String fileName;
	private RinitClient rinitClient;
	private FileDTO readingFile;
	private AbstractCliFileDriver readingFileDriver;
	
	public ReadBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.rinitClient = this.modelFacade.getRinitClientModel().getClient();
		this.splitParams();
		this.setReadingFile();
	}

	@Override
	public AbstractCliBinView getView() {
		return new ReadBinView(this.readingFile, this.readingFileDriver);
	}
	
	private void splitParams() {
		this.filePath = this.params[0];
		this.fileName = this.params[1];
	}

	private void setReadingFile() {
		this.readingFile = this.rinitClient.getFileService().getFileByPathAndName(this.filePath, this.fileName).get(0);
		try {
			this.readingFileDriver = this.modelFacade.getFileDriverModel().getCliFileDriverForExtention(readingFile);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
