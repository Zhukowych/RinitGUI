package com.rinit.gui.dev.bin.debugger.bin.context;

import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.model.ModelFacade;

public class ModelContext {

	public ModelFacade modelFacade;
	
	public IFileService getFileService() {
		return this.modelFacade.getRinitClientModel().getClient().getFileService();
	}
	
}
