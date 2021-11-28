package com.rinit.gui.dev.drivers.request.driver;

import com.rinit.gui.utils.XMLBuilder;

public class RequestExporter {
	
	private RequestDriver file;
	
	public RequestExporter(RequestDriver file) {
		this.file = file;
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("request", 
					builder.addGroup(
					    builder.addTag("protocol", file.getProtocol()),
					    builder.addTag("adress", file.getAdress()),
					    builder.addTag("path", file.getUrlPath())
					)
				);
	}
	
}
