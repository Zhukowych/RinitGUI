package com.rinit.gui.clibin.mkdir;

import com.rinit.debugger.server.core.Extentions;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.panels.PanelsModel;

public class MkDirBinLogic {

	private ModelFacade modelFacade;
	private IFileService fileService;
	private PanelsModel panelsModel;
	
	public MkDirBinLogic(ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
		this.fileService = this.modelFacade.getRinitClientModel().getClient().getFileService();
		this.panelsModel = this.modelFacade.getPanelsModel();
	}
	
	public void submit(String dirName) {
		FileDTO dirDto = FileDTO.builder().name(dirName).extention(Extentions.DIRECTORY).path(this.panelsModel.getCurrentPath()).build();
		try {
			this.fileService.createFile(dirDto);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.panelsModel.reUpdatePanels();
	}
		
}
