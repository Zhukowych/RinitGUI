package com.rinit.gui.clibin.copy;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.panels.PanelsModel;

public class CopyCliBin extends AbstractCliBin {

	public static final String NAME = "copy";
	
	private IFileService fileServiceClient;
	private PanelsModel panelsModel;
	
	public CopyCliBin() {}
	
	public CopyCliBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.fileServiceClient = this.modelFacade.getRinitClientModel().getClient().getFileService();
		this.panelsModel = this.modelFacade.getPanelsModel();
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public KeyStroke getKeyBinding() {
		return KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
	}

	@Override
	public AbstractCliBinView getView() {
		return null;
	}

	@Override
	public boolean isPopUp() {
		return false;
	}

	@Override
	public Dimension getPopUpSize() {
		return null;
	}
	
	@Override
	public boolean visible() {
		return false;
	}

	@Override
	public void run() {
		FileDTO fileToCopy = this.panelsModel.getSelectedPanelModel().getSelectedFile();
		String destination = this.panelsModel.getNotSelectedModel().getCurrentPath();
		this.fileServiceClient.copyFile(fileToCopy, destination);
		this.panelsModel.reUpdatePanels();
		System.out.println(destination);
	}
	
}
