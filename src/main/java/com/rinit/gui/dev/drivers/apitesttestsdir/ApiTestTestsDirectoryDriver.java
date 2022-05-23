package com.rinit.gui.dev.drivers.apitesttestsdir;

import java.util.List;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.apitest.logic.InitActionLogic;
import com.rinit.gui.dev.bin.debugger.bin.context.ModelContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;
import com.rinit.gui.dev.drivers.parsedobject.dirver.ParsedObjectDriver;
import com.rinit.gui.model.FileOperationModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.panels.PanelsModel;

public class ApiTestTestsDirectoryDriver extends AbstractDriver implements DebuggerDriver {

	public static final String EXTENTION = ApiTestTestsDirectoryCliDriver.NAME;
	
	@Override
	protected void buildFromDTO(){}

	@Override
	public String buildContent() { 
		this.setExtention(EXTENTION);
		return ""; 
	}

	@Override
	public void run(RunContext context) {
		List<ParsedObjectDriver> parsedObjects = this.getAllParsedObjects(context);
		ParsedObjectsContext parsedObjectsContext = new ParsedObjectsContext();
		parsedObjectsContext.addAll(parsedObjects);
	}

	@Override
	public void outRun(RunContext context) {}
	
	private List<ParsedObjectDriver> getAllParsedObjects(RunContext context){
		ModelFacade modelFacade = context.getContext(ModelContext.class).getModelFacade();
		FileOperationModel fileOperationModel = modelFacade.getFileOperationModel();
		PanelsModel panelsModel = modelFacade.getPanelsModel();
		String parsedObjectsDirPath = fileOperationModel.joinPaths(panelsModel.getCurrentPath(), InitActionLogic.OBJECTS_FOLDER_NAME);
		return fileOperationModel.getAllFilesByDirExtention(parsedObjectsDirPath, ParsedObjectDriver.EXTENTION, ParsedObjectDriver.class);
	}

}
