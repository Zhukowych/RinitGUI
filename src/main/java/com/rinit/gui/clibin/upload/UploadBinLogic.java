package com.rinit.gui.clibin.upload;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.file.library.ClassToLoadInfo;
import com.rinit.debugger.server.file.library.LibraryDriver;
import com.rinit.debugger.server.file.pfille.PhysicalFileDriver;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.debugger.server.services.interfaces.IPhysicalFileService;
import com.rinit.debugger.server.utils.FileToBytesConverter;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.model.ModelFacade;

public class UploadBinLogic {
	
	private static ModelFacade modelFacade;
	
	public static void setModelFacade(ModelFacade modelFacade) {
		UploadBinLogic.modelFacade = modelFacade;
	}
	
	public static PhysicalFileDriver uploadPhysicalFile(String name, String filePath) throws LogicException {
		RinitClient client = modelFacade.getRinitClientModel().getClient();
		IPhysicalFileService physicalFileService = client.getPhysicalServiceClient();
    	name = String.format("%s.%s", name, filePath.split("\\.")[filePath.split("\\.").length-1]);
		MockMultipartFile file = new MockMultipartFile(name, name,  MediaType.TEXT_PLAIN_VALUE, new FileToBytesConverter(filePath).getBytes());
    	PhysicalFileDriver pfile = PhysicalFileDriver.builder().name(name).build();
    	try {
			return physicalFileService.uploadFile(pfile, file);
		} catch (ServiceException e) {
			throw new LogicException(e.getMessage());
		}
	}
	
	public static void uploadLibrary(LibraryDriver libraryDriver, String path) throws LogicException {
		RinitClient client = modelFacade.getRinitClientModel().getClient();
		IFileService fileServiceClient = client.getFileService();
		try {
			FileDTO dto = libraryDriver.toDTO();
			dto.setPath(path);
			fileServiceClient.createFile(dto);
		} catch (ServiceException e) {
			throw new LogicException(e.getMessage());
		}
		
	}
	
	public static void uploadDriver(String driverName, String driverClassPath, String driverCliClassPath, String filePath) throws LogicException {
		PhysicalFileDriver pfile = UploadBinLogic.uploadPhysicalFile(driverName, filePath);
		LibraryDriver library = new LibraryDriver();
		library.setName(driverName);
		library.setPhysicalFile(pfile);
		library.addClassToLoad(new ClassToLoadInfo(driverName, driverClassPath));
		library.addClassToLoad(new ClassToLoadInfo(driverName+"_cli", driverCliClassPath));
		UploadBinLogic.uploadLibrary(library, "/lib/ext/");
	}
	
	
}
