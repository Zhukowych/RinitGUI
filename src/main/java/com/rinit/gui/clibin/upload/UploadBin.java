package com.rinit.gui.clibin.upload;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;

public class UploadBin extends AbstractCliBin {

	public static final String NAME = "upload";
	
	private UploadBinView view;
	
	public UploadBin() {
		this.view = new UploadBinView();
	}
	
	@Override
	public AbstractCliBinView getView() {
		return this.view;
	}

}
