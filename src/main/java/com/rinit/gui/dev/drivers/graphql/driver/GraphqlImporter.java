package com.rinit.gui.dev.drivers.graphql.driver;

import com.rinit.debugger.server.utils.XMLReader;

public class GraphqlImporter {
	 
	private GraphqlDriver file;
	private XMLReader reader;
	
	public GraphqlImporter(GraphqlDriver file) {
		this.file = file;
		this.reader = new XMLReader(file.getContent());
	}
	
	public void parse() {
		if(!this.reader.isOk())
			return;
		this.file.setQueryName(this.reader.getTagValueByName("queryName", "graphql"));
		this.file.setApiUrl(this.reader.getTagValueByName("apiUrl", "graphql"));
	}
	
}
