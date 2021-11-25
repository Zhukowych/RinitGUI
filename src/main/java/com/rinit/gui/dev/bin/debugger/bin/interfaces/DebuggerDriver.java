package com.rinit.gui.dev.bin.debugger.bin.interfaces;

import com.rinit.gui.dev.bin.debugger.bin.context.RunContext;

public interface DebuggerDriver {
	
	public void run(RunContext context);
	public void outRun(RunContext context);
	
}
