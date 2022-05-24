package com.rinit.gui.dev.drivers.variables.driver;

import java.util.HashMap;
import java.util.Map;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;

public class VariablesDriver extends AbstractDriver implements DebuggerDriver {

	public static final String EXTENTION = "variables";

	private Map<String, String> values = new HashMap<String, String>();
	
	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}
	
	public String getVariable(String variableName) {
		return this.values.get(variableName);
	}
	
	public void setVariable(String variableName, String variableValue) {
		this.values.put(variableName, variableValue);
	}
 
	@Override
	public void run(RunContext context) {
		VariablesContext variablesContext = context.getContext(VariablesContext.class);
		variablesContext.addVariables(this);
	}

	@Override
	public void outRun(RunContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buildFromDTO() {
		VariablesImporter importer = new VariablesImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		VariablesExporter exporter = new VariablesExporter(this);		
		return exporter.export();
	}

}
