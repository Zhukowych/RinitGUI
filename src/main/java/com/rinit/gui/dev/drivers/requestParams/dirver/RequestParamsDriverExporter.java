package com.rinit.gui.dev.drivers.requestParams.dirver;

import com.rinit.gui.utils.XMLBuilder;

public class RequestParamsDriverExporter {
	
	private RequestParamsDriver file;
	private String[] paramCols = new String[]{"key", "value"};
	
	public RequestParamsDriverExporter(RequestParamsDriver file) {
		this.file = file;
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("requestParams", builder.addGroup(
					builder.addTag("method", this.file.getMethod()),
					builder.addTag("getParams", builder.addTable("param", paramCols, this.file.getGetParams())),
					builder.addTag("postParams", builder.addTable("param", paramCols, this.file.getPostParams()))
				)
			);
	}

}
