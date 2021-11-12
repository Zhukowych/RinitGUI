package com.rinit.gui.dev.drivers.requestParams;

public class RequestParamsSubmitData {
	
	
	public String method;
	public String[][] getParams = new String[0][0];
	public String[][] postParams = new String[0][0];
	
	public RequestParamsSubmitData() {}
	
	public RequestParamsSubmitData(String method, String[][] getParams, String[][] postParams) {
		this.method = method;
		this.getParams = getParams;
		this.postParams = postParams;
	}
	
}
