package com.rinit.gui.dev.bin;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.dev.bin.debuggerBin.DebuggerBin;
import com.rinit.gui.dev.core.DevCliBin;

public class DevDebuggerBin implements DevCliBin {

	private static final String NAME = "debugger";
	
	@Override
	public String getBinName() {
		return DevDebuggerBin.NAME;
	}

	@Override
	public Class<? extends AbstractCliBin> getBinClass() {
		return DebuggerBin.class;
	}

}
