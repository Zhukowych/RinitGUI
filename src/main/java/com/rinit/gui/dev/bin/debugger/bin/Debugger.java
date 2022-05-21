package com.rinit.gui.dev.bin.debugger.bin;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.bin.debugger.bin.context.AbstractionContext;
import com.rinit.gui.dev.bin.debugger.bin.context.ModelContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestContext;
import com.rinit.gui.dev.bin.debugger.bin.context.ReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.RequestReportCallBack;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;
import com.rinit.gui.dev.drivers.debugreport.driver.DebugReportDriver;
import com.rinit.gui.dev.drivers.directory.DirectoryDriver;
import com.rinit.gui.exceptions.DriverNotFoundException;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.FileDriverModel;

public class Debugger implements Runnable {
	
	private ModelFacade modelFacade;
	private String runName;
	private RequestReportCallBack requestReportCallBack;
	
	private Stack<Deque<FileDTO>> fileStack = new Stack<Deque<FileDTO>>();
	private Stack<FileDTO> openedDirableFiles = new Stack<FileDTO>();
	
	private String initialPath;
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
	
	public void setRunName(String runName) {
		this.runName = runName;
	}
	
	@Override
	public void run() {
        this.runContext = this.createInitialRunContext();
		this.initialPath = this.modelFacade.getPanelsModel().getCurrentPath();
		this.moveToChildrenPaths(initialPath);
		this.doReport();
    }
	
	private void doReport() {
		DirectoryDriver dir = this.createReportDir();
		ReportContext reportContext = this.runContext.getContext(ReportContext.class);
		if (Thread.currentThread().isInterrupted())
			reportContext.addReport(this.createInterruptionContext());
		DebugReportDriver report = reportContext.getReportItem(); 
		report.setPath(dir.getChildrenPath());
		report.setName(String.format("report_%s", this.runName));
		try {
			this.fileServiceClient.saveFile(report);
		} catch (ServiceException e) {}
		this.modelFacade.getPanelsModel().reUpdatePanels();
	}
	
	private DirectoryDriver createReportDir() {
		DirectoryDriver dir = new DirectoryDriver();
		dir.setPath(this.initialPath);
		dir.setName("reports");
		try {
			this.fileServiceClient.createOrCheckFile(dir);
		} catch (ServiceException e) {e.printStackTrace();}
		return dir;
	}
	
	private ReportItem createInterruptionContext() {
		return new ReportItem("-", "", "-", "INTERRUPTED", "Test interrupted by user", "O ms");
	}
	
	private RunContext createInitialRunContext() {
		RunContext runContext = new RunContext();
		RequestContext requestContext = new RequestContext();
		ReportContext requestReportContext = new ReportContext(this.requestReportCallBack);
		AbstractionContext abstractionContext = new AbstractionContext();
		ModelContext modelContext = new ModelContext();
		modelContext.modelFacade = this.modelFacade;
		runContext.addContext(requestReportContext);
		runContext.addContext(requestContext);
		runContext.addContext(abstractionContext);
		runContext.addContext(modelContext);
		return runContext;
	}
	
	private void moveToChildrenPaths(String initialPath) {
		this.fileStack.push(this.getPathFilesDeque(this.fileServiceClient.getFilesByPath(initialPath)));
		while(!this.fileStack.empty() && !Thread.currentThread().isInterrupted()) {
			while(this.fileStack.peek().peek() != null && !Thread.currentThread().isInterrupted()) {
				this.processUpPathElement(this.fileStack.peek().pop());
			}
			if (this.fileStack.peek().isEmpty() && !Thread.interrupted()) 
				this.processDown();
		}
	}

	private void processUpPathElement(FileDTO dto) {
		AbstractDriver file = null;
		try {
			file = this.fileDriverModel.getDriverForFile(dto);
		} catch (DriverNotFoundException e) {}
		
		
		if (file != null)
			this.runDebuggerDriver(file);
		
		if (this.fileDriverModel.isExtentionDirable(dto.getExtention())) {
			this.fileStack.push(this.getPathFilesDeque(this.fileServiceClient.getFilesByPath(dto.getChildrenPath())));
			this.openedDirableFiles.push(dto);
		}
	}
	
	private void processDown(){
		this.fileStack.pop();
		if (this.openedDirableFiles.size()==0)
			return;
		
		AbstractDriver file = null;
		try {
			file = this.fileDriverModel.getDriverForFile(this.openedDirableFiles.pop());
		} catch (DriverNotFoundException e) {}
		
		if (file != null)
			this.outRunDebuggerDriver(file);
	}
	
	
	private void runDebuggerDriver(AbstractDriver file) {
		Class<?> debuggerFileClass = file.getClass();
		if (DebuggerDriver.class.isAssignableFrom(debuggerFileClass)) {
			DebuggerDriver debuggerDriver = (DebuggerDriver)file;
			debuggerDriver.run(this.runContext);
		}		
	}
	
	private void outRunDebuggerDriver(AbstractDriver file) {
		Class<?> debuggerFileClass = file.getClass();
		if (DebuggerDriver.class.isAssignableFrom(debuggerFileClass)) {
			DebuggerDriver debuggerDriver = (DebuggerDriver)file;
			debuggerDriver.outRun(this.runContext);
		}	
	}
	
	private Deque<FileDTO> getPathFilesDeque(List<FileDTO> pathFiles){
		Deque<FileDTO> pathFilesStack = new ArrayDeque<FileDTO>(pathFiles);
		return pathFilesStack;
	}
}
