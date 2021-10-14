package com.rinit.gui.mock;

import java.util.ArrayList;
import java.util.List;

import com.rinit.debugger.server.dto.FileDTO;

public class TestFileStructure {
	
	public List<FileDTO> testFiles = new ArrayList<FileDTO>();
	
	public TestFileStructure() {
		FileDTO testFile = FileDTO.builder().name("werwer").build();
		for (int i=0; i<20; i++)
			this.testFiles.add(testFile);
	}
	
}
