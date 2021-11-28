package com.rinit.gui.clibin.upload;

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
import com.rinit.gui.model.ModelFacade;

public class UploadBinLogic extends UploadLogic {
	
	public UploadBinLogic(ModelFacade modelFacade) {
		super(modelFacade);
	}
	
	public void submit(UploadBinSubmitData submitData) {
		try {
			PhysicalFileDriver pfile = this.uploadPhysicalFile(submitData.name, submitData.jarFilePath);
			LibraryDriver library = this.createLibrary(submitData.name, "/lib/bin/", pfile);
			library.addClassToLoad(new ClassToLoadInfo(submitData.name, submitData.classPath));
			this.saveLibrary(library);
			this.createBin(library);
		} catch (LogicException e) {
			e.printStackTrace();
		}
	}
	
	public void createBin(LibraryDriver library) {
		BinDriver bin = new BinDriver();
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
