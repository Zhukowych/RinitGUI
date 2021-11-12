package com.rinit.gui.clibin.upload;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.file.library.LibraryDriver;
import com.rinit.debugger.server.file.pfille.PhysicalFileDriver;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.debugger.server.services.interfaces.ILibraryService;
import com.rinit.debugger.server.services.interfaces.IPhysicalFileService;
import com.rinit.debugger.server.utils.FileToBytesConverter;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.model.ModelFacade;

public class UploadLogic {

	protected ModelFacade modelFacade;
	
	public UploadLogic(ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	public PhysicalFileDriver uploadPhysicalFile(String name, String filePath) throws LogicException {
		RinitClient client = this.modelFacade.getRinitClientModel().getClient();
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
	
	public LibraryDriver createLibrary(String name, String path, PhysicalFileDriver pfile) {
		LibraryDriver library = new LibraryDriver();
		library.setName(name);
		library.setPath(path);
		library.setPhysicalFile(pfile);
		return library;
	}
	
	protected void saveLibrary(LibraryDriver library) {
		RinitClient client = this.modelFacade.getRinitClientModel().getClient();
		IFileService fileService = client.getFileService();
		
		try {
			fileService.createFile(library.toDTO());
		} catch (Exception ex) {
			FileDTO dto = fileService.getFileByPathAndName(library.getPath(), library.getName()).get(0);
			dto.cwrite(library.getContent());
			try {
				fileService.saveFile(dto);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	protected void updateFileDriverService() {
		RinitClient client = this.modelFacade.getRinitClientModel().getClient();
		ILibraryService libraryService = client.getLibraryServiceClient();
		libraryService.autodiscover();
	}
	
}
