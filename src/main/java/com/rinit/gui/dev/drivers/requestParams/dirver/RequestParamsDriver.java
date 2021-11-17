package com.rinit.gui.dev.drivers.requestParams.dirver;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.debugger.bin.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.RequestBuilder;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;

public class RequestParamsDriver extends AbstractDriver implements DebuggerDriver {

	private String method;
	private String[][] getParams;
	private String[][] postParams;
	
	@Override
	protected void buildFromDTO() {
		RequestParamsDriverImporter importer = new RequestParamsDriverImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		RequestParamsDriverExporter exporter = new RequestParamsDriverExporter(this);
		return exporter.export();
	}
	
	@Override
	public boolean isDirable() {
		return true;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String[][] getGetParams() {
		return getParams;
	}

	public void setGetParams(String[][] getParams) {
		this.getParams = getParams;
	}

	public String[][] getPostParams() {
		return postParams;
	}

	public void setPostParams(String[][] postParams) {
		this.postParams = postParams;
	}

	@Override
	public void run(RunContext context) {
		RequestContext requestContext = context.getContext(RequestContext.class);
		RequestReportContext reportContext = context.getContext(RequestReportContext.class);
		RequestBuilder builder = requestContext.peekRequest();
		builder.setMethod(this.getMethod());
		builder.doRequest();
		reportContext.addReport(this.createReport());
	}

	@Override
	public void outRun(RunContext context) {
		RequestContext requestContext = context.getContext(RequestContext.class);
		requestContext.popRequest();
	}

	private ReportItem createReport() {
		ReportItem item = new ReportItem();
		item.elementName = this.getName();
		item.elementType = this.getExtention();
		item.shortReport = "Done";
		item.fullReport = "Done";
		item.time = "123";
		return item;
	}
	
}
