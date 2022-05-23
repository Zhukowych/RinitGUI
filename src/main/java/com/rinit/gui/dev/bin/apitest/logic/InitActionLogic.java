package com.rinit.gui.dev.bin.apitest.logic;

import java.nio.file.FileAlreadyExistsException;

import com.rinit.gui.AbstractCliBinLogic;
import com.rinit.gui.dev.drivers.apitestconfig.driver.ApiTestConfiigDriver;
import com.rinit.gui.dev.drivers.apitesttestsdir.ApiTestTestsDirectoryDriver;
import com.rinit.gui.model.FileOperationModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.panels.PanelsModel;

public class InitActionLogic extends AbstractCliBinLogic {

	public static final String API_TEST_FOLDER_NAME = ".apitest";
	public static final String OBJECTS_FOLDER_NAME = "objects";
	public static final String CHANGES_FOLDER_NAME = "changes";
	public static final String CONFIG_FILE_NAME = "config";
	public static final String TESTS_FOLDER_NAME = "tests";
	
	private String message;
	private String apiTestFolderPath;
	private PanelsModel panelsModel;
	private FileOperationModel fileOperationModel;
	
	public InitActionLogic(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.panelsModel = modelFacade.getPanelsModel();
		this.fileOperationModel = modelFacade.getFileOperationModel();
		
		this.apiTestFolderPath = this.fileOperationModel.joinPaths(this.panelsModel.getCurrentPath(), API_TEST_FOLDER_NAME);
		
		this.initializateApiTest();
	}
	
	public String getMessaage() {
		return this.message;
	}
	
	private void initializateApiTest() {
		this.createApiTestFolder();
		this.createQueriesFiolder();
		this.createApiTestConfigFile();	
		this.createChangesFolder();
		this.createTestsFolder();
	}
	
	private void createApiTestFolder() {
		try {
			this.fileOperationModel.mkDir(API_TEST_FOLDER_NAME);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}	
	}

	private void createQueriesFiolder() {
		try {
			this.fileOperationModel.mkDir(OBJECTS_FOLDER_NAME, this.apiTestFolderPath);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}	
	}
	
	private void createApiTestConfigFile() {
		ApiTestConfiigDriver apiTestConfig = new ApiTestConfiigDriver();
		apiTestConfig.setPath(this.apiTestFolderPath);
		apiTestConfig.setName(CONFIG_FILE_NAME);
		   
		try {
			this.fileOperationModel.mkFile(apiTestConfig);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}			
	}
	
	private void createChangesFolder() {
		try {
			this.fileOperationModel.mkDir(CHANGES_FOLDER_NAME, this.apiTestFolderPath);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}	
	}
	
	private void createTestsFolder() {
		try {
			ApiTestTestsDirectoryDriver apiTestTestsDirectory = new ApiTestTestsDirectoryDriver();
			apiTestTestsDirectory.setName("tests");
			apiTestTestsDirectory.setPath(this.panelsModel.getCurrentPath());
			this.fileOperationModel.mkFile(apiTestTestsDirectory);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}		
	}
		
}
