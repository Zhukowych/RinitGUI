package com.rinit.gui.dev.drivers.requestParams;

public class RequestParamsSubmitData {
	
	
	public String method;
	public String[][] getParams;
	public String[][] postParams;
	
	public RequestParamsSubmitData() {}
	
	public RequestParamsSubmitData(String method, String[][] getParams, String[][] postParams) {
		this.method = method;
		this.getParams = getParams;
		this.postParams = postParams;
	}
	
}
