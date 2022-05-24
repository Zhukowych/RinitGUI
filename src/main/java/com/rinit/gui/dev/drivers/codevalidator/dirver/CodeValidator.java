package com.rinit.gui.dev.drivers.codevalidator.dirver;

import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;

public interface CodeValidator {
	
	public ReportItem validate(RunContext context, CodeValidatorDriver validatorDriver);

}
