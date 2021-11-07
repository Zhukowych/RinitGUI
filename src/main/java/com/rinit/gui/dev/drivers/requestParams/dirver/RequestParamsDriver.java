package com.rinit.gui.dev.drivers.requestParams.dirver;

import com.rinit.debugger.server.file.AbstractDriver;

public class RequestParamsDriver extends AbstractDriver {

	private String method;
	private String[][] getParams;
	private String[][] postParams;
	
	@Override
	protected void buildFromDTO() {
		RequestParamsDriverImporter importer = new RequestParamsDriverImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		RequestParamsDriverExporter exporter = new RequestParamsDriverExporter(this);
		return exporter.export();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String[][] getGetParams() {
		return getParams;
	}

	public void setGetParams(String[][] getParams) {
		this.getParams = getParams;
	}

	public String[][] getPostParams() {
		return postParams;
	}

	public void setPostParams(String[][] postParams) {
		this.postParams = postParams;
	}

}
