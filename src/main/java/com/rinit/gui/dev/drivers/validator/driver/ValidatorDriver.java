package com.rinit.gui.dev.drivers.validator.driver;

import org.apache.http.client.methods.CloseableHttpResponse;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.debugger.bin.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;

public class ValidatorDriver extends AbstractDriver implements DebuggerDriver {

	private int requiredHttpCode;
	
	@Override
	protected void buildFromDTO() {
		if (this.getContent().isEmpty())
			return;
		ValidatorDriverImporter importer = new ValidatorDriverImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		ValidatorDriverExporter exporter = new ValidatorDriverExporter(this);
		return exporter.export();
	}

	public int getRequiredHttpCode() {
		return requiredHttpCode;
	}

	public void setRequiredHttpCode(int requiredHttpCode) {
		this.requiredHttpCode = requiredHttpCode;
	}

	@Override
	public void run(RunContext context) {
		RequestContext requestContext = context.getContext(RequestContext.class);
		RequestReportContext reportContext = context.getContext(RequestReportContext.class);
		CloseableHttpResponse response = requestContext.peekRequest().getResponse();
		int statusCode = response.getStatusLine().getStatusCode();
		reportContext.addReport(this.createReport(statusCode));
	}

	@Override
	public void outRun(RunContext context) {}
	
	private ReportItem createReport(int statusCode) {
		ReportItem item = new ReportItem();
		item.elementName = this.getName();
		item.elementType = this.getExtention();
		item.shortReport = "Done";
		item.fullReport = "Done";
		return item;
	}

}
