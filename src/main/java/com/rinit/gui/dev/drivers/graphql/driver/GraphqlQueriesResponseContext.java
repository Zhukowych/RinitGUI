package com.rinit.gui.dev.drivers.graphql.driver;

import java.util.Stack;

import com.rinit.gui.dev.bin.debugger.bin.RequestBuilder;

public class GraphqlQueriesResponseContext {
	
	private Stack<GraphqlResponse> responses = new Stack<GraphqlResponse>();

	public GraphqlResponse createNewRequest(GraphqlResponse response) {
		this.responses.push(response);
		return response; 
	}
	
	public GraphqlResponse peekRequest() {
		return this.responses.peek();
	}
	
	public GraphqlResponse popRequest() {
		return this.responses.pop();
	}
	
}
