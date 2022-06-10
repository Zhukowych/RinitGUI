package com.rinit.gui.clibin.upload;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.debugger.server.core.Extentions;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.file.bin.BinDriver;
import com.rinit.debugger.server.file.library.ClassToLoadInfo;
import com.rinit.debugger.server.file.library.LibraryDriver;
import com.rinit.debugger.server.file.pfille.PhysicalFileDriver;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.model.BinModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.utils.ReflectionUtils;

public class UploadBinLogic extends UploadLogic {
	
	private BinModel binModel;
	
	public UploadBinLogic(ModelFacade modelFacade) {
		super(modelFacade);
		this.binModel = modelFacade.getBinModel();
	}
	
	public void submit(UploadBinSubmitData submitData) throws Exception {
		try {
			Set<String> classesInJar =  ReflectionUtils.getClassNamesFromJarFile(new File(submitData.jarFilePath));
			if (!classesInJar.contains(submitData.classPath))
				throw new Exception(String.format("File %s has no class %s", submitData.jarFilePath, submitData.classPath));
			
			
			PhysicalFileDriver pfile = this.uploadPhysicalFile(submitData.name, submitData.jarFilePath);
			LibraryDriver library = this.createLibrary(submitData.name, "/lib/bin/", pfile);
			library.addClassToLoad(new ClassToLoadInfo(submitData.name, submitData.classPath));
			this.saveLibrary(library);
			this.createBin(library, submitData.name);
			this.binModel.reloadRemoveBins();
		} catch (LogicException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createBin(LibraryDriver library, String name) {
		BinDriver bin = new BinDriver();
		bin.setName(name);
		bin.setBinLibraryName(library.getName());
		bin.setBinLibraryPath(library.getPath());
		FileDTO dto = bin.toDTO();
		dto.setExtention(Extentions.BIN);
		dto.setPath("/bin/");
		dto.setName(library.getName());
		RinitClient client = this.modelFacade.getRinitClientModel().getClient();
		IFileService fileService = client.getFileService();
		try {
			fileService.saveFile(dto);
		} catch (ServiceException e) {e.printStackTrace();}
	}
	
}
