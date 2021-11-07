package com.rinit.gui.dev.drivers.requestParams.dirver;

import com.rinit.debugger.server.utils.XMLReader;

public class RequestParamsDriverImporter {

	private XMLReader reaader;
	private RequestParamsDriver file;

	public RequestParamsDriverImporter(RequestParamsDriver file) {
		this.file = file;
		this.reaader = new XMLReader(file.getContent());

	}
	
	public void parse() {
		this.file.setMethod(this.reaader.getTagValueByName("method", "requestParams"));
		this.file.setGetParams(this.reaader.getTableData("getParams", new String[] {"key", "value"}));
		this.file.setPostParams(this.reaader.getTableData("postParams", new String[] {"key", "value"}));
	}
	
}
