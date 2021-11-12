package com.rinit.gui.dev.bin.debugger.bin;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.exceptions.DriverNotFoundException;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.FileDriverModel;

public class Debugger {
	
	private ModelFacade modelFacade;
	private RequestReportCallBack requestReportCallBack;
	private Stack<Deque<FileDTO>> fileStack = new Stack<Deque<FileDTO>>();
	private FileDriverModel fileDriverModel;
	private IFileService fileServiceClient;
	private RunContext runContext;
	
	
	public Debugger(ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
		this.fileDriverModel = this.modelFacade.getFileDriverModel();
		this.fileServiceClient = this.modelFacade.getRinitClientModel().getClient().getFileService();
	}
	
	public void setRequestReportCallBack(RequestReportCallBack requestReportCallBack) {
		this.requestReportCallBack = requestReportCallBack;
	}
	
	public void run() {
		this.runContext = this.createInitialRunContext();
		String initialPath = this.modelFacade.getPanelsModel().getCurrentPath();
		this.moveToChildrenPaths(initialPath);
	}
	
	private RunContext createInitialRunContext() {
		RunContext runContext = new RunContext();
		RequestContext requestContext = new RequestContext();
		RequestReportContext requestReportContext = new RequestReportContext(this.requestReportCallBack);
		runContext.addContext(requestReportContext);
		runContext.addContext(requestContext);
		return runContext;
	}
	
	private void moveToChildrenPaths(String initialPath) {
		this.fileStack.push(this.getPathFilesDeque(this.fileServiceClient.getFilesByPath(initialPath)));
		while(!this.fileStack.empty()) {
			while(this.fileStack.peek().peek() != null) {
				this.processPathElement(this.fileStack.peek().pop());
			}
			if (this.fileStack.peek().isEmpty())
				this.fileStack.pop();
		}
	}

	private void processPathElement(FileDTO dto) {
		AbstractDriver file = null;
		try {
			file = this.fileDriverModel.getDriverForFile(dto);
		} catch (DriverNotFoundException e) {}
		
		
		if (file != null)
			this.runDebuggerDriver(file);
		
		if (this.fileDriverModel.isExtentionDirable(dto.getExtention())) {
			this.fileStack.push(this.getPathFilesDeque(this.fileServiceClient.getFilesByPath(dto.getChildrenPath())));
		}
			
	}
	
	private void runDebuggerDriver(AbstractDriver file) {
		Class<?> debuggerFileClass = file.getClass();
		if (DebuggerDriver.class.isAssignableFrom(debuggerFileClass)) {
			DebuggerDriver debuggerDriver = (DebuggerDriver)file;
			debuggerDriver.run(this.runContext);
		}
		
	}
	
	private Deque<FileDTO> getPathFilesDeque(List<FileDTO> pathFiles){
		Deque<FileDTO> pathFilesStack = new ArrayDeque<FileDTO>(pathFiles);
		return pathFilesStack;
	}
}
