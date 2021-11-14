
package com.rinit.gui.controller;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.debugger.server.core.Extentions;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.event.Mode;
import com.rinit.gui.model.BinModel;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.FileDriverModel;
import com.rinit.gui.model.panels.AbstractPanelModel;
import com.rinit.gui.model.panels.Panel;
import com.rinit.gui.model.panels.PanelsModel;
import com.rinit.gui.model.viewModel.CurrentPathViewMode;
import com.rinit.gui.utils.UtilExtentions;

public class PanelsController {
	
	private IEventHandler eventHandler;
	private PanelsModel panelsModel;
	private BinModel binModel;
	private FileDriverModel driverModel;
	
	public PanelsController(IEventHandler eventHandler, ModelFacade modelFacade) {
		this.eventHandler = eventHandler;
		this.panelsModel = modelFacade.getPanelsModel();
		this.binModel = modelFacade.getBinModel();
		this.driverModel = modelFacade.getFileDriverModel();
		this.bindKeys();
		this.updateCurrentPath();
	}
	
	private void goToLeftPanel() {
		this.panelsModel.setPanel(Panel.LEFT);
		this.updateCurrentPath();
	}
	
	private void goToRightPanel() {
		this.panelsModel.setPanel(Panel.RIGHT);
		this.updateCurrentPath();
	}
	
	private void goUp() {
		this.ifPanelNotSelected();
		this.panelsModel.getSelectedPanelModel().goUp();
		this.updateCurrentPath();
	}
	
	private void goDown() {
		this.ifPanelNotSelected();
		this.panelsModel.getSelectedPanelModel().goDown();
		this.updateCurrentPath();
	}
	
	private void enterOnFile() {
		FileDTO selectedFile = this.panelsModel.getSelectedPanelModel().getSelectedFile();
		if (selectedFile.getExtention().contains(Extentions.DIRECTORY) || this.driverModel.isExtentionDirable(selectedFile.getExtention()))
			this.panelsModel.getSelectedPanelModel().goDeepHight(true);
		else if (selectedFile.getExtention().equals(UtilExtentions.HIGH))
			this.panelsModel.getSelectedPanelModel().goDeepHight(false);			
		else  
			this.binModel.execute(this.createReadCommandForFile(selectedFile));
		this.updateCurrentPath();
	}
	
	private void forceEnterOnFile() {
		FileDTO selectedFile = this.panelsModel.getSelectedPanelModel().getSelectedFile();
		this.binModel.execute(this.createReadCommandForFile(selectedFile));
	}
	
	private void deleteFile() {
		this.panelsModel.getSelectedPanelModel().deleteSelectedFile();
		this.panelsModel.reUpdatePanels();
		this.panelsModel.getSelectedPanelModel().goUp();
	}
	
	private void updateCurrentPath() {
		this.eventHandler.performEvent(Event.CURRENT_PATH_UPDATE, this, new  CurrentPathViewMode(this.panelsModel.getCurrentPath()));
	}

	private void bindKeys() {
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				goToLeftPanel();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK), Mode.DEFAULT);
		
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				goToRightPanel();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), Mode.DEFAULT);
		
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				goUp();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), Mode.DEFAULT);

		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				goDown();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), Mode.DEFAULT);

		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				enterOnFile();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), Mode.DEFAULT);

		this.eventHandler.subscribeForKeyEvent(new IListener() {

			@Override
			public void eventPerformed(IEventContext eventInfo) {
				forceEnterOnFile();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK), Mode.DEFAULT);
		
		this.eventHandler.subscribeForKeyEvent(new IListener() {
			
			@Override
			public void eventPerformed(IEventContext eventInfo) {
				deleteFile();
			}
			
		}, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), Mode.DEFAULT);
		
	}
	
	private void ifPanelNotSelected() {
		AbstractPanelModel selectedPanelModel = this.panelsModel.getSelectedPanelModel();
		if (selectedPanelModel == null) {
			this.goToLeftPanel();
		}
	}
	
	private String createReadCommandForFile(FileDTO file) {
		StringBuilder commandBuilder = new StringBuilder("read ");
		commandBuilder.append(file.getPath() + " ");
		commandBuilder.append(file.getName());
		return commandBuilder.toString();
	}
	
}
