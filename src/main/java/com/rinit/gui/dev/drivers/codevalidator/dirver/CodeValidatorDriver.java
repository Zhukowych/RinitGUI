package com.rinit.gui.dev.drivers.codevalidator.dirver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.debugger.server.utils.XMLReader;
import com.rinit.gui.dev.bin.debugger.bin.context.ReportContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.DebuggerDriver;
import com.rinit.gui.utils.ReflectionUtils;
import com.rinit.gui.utils.XMLBuilder;

public class CodeValidatorDriver extends AbstractDriver implements DebuggerDriver {

	public static final String EXTENTION = "codevalidator";
	
	private String validatorClassPath;
	private String validatroClassName;
	
	public String getValidatorClassPath() {
		return validatorClassPath;
	}

	public void setValidatorClassPath(String validatorClassPath) {
		this.validatorClassPath = validatorClassPath;
	}

	public String getValidatroClassName() {
		return validatroClassName;
	}

	public void setValidatroClassName(String validatroClassName) {
		this.validatroClassName = validatroClassName;
	}

	@Override
	protected void buildFromDTO() {
		XMLReader reader = new XMLReader(this.getContent());
		if (!reader.isOk())
			return;
		this.setValidatorClassPath(reader.getTagValueByName("validatorClassPath", "codeValidator"));
		this.setValidatroClassName(reader.getTagValueByName("validatroClassName", "codeValidator"));
	}

	@Override
	public String buildContent() {
		this.setExtention(EXTENTION);
		XMLBuilder builder = new XMLBuilder();
		return builder.addTag("codeValidator", builder.addGroup(
			builder.addTag("validatorClassPath", this.validatorClassPath),
			builder.addTag("validatroClassName", this.validatroClassName)
		));
	}

	@Override
	public void run(RunContext context) {
		ReportContext reportContext = context.getContext(ReportContext.class);
		CodeValidator validator = this.getValidatorInstance();
		reportContext.addReport(validator.validate(context, this));
	}

	@Override
	public void outRun(RunContext context) {}
	
	public CodeValidator getValidatorInstance() {
		Class<?> validatorClass = null;
	    try {
			ClassLoader classLoader = new URLClassLoader(new URL[]{ new File(this.validatorClassPath).toURI().toURL() });
			validatorClass = classLoader.loadClass(this.validatroClassName);	
	    } catch (MalformedURLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	    return (CodeValidator)ReflectionUtils.createInstanceOf(validatorClass);
	}

}
