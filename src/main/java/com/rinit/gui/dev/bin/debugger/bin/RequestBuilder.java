package com.rinit.gui.dev.bin.debugger.bin;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RequestBuilder {
	
	private String url;
	private String method;
	private String[][] getParameters;
	private String[][] postParamaters;
	
	private CloseableHttpResponse response;
	
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
	
	public CloseableHttpResponse getResponse() {
		return response;
	}

	public void doRequest(){
		HttpUriRequest request = this.createRequest();
		try {
			this.executeRequest(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private HttpUriRequest createRequest() {
		System.out.println(this.method);
		if (this.method.equals("GET")) {
			return this.createGetRequest();
		} else {
			
		}
		return null;
	}
	
	private HttpUriRequest createGetRequest() {
		HttpGet request = new HttpGet(this.url);
		return request;
		
	}
	
	private HttpUriRequest createPostRequest() {
		return null;
	}
	
	private void executeRequest(HttpUriRequest request) throws IOException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()){
			this.response = httpClient.execute(request);
		}
	}

		
}
