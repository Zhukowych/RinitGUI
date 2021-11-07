package com.rinit.gui.dev.drivers.request;

public class RequestSubmitData {
	
	public String protocol;
	public String adress; 
	public String path;
	
	public RequestSubmitData(String protocol, String adress, String path) {
		this.protocol = protocol;
		this.adress = adress;
		this.path = path;
	}
}
