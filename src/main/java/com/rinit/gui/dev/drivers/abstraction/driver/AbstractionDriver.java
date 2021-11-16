package com.rinit.gui.dev.drivers.abstraction.driver;

import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.bin.debugger.bin.DebuggerDriver;
import com.rinit.gui.dev.bin.debugger.bin.context.AbstractionContext;
import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;

public class AbstractionDriver extends AbstractDriver implements DebuggerDriver {

	@Override
	public boolean isDirable() {
		return true;
	}
	
	@Override
	protected void buildFromDTO() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String buildContent() {
		return "";
	}

	@Override
	public void run(RunContext context) {
		DebuggerDriver realization = this.getRealization(context);
		realization.run(context);
	}

	@Override
	public void outRun(RunContext context) {
		DebuggerDriver realization = this.getRealization(context);
		realization.outRun(context);
	}
	
	private DebuggerDriver getRealization(RunContext context) {
		AbstractDriver realization = context.getContext(AbstractionContext.class).getRealization(this.getName());
		DebuggerDriver realizationDebugger = (DebuggerDriver)realization;
		return realizationDebugger;
	}

}
