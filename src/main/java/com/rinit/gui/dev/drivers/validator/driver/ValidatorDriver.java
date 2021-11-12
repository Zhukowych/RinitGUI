package com.rinit.gui.dev.drivers.validator.driver;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.debugger.bin.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.RunContext;

public class ValidatorDriver extends AbstractDriver implements DebuggerDriver {

	private int requiredHttpCode;
	
	@Override
	protected void buildFromDTO() {
		if (this.getContent().isEmpty())
			return;
		ValidatorDriverImporter importer = new ValidatorDriverImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		ValidatorDriverExporter exporter = new ValidatorDriverExporter(this);
		return exporter.export();
	}

	public int getRequiredHttpCode() {
		return requiredHttpCode;
	}

	public void setRequiredHttpCode(int requiredHttpCode) {
		this.requiredHttpCode = requiredHttpCode;
	}

	@Override
	public void run(RunContext context) {
		// TODO Auto-generated method stub
		
	}

}
