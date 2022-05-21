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

	private String query;
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	@Override
	protected void buildFromDTO() {		
		XMLReader reader = new XMLReader(this.getContent());
		this.setQuery(reader.getTagValueByName("query", "graphql"));
	}

	@Override
	public String buildContent() {
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("graphql", builder.addTag("query", this.getQuery()));
	}

	@Override
	public void run(RunContext context) {
		ReportContext reportContext = context.getContext(ReportContext.class);
		ReportItem report = ReportItem.createDefaultReport(this);
		reportContext.addReport(report);		
	}

	@Override
	public void outRun(RunContext context) {
		// TODO Auto-generated method stub
		
	}

}
