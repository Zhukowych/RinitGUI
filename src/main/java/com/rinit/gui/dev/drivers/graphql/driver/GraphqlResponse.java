package com.rinit.gui.dev.drivers.graphql.driver;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GraphqlResponse {
	
	public Map<String, Object> jsonMap = new HashMap<String, Object>();
	
	public GraphqlResponse(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.jsonMap = objectMapper.readValue(json, HashMap.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	
	
}
