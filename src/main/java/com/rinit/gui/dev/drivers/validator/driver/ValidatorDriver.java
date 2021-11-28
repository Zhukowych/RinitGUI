package com.rinit.gui.dev.drivers.validator.driver;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.methods.CloseableHttpResponse;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.debugger.bin.context.RequestContext;
import com.rinit.gui.dev.bin.debugger.bin.context.ReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;
import com.rinit.gui.utils.TimeUtils;

public class ValidatorDriver extends AbstractDriver implements DebuggerDriver {

	private int requiredHttpCode;
	private String requiredContent;
	private ReportItem reportItem;
	
	@Override
	protected void buildFromDTO() {
		if (this.getContent().isEmpty())
			return;
		ValidatorDriverImporter importer = new ValidatorDriverImporter(this);
		importer.parse();
		this.reportItem = ReportItem.createDefaultReport(this);
		this.reportItem.fullReport = "";
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

	public String getRequiredContent() {
		return requiredContent;
	}

	public void setRequiredContent(String requiredContent) {
		this.requiredContent = requiredContent;
	}

	@Override
	public void run(RunContext context) {
		long startTime = System.nanoTime();
		RequestContext requestContext = context.getContext(RequestContext.class);
		ReportContext reportContext = context.getContext(ReportContext.class);
		CloseableHttpResponse response = requestContext.peekRequest().getResponse();
		this.checkResponseCode(response);
		this.checkResponseContent(response);
		long endTime = System.nanoTime();	
		this.reportItem.time = TimeUtils.toMsString(TimeUtils.nanoToMiliSeconds(endTime-startTime));
		reportContext.addReport(this.reportItem);
	}

	@Override
	public void outRun(RunContext context) {}
	
	private void checkResponseCode(CloseableHttpResponse response) {
		int responseCode = response.getStatusLine().getStatusCode();
		if (responseCode == this.requiredHttpCode)
			this.setRepostPassed();
		else {
			this.setReportFail();
			this.reportItem.fullReport += String.format("Expected response code %s, but get %s \n", this.requiredHttpCode, responseCode);
		}
	}
	
	private void checkResponseContent(CloseableHttpResponse response) {
		if (this.requiredContent == null || this.requiredContent.isEmpty())
			return;
		String content = this.readContent(response);
		if (content.contains(this.requiredContent)) 
			this.setRepostPassed();
		else {
			this.setReportFail();
			this.reportItem.fullReport += String.format("Expected response content contains '%s', but id does not\n", this.requiredContent);			
		}
	}
	
	private void setReportFail() {
		this.reportItem.shortReport = "FAIL";
	}
	
	private void setRepostPassed() {
		if (!this.reportItem.shortReport.equals("FAIL"))
			this.reportItem.shortReport = "PASSED";
	}
	
	private String readContent(CloseableHttpResponse response) {
		InputStream input = null;
		try {
			input = response.getEntity().getContent();
		} catch (UnsupportedOperationException | IOException e) {e.printStackTrace();}
		try {
			return new String(input.readAllBytes());
		} catch (IOException e) {e.printStackTrace();}
		return "";
	}
	
}
