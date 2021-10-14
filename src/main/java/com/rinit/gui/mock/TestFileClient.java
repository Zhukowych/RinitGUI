package com.rinit.gui.mock;

import java.util.List;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;

public class TestFileClient implements IFileService {
	
	private TestFileStructure fileStructure = new TestFileStructure();
	
	public void deleteFile(FileDTO dto) {
		// TODO Auto-generated method stub
		
	}

	public List<String> getAllChildrenDirs(String baseDir, String extention) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FileDTO> getFilesByPath(String path) {
		// TODO Auto-generated method stub
		return fileStructure.testFiles;
	}

	public List<FileDTO> getFilesByPathAndExtention(String path, String extention) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isFileExists(FileDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	public FileDTO createFile(FileDTO file) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public FileDTO createOrCheckFile(FileDTO dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAllChildrenOfPath(String path) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public FileDTO saveFile(FileDTO file) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
