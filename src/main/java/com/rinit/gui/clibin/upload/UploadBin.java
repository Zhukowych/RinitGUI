package com.rinit.gui.clibin.upload;

import java.awt.Dimension;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;

public class UploadBin extends AbstractCliBin {

	public static final String NAME = "upload";
	
	private static final String LIBRARY = "lib";
	private static final String BIN = "bin";
	private static final String PHYSICAL_FILE = "pfile";
	private static final String DRIVER = "driver";
	
	private String[] params;
	private ModelFacade modelFacade;
	private AbstractCliBinView view;
	
	public UploadBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.setView();
		this.injectModelFacades();
	}
	
	@Override
	public AbstractCliBinView getView() {
		return this.view;
	}
	
	@Override
	public boolean isPopUp() {
		return false;
	}

	@Override
	public Dimension getPopUpSize() {
		return null;
	}
	
	private void injectModelFacades() {
		UploadBinLogic.setModelFacade(this.modelFacade);
	}
	
	private void setView() {
		if (this.params[0].equals(UploadBin.LIBRARY)) {
			this.view = new UploadLibView();
		} else if (this.params[0].equals(UploadBin.BIN)) {
			this.view = new UploadBinView();
		} else if (this.params[0].equals(UploadBin.PHYSICAL_FILE)) {
			this.view = new UploadPhysicalFileView();
		} else if(this.params[0].equals(UploadBin.DRIVER)) {
			this.view = new UploadDriverView();
		}
	}

}
