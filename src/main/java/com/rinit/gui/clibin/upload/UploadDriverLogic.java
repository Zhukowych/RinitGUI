package com.rinit.gui.clibin.upload;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.rinit.debugger.server.file.library.ClassToLoadInfo;
import com.rinit.debugger.server.file.library.LibraryDriver;
import com.rinit.debugger.server.file.pfille.PhysicalFileDriver;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.utils.ReflectionUtils;

public class UploadDriverLogic extends UploadLogic {

	public UploadDriverLogic(ModelFacade modelFacade) {
		super(modelFacade);
	}
	
	public void uploadDriver(UploadDriverSubmitData submitData) throws Exception {
		try {
			Set<String> classesInJar =  ReflectionUtils.getClassNamesFromJarFile(new File(submitData.jarFilePath));
			if (!classesInJar.contains(submitData.classPath))
				throw new Exception(String.format("File %s has no class %s", submitData.jarFilePath, submitData.classPath));
			
			
			PhysicalFileDriver pfile = this.uploadPhysicalFile(submitData.extention, submitData.jarFilePath);
			LibraryDriver library = this.createLibrary(submitData.extention, "/lib/ext/", pfile);
			library.addClassToLoad(new ClassToLoadInfo(submitData.extention, submitData.classPath));
			this.saveLibrary(library);
		} catch (LogicException | IOException e) {
			e.printStackTrace();
		}
	}

	

}