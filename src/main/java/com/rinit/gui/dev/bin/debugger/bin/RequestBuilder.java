package com.rinit.gui.dev.bin.debugger.bin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

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
		if (this.method.equals("GET")) {
			return this.createGetRequest();
		} else {
			return this.createPostRequest();
		}
	}
	
	private HttpUriRequest createGetRequest() {
		HttpGet request = new HttpGet(this.createURI());
		return request;
	}
	
	private HttpUriRequest createPostRequest() {
		HttpPost httpPost = new HttpPost(this.createURI());
		this.addPostParameters(httpPost);
		return httpPost;
	}
	
	private void addPostParameters(HttpPost httpPost) {
		if (this.postParamaters == null)
			return;
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		for (String[] keyValue : this.postParamaters)
			postParams.add(new BasicNameValuePair(keyValue[0], keyValue[1]));
		httpPost.setEntity(new UrlEncodedFormEntity(postParams, StandardCharsets.UTF_8));		
	}
	
	private URI createURI() {
		if (this.getParameters == null)
			try {
				return new URI(this.url);
			} catch (URISyntaxException e1) {e1.printStackTrace();}
		
		URI uri = null;
		try {
			URIBuilder builder = new URIBuilder(this.url);
			for(String[] keyValue : this.getParameters)
				builder.addParameter(keyValue[0], keyValue[1]);
			uri = builder.build();
		} catch (URISyntaxException e) {e.printStackTrace();}
		return uri;
	}
	
	private void executeRequest(HttpUriRequest request) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		this.response = httpClient.execute(request);
	}

		
}
