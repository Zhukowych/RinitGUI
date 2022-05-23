package com.rinit.gui.dev.bin.apitest.logic;

import java.util.List;

import com.rinit.gui.AbstractCliBinLogic;
import com.rinit.gui.dev.drivers.change.driver.ChangeDriver;
import com.rinit.gui.model.FileOperationModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.panels.PanelsModel;

public class ChangesActionLogic extends AbstractCliBinLogic {

	private PanelsModel panelsModel;
	private FileOperationModel fileOperationModel;
	
	private String changesFolderPath;
	
	private List<ChangeDriver> changes;
	
	public ChangesActionLogic(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.panelsModel = this.modelFacade.getPanelsModel();
		this.fileOperationModel = this.modelFacade.getFileOperationModel();
		
		this.changesFolderPath = this.fileOperationModel.joinPaths(
			this.panelsModel.getCurrentPath(),
			InitActionLogic.API_TEST_FOLDER_NAME,
			InitActionLogic.CHANGES_FOLDER_NAME
		);
		
	}
	
	public List<ChangeDriver> getChanges(){
		this.changes = this.fileOperationModel.getAllFilesByDirExtention(changesFolderPath, "change", ChangeDriver.class);
		return this.changes;
	}
	
	public ChangeDriver getChangeAt(int ind) {
		return this.changes.get(ind);
	}
	
	public void deleteChangeAtInd(int ind) {
		this.fileOperationModel.deleteFile(this.changes.get(ind));
		this.changes.remove(ind);
	}
	
}
