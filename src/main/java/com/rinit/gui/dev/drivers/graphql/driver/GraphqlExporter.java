package com.rinit.gui.dev.drivers.graphql.driver;

import com.rinit.gui.utils.XMLBuilder;

public class GraphqlExporter {
	
	private GraphqlDriver file;
	
	public GraphqlExporter(GraphqlDriver file) {
		this.file = file;
	}
	
	public String export() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("graphql", builder.addGroup(
			builder.addTag("queryName", this.file.getQueryName()),
			builder.addTag("apiUrl", this.file.getApiUrl())
		));
	}
	
}
