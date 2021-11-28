package com.rinit.gui.dev.drivers.request.driver;

import com.rinit.debugger.server.utils.XMLReader;

public class RequestImporter {
	
	private RequestDriver file;
	private XMLReader reaader;
	
	public RequestImporter(RequestDriver file) {
		this.file = file;
		this.reaader = new XMLReader(this.file.getDto().getContent());
	}
	
	public void parse() {
		if (!this.reaader.isOk())
			return;
		this.file.setProtocol(this.reaader.getTagValueByName("protocol", "request"));
		this.file.setAdress(this.reaader.getTagValueByName("adress", "request"));
		this.file.setUrlPath(this.reaader.getTagValueByName("path", "request"));
	}
	
}
