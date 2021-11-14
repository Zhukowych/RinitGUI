package com.rinit.gui.dev.drivers.directory;

import com.rinit.debugger.server.core.Extentions;
import com.rinit.debugger.server.file.AbstractDriver;

public class DirectoryDriver extends AbstractDriver {

	@Override
	protected void buildFromDTO() {
		this.setExtention(Extentions.DIRECTORY);
	}

	@Override
	public String buildContent() {
		this.setExtention(Extentions.DIRECTORY);
		return "";
	}

}
