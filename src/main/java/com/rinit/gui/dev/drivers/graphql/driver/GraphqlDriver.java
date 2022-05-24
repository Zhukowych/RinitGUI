package com.rinit.gui.dev.drivers.graphql.driver;

import java.io.IOException;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.debugger.server.utils.XMLReader;
import com.rinit.gui.dev.bin.debugger.bin.RequestBuilder;
import com.rinit.gui.dev.bin.debugger.bin.context.ReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;
import com.rinit.gui.dev.drivers.apitesttestsdir.ParsedObjectsContext;
import com.rinit.gui.dev.drivers.parsedobject.dirver.ParsedObjectDriver;
import com.rinit.gui.dev.drivers.variables.driver.VariablesContext;
import com.rinit.gui.utils.XMLBuilder;

public class GraphqlDriver extends AbstractDriver implements DebuggerDriver {

	public static final String EXTENTION = "graphql";
	
	private String queryName;
	
	private String apiUrl;
	
	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	
	@Override
	public void run(RunContext context) {
		ReportContext reportContext = context.getContext(ReportContext.class);
		this.runQuery(context);
		ReportItem report = ReportItem.createDefaultReport(this);
		reportContext.addReport(report);		
	}

	private void runQuery(RunContext context) {
		ParsedObjectsContext parsedObjectsContext = context.getContext(ParsedObjectsContext.class);	
		RequestContext requestContext = context.getContext(RequestContext.class);
		GraphqlQueriesResponseContext graphqlQueriesResponseContext = context.getContext(GraphqlQueriesResponseContext.class);
		VariablesContext variablesContext = context.getContext(VariablesContext.class);
		ParsedObjectDriver parsedQuery = parsedObjectsContext.getParsedObject(this.queryName);

		requestContext.createNewRequest();
		RequestBuilder builder = requestContext.peekRequest();
		
		builder.setMethod("POST");
		builder.setUrl(variablesContext.escapeString(this.apiUrl));
		builder.setPostParamaters(this.createPostParams(parsedQuery));
		builder.doRequest();
			
		graphqlQueriesResponseContext.createNewRequest(new GraphqlResponse(builder.readContent()));
	}
	
	public String[][] createPostParams(ParsedObjectDriver parsedQuery) {
		return new String[][] {
			new String[] {"query", parsedQuery.getValue("query")},
			new String[] {"variables", "{}"}
		};
	}
	
	@Override
	public void outRun(RunContext context) {
		RequestContext requestContext = context.getContext(RequestContext.class);
		GraphqlQueriesResponseContext graphqlQueriesResponseContext = context.getContext(GraphqlQueriesResponseContext.class);
		graphqlQueriesResponseContext.popRequest();
		RequestBuilder builder = requestContext.popRequest();
		try {
			builder.getResponse().close();
		} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	protected void buildFromDTO() {
		GraphqlImporter importer = new GraphqlImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		this.setExtention(EXTENTION);
		GraphqlExporter exporter = new GraphqlExporter(this);
		return exporter.export();
	}

}
