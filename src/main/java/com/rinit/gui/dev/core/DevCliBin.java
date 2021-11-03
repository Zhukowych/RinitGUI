package com.rinit.gui.dev.core;

import com.rinit.gui.clibin.AbstractCliBin;

public interface DevCliBin {
	
	public String getBinName();
	public Class<? extends AbstractCliBin> getBinClass();
	
}
