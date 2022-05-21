package com.rinit.gui.dev.drivers.request.driver;

import java.io.IOException;

import com.rinit.debugger.server.file.AbstractDriver;

import com.rinit.gui.dev.bin.debugger.bin.RequestBuilder;
import com.rinit.gui.dev.bin.debugger.bin.context.ReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;

public class RequestDriver extends AbstractDriver implements DebuggerDriver {

	public static final String EXTENTION = "request";
	
	private String protocol;
	private String adress;
	private String path;
	
	@Override
	protected void buildFromDTO() {
		RequestImporter importer = new RequestImporter(this);
		importer.parse();
	}

	@Override
	public String buildContent() {
		RequestExporter exporter = new RequestExporter(this);
		return exporter.export();
	}
	
	@Override
	public boolean isDirable() {
		return true;
	}
	
	
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getUrlPath() {
		return path;
	}

	public void setUrlPath(String path) {
		this.path = path;
	}
	
	private String toURL() {
		return String.format("%s://%s%s", this.protocol, this.adress, this.path);
	}
	
	@Override
	public void run(RunContext context) {
		RequestContext requestContext = context.getContext(RequestContext.class);
		ReportContext reportContext = context.getContext(ReportContext.class);
		RequestBuilder requestBuilder = requestContext.createNewRequest();
		requestBuilder.setUrl(this.toURL());
		reportContext.addReport(ReportItem.createDefaultReport(this));
	}

	@Override
	public void outRun(RunContext context) {
		RequestContext requestContext = context.getContext(RequestContext.class);
		RequestBuilder builder = requestContext.popRequest();
		try {
			builder.getResponse().close();
		} catch (IOException e) {e.printStackTrace();}
	}

}
