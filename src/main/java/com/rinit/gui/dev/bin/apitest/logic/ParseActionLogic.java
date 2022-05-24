package com.rinit.gui.dev.bin.apitest.logic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.AbstractCliBinLogic;
import com.rinit.gui.dev.drivers.apitestconfig.driver.ApiTestConfiigDriver;
import com.rinit.gui.dev.drivers.apitestconfig.driver.FileToParse;
import com.rinit.gui.dev.drivers.change.driver.ChangeDriver;
import com.rinit.gui.dev.drivers.parsedobject.dirver.ParsedObjectDriver;
import com.rinit.gui.model.FileOperationModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.panels.PanelsModel;
import com.rinit.gui.utils.OSUtils;

public class ParseActionLogic extends AbstractCliBinLogic {
	
	private FileOperationModel fileOperationModel;
	private ApiTestConfiigDriver apiTestConfig;
	private PanelsModel panelsModel;
	
	private String apiTestFolderPath;
	private String parsedObjectsFolderPath;
	private String changesFolderPath;

	private Map<String, ParsedObjectDriver> parsedObjects = new HashMap<String, ParsedObjectDriver>();
	
	public ParseActionLogic(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.fileOperationModel = modelFacade.getFileOperationModel();	
		this.panelsModel = modelFacade.getPanelsModel();
		
		this.loadConfigFile();
	}
	
	public void parse(String initialPath) {
		this.loadExistingParsedObjects();
		this.parseDataFromInitialDir(initialPath);
		this.saveParsedObjects();
	}
	
	private void saveParsedObjects() {
		
		for (ParsedObjectDriver parsedObject : this.parsedObjects.values()) {
			parsedObject.setPath(this.parsedObjectsFolderPath);
			try {
				this.fileOperationModel.mkFile(parsedObject);
			} catch (FileAlreadyExistsException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void loadExistingParsedObjects() {
		List<ParsedObjectDriver> existingParsedObjects = this.fileOperationModel
				.getAllFilesByDirExtention(this.parsedObjectsFolderPath, ParsedObjectDriver.EXTENTION, ParsedObjectDriver.class);
		for (ParsedObjectDriver existingParsedObject : existingParsedObjects) 
			this.parsedObjects.put(existingParsedObject.getKey(), existingParsedObject);
		
	}
	
	private void parseDataFromInitialDir(String initialPath) {
		List<Path> subdirs = OSUtils.getAllSubDirsOf(initialPath);
		for (Path dir : subdirs) 
			this.parseDataFromDir(dir);
	}

	private void parseDataFromDir(Path dir) {
		List<File> filesInDir = Stream.of(new File(dir.toAbsolutePath().toString()).listFiles())
							      .filter(file -> !file.isDirectory())
							      .collect(Collectors.toList());
		
		for (File file : filesInDir) {
			for (FileToParse fileToParse : this.apiTestConfig.getFilesToParse()) {
				if(Pattern.matches(fileToParse.getFileNameRegex(), file.getName()))
					this.parseFile(fileToParse, file);
			}
		}
	
	}
	
	private void parseFile(FileToParse fileToParse, File file) {
		String fileContent = null;
		try {
			fileContent = Files.readString(file.toPath(), StandardCharsets.US_ASCII);
		} catch (IOException e) { e.printStackTrace(); }	
		
		List<String> keyMatches = new ArrayList<String>();
		List<String> valueMatches = new ArrayList<String>();
		
		Matcher keyMatcher = Pattern.compile(fileToParse.getKeyRegex(), Pattern.MULTILINE).matcher(fileContent);
		Matcher valueMatcher = Pattern.compile(fileToParse.getValueRegex(), Pattern.MULTILINE).matcher(fileContent);
		
		while (keyMatcher.find()) 
			keyMatches.add(keyMatcher.group(keyMatcher.groupCount()));
		
		while (valueMatcher.find())
			valueMatches.add(valueMatcher.group(valueMatcher.groupCount()));
	
		for (int i=0; i<keyMatches.size(); i++) 
			this.addParsedObject(keyMatches.get(i), valueMatches.get(i), fileToParse);
		
	}
	
	private void addParsedObject(String key, String value, FileToParse fileToParse) {
		if (this.parsedObjects.get(key) == null) {
			this.parsedObjects.put(key, new ParsedObjectDriver(key));
			this.createNewParsedObjectMessage(this.parsedObjects.get(key));
		}
		
		if (this.parsedObjects.get(key).getValue(fileToParse.getName()) != null 
				&& !this.parsedObjects.get(key).getValue(fileToParse.getName()).equals(value))
			this.createUpdatedParsedObjectsValue(this.parsedObjects.get(key), fileToParse.getName(), value);
			
		this.parsedObjects.get(key).addValue(fileToParse.getName(), value);
	}
	
	private void createNewParsedObjectMessage(ParsedObjectDriver newParsedObject) {
		ChangeDriver change = new ChangeDriver();
		change.setMessage(String.format("Parsed new object with key %s", newParsedObject.getKey()));
		change.setChangedObjectPath(newParsedObject.getKey());
		this.saveChange(change);
	}
	
	private void createUpdatedParsedObjectsValue(ParsedObjectDriver changedParsedObject, String changedValueType, String newValue) {
		ChangeDriver change = new ChangeDriver();
		change.setMessage(String.format("In object with key %s changes value %s", changedParsedObject.getKey(), changedValueType));
		change.setPreviousValue(changedParsedObject.getValue(changedValueType));
		change.setCurrentValue(newValue);	
		change.setChangedObjectPath(changedParsedObject.getKey());
		this.saveChange(change);
	}
	
	private void saveChange(ChangeDriver change) {
		change.setPath(this.changesFolderPath);
		try {
			this.fileOperationModel.mkFile(change);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
	
	private void loadConfigFile() {
		this.apiTestFolderPath = this.fileOperationModel.joinPaths(
			this.panelsModel.getCurrentPath(),
			InitActionLogic.API_TEST_FOLDER_NAME
		);
		this.parsedObjectsFolderPath = this.fileOperationModel.joinPaths(this.apiTestFolderPath, InitActionLogic.OBJECTS_FOLDER_NAME);
		this.changesFolderPath = this.fileOperationModel.joinPaths(this.apiTestFolderPath, InitActionLogic.CHANGES_FOLDER_NAME);
		
		FileDTO configFileDTO = this.fileOperationModel.getFilWithPathAndName(this.apiTestFolderPath, InitActionLogic.CONFIG_FILE_NAME);
		this.apiTestConfig = new ApiTestConfiigDriver();
		this.apiTestConfig.fromDTO(configFileDTO);
	}
	
}
