package com.rinit.gui.dev.bin.debugger.bin.context;

import java.util.Stack;

import com.rinit.gui.dev.bin.debugger.bin.RequestBuilder;

public class RequestContext {
	
	private Stack<RequestBuilder> requests = new Stack<RequestBuilder>();
	
	public RequestBuilder createNewRequest() {
		this.requests.push(new RequestBuilder());
		return this.requests.peek(); 
	}
	
	public RequestBuilder peekRequest() {
		return this.requests.peek();
	}
	
	public RequestBuilder popRequest() {
		return this.requests.pop();
	}
	
}
