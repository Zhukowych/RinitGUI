package com.rinit.gui.dev.bin.debugger.bin;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.rinit.debugger.server.utils.CollectionUtils;

public class RequestBuilder {
	
	private String url;
	private String method;
	private String[][] getParameters;
	private String[][] postParamaters;
	
	private HttpsURLConnection connection;
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public void setGetParameters(String[][] getParameters) {
		this.getParameters = getParameters;
	}

	public void setPostParamaters(String[][] postParamaters) {
		this.postParamaters = postParamaters;
	}
	
	public void doRequest() throws IOException {
		URL url = new URL(this.url);
		this.connection = (HttpsURLConnection) url.openConnection();
		this.connection.setRequestMethod(this.method);
		
	}

	private void addRequestParameters() {
		CollectionUtils.ndArrayToMap(this.postParamaters);
		this.connection.setDoInput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();
	}
	
	private String paramsToString(Map<String> string) {
		StringBuilder bui;
	}
	
}
