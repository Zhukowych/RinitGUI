
package com.rinit.gui.dev.drivers.requestParams.dirver;

import java.io.IOException;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.debugger.bin.RequestBuilder;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestContext;
import com.rinit.gui.dev.bin.debugger.bin.context.ReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;
import com.rinit.gui.utils.TimeUtils;

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
		ReportContext reportContext = context.getContext(ReportContext.class);
		RequestBuilder builder = requestContext.peekRequest();
		builder.setMethod(this.getMethod());
//		
//		if (this.getName().equals("videosFiltered")) {
//			System.out.println(this.getExtention());
//			getParams[0][0] = "channel";
//			getParams[0][1] = "OverSimplified";
//			builder.setGetParameters(getParams);
//		}
		if (getParams[0][0] != null)
			builder.setGetParameters(getParams);
		
		long startTime = System.nanoTime();
		builder.doRequest();
		reportContext.addReport(this.createReport(System.nanoTime() - startTime));
	}

	@Override
	public void outRun(RunContext context) {

	}

	private ReportItem createReport(long deltaTime) {
		ReportItem item = ReportItem.createDefaultReport(this);
		item.time = String.format("%s ms",
								Long.toString(TimeUtils.nanoToMiliSeconds(deltaTime)));
		return item;
	}
	
}
