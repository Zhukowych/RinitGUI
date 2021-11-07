package com.rinit.gui.dev.drivers.request;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.utils.XMLReader;

public class RequestImporter {
	
	private RequestDriver file;
	private XMLReader reaader;
	
	public RequestImporter(RequestDriver file, FileDTO dto) {
		this.file = file;
		this.reaader = new XMLReader(dto.getContent());
	}
	
	public void parse() {
		this.file.setProtocol(this.reaader.getTagValueByName("protocol", "request"));
		this.file.setAdress(this.reaader.getTagValueByName("adress", "request"));
		this.file.setPath(this.reaader.getTagValueByName("path", "request"));
	}
	
}
