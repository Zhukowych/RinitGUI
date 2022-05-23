package com.rinit.gui.dev.drivers.apitestconfig.driver;

import java.util.ArrayList;
import java.util.List;

import com.rinit.debugger.server.file.AbstractDriver;

public class ApiTestConfiigDriver extends AbstractDriver {

	public static final String NAME = "apitest.config";
	
	private String rootParseFolderPath;
	
	private List<FileToParse> filesToParse = new ArrayList<FileToParse>();
	
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

	public String getRootParseFolderPath() {
		return rootParseFolderPath;
	}

	public void setRootParseFolderPath(String rootParseFolderPath) {
		this.rootParseFolderPath = rootParseFolderPath;
	}

	public List<FileToParse> getFilesToParse() {
		return filesToParse;
	}

	public void setFilesToParse(List<FileToParse> filesToParse) {
		this.filesToParse = filesToParse;
	}

	public void addFileToParse(FileToParse fileToParse) {
		this.filesToParse.add(fileToParse);
	}
	
}
