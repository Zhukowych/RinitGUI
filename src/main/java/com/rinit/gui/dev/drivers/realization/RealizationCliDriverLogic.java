
package com.rinit.gui.dev.drivers.realization;

import java.util.List;

import javax.swing.JDialog;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.dev.bin.debugger.bin.DebuggerDriver;
import com.rinit.gui.dev.drivers.realization.driver.RealizationDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.FileDriverModel;

public class RealizationCliDriverLogic {

	private FileDTO readingFile;
	private ModelFacade modelFacade;
	private FileDriverModel driverModel;
	private IFileService fileServiceClient;
	
	private RealizationDriver file = new RealizationDriver();
	private FileDTO realizationFile = new FileDTO();
	private RealizationCliDriverSubmitData initialData = new RealizationCliDriverSubmitData();
	
	private JDialog popUp;
	
	public RealizationCliDriverLogic(FileDTO readingFile, ModelFacade modelFacade) {
		this.readingFile = readingFile;
		this.modelFacade = modelFacade;
		this.driverModel = this.modelFacade.getFileDriverModel();
		this.fileServiceClient = this.modelFacade.getRinitClientModel().getClient().getFileService();
		this.file.fromDTO(this.readingFile);
	}


	public RealizationCliDriverSubmitData getInitialData() {
		this.buildInitialData();
		return this.initialData;
	}
	
	public void changeRealizationTo(String extention) {
		this.initialData.selectedExtention  = extention;
		this.addRealizationView();
	}
	
	public void setDialog(JDialog dialog) {		
		this.popUp = dialog;
		this.initialData.realizationView.setPopUp(this.popUp);
	}
	
	private void buildInitialData() {
		this.addExtentions();
		this.addSelectedExtention();
		this.addRealizationView();
	}
	
	private void addRealizationView() {
		this.realizationFile = this.createExtention(this.initialData.selectedExtention);
		this.initialData.realizationView = this.driverModel.getCliFileDriverForExtention(this.realizationFile).getView();
	}
	
	private void addExtentions(){
		for (String extention : this.driverModel.getDriversNames()) {
			Class<?> driverClass = this.driverModel.getFileDrivers().get(extention);
			if (driverClass != null && DebuggerDriver.class.isAssignableFrom(driverClass)) 
				this.initialData.extentions.add(extention);
		}
	}
	
	private void addSelectedExtention() {
		if (this.file.getRealizationExtention() != null)
			this.initialData.selectedExtention = this.file.getRealizationExtention();
		else
			this.initialData.selectedExtention = this.initialData.extentions.get(0);
	}
		
	private FileDTO createExtention(String extention) {
		if (this.file.getRealizationExtention() != null) {
			List<FileDTO> files = this.fileServiceClient.getFilesByParentPathExtention(this.readingFile.getChildrenPath(), this.initialData.selectedExtention); 
			if (files.size() != 0)
				return files.get(0);
			else
				return this.createNewFile(extention);
		}else {
			return this.createNewFile(extention);
		}
	}
	

	private FileDTO createNewFile(String extention) {
		return FileDTO.builder().name("realization").extention(extention).path(this.readingFile.getChildrenPath()).content("").build();		
	}
	
	public void submit() {
		this.file.setRealizationExtention(this.initialData.selectedExtention);
		try {
			this.fileServiceClient.saveFile(file);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
