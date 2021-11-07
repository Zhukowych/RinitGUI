package com.rinit.gui.dev.drivers.requestParams;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.drivers.requestParams.dirver.RequestParamsDriver;
import com.rinit.gui.model.ModelFacade;

public class RequestParamsCliDriverLogic {

	private IFileService fileService;
	
	private FileDTO readingFile;
	private RequestParamsDriver file = new RequestParamsDriver();
	
	public RequestParamsCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		this.fileService = modelFacade.getRinitClientModel().getClient().getFileService();
		this.readingFile = readingFile;
		this.file.fromDTO(readingFile);
	}

	public RequestParamsSubmitData getInitialData() {
		RequestParamsSubmitData initialData = new RequestParamsSubmitData();
		initialData.method = this.file.getMethod();
		initialData.getParams = this.file.getGetParams();
		initialData.postParams = this.file.getPostParams();
		return initialData;
	}
	
	public void submit(RequestParamsSubmitData submitData) {
		RequestParamsDriver file = createRequestParamsFile(submitData);
		this.saveFile(file);
	}
	
	private void saveFile(RequestParamsDriver file) {
		try {
			this.fileService.saveFile(file);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	private RequestParamsDriver createRequestParamsFile(RequestParamsSubmitData submitData) {
		RequestParamsDriver requestParams = new RequestParamsDriver();
		requestParams.fromDTO(this.readingFile);
		requestParams.setMethod(submitData.method);
		requestParams.setGetParams(submitData.getParams);
		requestParams.setPostParams(submitData.postParams);
		return requestParams;
	}
	
}
