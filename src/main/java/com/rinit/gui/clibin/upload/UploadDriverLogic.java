package com.rinit.gui.clibin.upload;

import com.rinit.debugger.server.file.library.ClassToLoadInfo;
import com.rinit.debugger.server.file.library.LibraryDriver;
import com.rinit.debugger.server.file.pfille.PhysicalFileDriver;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.model.ModelFacade;

public class UploadDriverLogic extends UploadLogic {

	public UploadDriverLogic(ModelFacade modelFacade) {
		super(modelFacade);
	}
	
	public void uploadDriver(UploadDriverSubmitData submitData) {
		try {
			PhysicalFileDriver pfile = this.uploadPhysicalFile(submitData.extention, submitData.jarFilePath);
			LibraryDriver library = this.createLibrary(submitData.extention, "/lib/ext/", pfile);
			library.addClassToLoad(new ClassToLoadInfo(submitData.extention, submitData.classPath));
			this.saveLibrary(library);
		} catch (LogicException e) {
			e.printStackTrace();
		}
	}

	

}