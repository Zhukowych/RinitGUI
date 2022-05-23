package com.rinit.gui.dev.drivers.graphql.driver;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.debugger.server.utils.XMLReader;
import com.rinit.gui.dev.bin.debugger.bin.RequestBuilder;
import com.rinit.gui.dev.bin.debugger.bin.context.ReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;
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
		ReportItem report = ReportItem.createDefaultReport(this);
		reportContext.addReport(report);		
	}

	@Override
	public void outRun(RunContext context) {}

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
