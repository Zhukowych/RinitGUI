package com.rinit.gui.dev.drivers.apitestconfig.driver;

import com.rinit.debugger.server.file.AbstractDriver;

public class ApiTestConfiigDriver extends AbstractDriver {

	public static final String NAME = "apitest.config";
	
	private String queryFindFolderPath;
	private String queryFileName;
	private String variablesFileName;
	
	public ApiTestConfiigDriver() {
		this.setExtention(NAME);
	}
	
	@Override
	protected void buildFromDTO() {
		ApiTestConfigImporter importer = new ApiTestConfigImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		ApiTestConfigExporter exporeter = new ApiTestConfigExporter(this);
		return exporeter.export();
	}

	public String getQueryFindFolderPath() {
		return queryFindFolderPath;
	}

	public void setQueryFindFolderPath(String queryFindFolderPath) {
		this.queryFindFolderPath = queryFindFolderPath;
	}

	public String getQueryFileName() {
		return queryFileName;
	}

	public void setQueryFileName(String queryFileName) {
		this.queryFileName = queryFileName;
	}

	public String getVariablesFileName() {
		return variablesFileName;
	}

	public void setVariablesFileName(String variablesFileName) {
		this.variablesFileName = variablesFileName;
	}
	
}
