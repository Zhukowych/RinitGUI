package com.rinit.gui.clibin.upload;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class UploadBin extends AbstractCliBin {

	public static final String NAME = "upload";
	
	private static final String LIBRARY = "lib";
	private static final String BIN = "bin";
	
	private String[] params;
	private ModelFacade modelFacade;
	
	private AbstractCliBinView view;
	
	
	public UploadBin(String[] params, ModelFacade modelFacade) {
		this.params = params;
		this.modelFacade = modelFacade;
		this.setView();
	}
	
	@Override
	public AbstractCliBinView getView() {
		return this.view;
	}
	
	private void setView() {
		if (this.params[0].equals(UploadBin.LIBRARY)) {
			this.view = new UploadLibView();
		} else {
			this.view = new UploadBinView();
		} 
	}

}
