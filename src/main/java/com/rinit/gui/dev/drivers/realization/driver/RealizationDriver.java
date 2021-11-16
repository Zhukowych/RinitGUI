package com.rinit.gui.dev.drivers.realization.driver;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.bin.debugger.bin.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.context.AbstractionContext;
import com.rinit.gui.dev.bin.debugger.bin.context.ModelContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.exceptions.DriverNotFoundException;

public class RealizationDriver extends AbstractDriver implements DebuggerDriver {

	private String realizationExtention;
	
	@Override
	protected void buildFromDTO() {
		RealizationDriverImporter importer = new RealizationDriverImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		RealizationDriverExporter exporter = new RealizationDriverExporter(this);
		return exporter.export();
	}

	public String getRealizationExtention() {
		return realizationExtention;
	}

	public void setRealizationExtention(String realizationExtention) {
		this.realizationExtention = realizationExtention;
	}

	@Override
	public void run(RunContext context) {
		AbstractDriver realization = this.getRealizationDriver(context);
		this.addRealization(context, realization);
	}

	@Override
	public void outRun(RunContext context) {
		// TODO Auto-generated method stub
		
	}
	
	private void addRealization(RunContext context, AbstractDriver realization) {
		context.getContext(AbstractionContext.class).addRealization(this.getName(), realization);
	}

	private AbstractDriver getRealizationDriver(RunContext context) {
		FileDTO realizationFile = this.getRealizationFile(context);
		try {
			return context.getContext(ModelContext.class).modelFacade.getFileDriverModel().getDriver(realizationFile);
		} catch (DriverNotFoundException e) {e.printStackTrace();}
		return null;
	}
	
	private FileDTO getRealizationFile(RunContext context) {
		IFileService fileService = context.getContext(ModelContext.class).getFileService();
		return fileService.getFilesByParentPathExtention(this.getChildrenPath(), this.getRealizationExtention()).get(0);
	}
	
	
}
