package com.rinit.gui.model.panels;

import java.util.List;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.debugger.server.core.Extentions;
import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.exception.ServiceException;
import com.rinit.debugger.server.services.interfaces.IFileService;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.viewModel.FilesListViewModel;
import com.rinit.gui.utils.FileConstants;
import com.rinit.gui.utils.UtilExtentions;

public abstract class AbstractPanelModel {
	
	protected IEventHandler eventHandler;
	
	protected RinitClient client = new RinitClient("http://localhost:8001");
	protected IFileService fileService = client.getFileService();

	protected FilesListViewModel fileList = new FilesListViewModel();
	
	// UTIL
	
	protected FileDTO upFile = FileDTO.builder().name("../").extention(UtilExtentions.HIGH).id((long) 0).build();
	
	public AbstractPanelModel(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
		this.setInitialFiles();
	}
	
	public void reUpdateFiles() {
		this.fileList.setFiles(this.getFilesByPath());
		this.updateView();
	}
	
	protected void setInitialFiles() {
		this.fileList.setFiles(this.getFilesByPath());
		this.updateView();
	}
	
	public void switchTo() {
		this.updateViewSelection();
	}
	
	public void goUp() {
		this.fileList.decrement();
		this.updateViewSelection();
	}
	
	public void goDown() {
		this.fileList.increment();
		this.updateViewSelection();
	}

	public void goDeepHight(boolean isDirable) {
		FileDTO file = this.fileList.getSelectedFile();
		if(file.getExtention().contains(Extentions.DIRECTORY) || isDirable) {
			this.goDeep(file);
		}else if(file.getExtention().contentEquals(UtilExtentions.HIGH)){
			this.goHigh();
		}
	}
	
	public void increasePosition() {
		if (this.fileList.getSelectedIndex() == 0)
			return;
		
		FileDTO selectedFile = this.getSelectedFile();
		selectedFile.setPosition(selectedFile.getPosition() - 1);
		this.saveFile(this.getSelectedFile());
		this.reUpdateFiles();
		this.fileList.setSelectedIndex(this.fileList.indexOf(selectedFile));
		this.updateViewSelection();
	}
	
	public void decreasePosition() {
		if (this.fileList.getSelectedIndex() == 0)
			return;


		FileDTO selectedFile = this.getSelectedFile();
		selectedFile.setPosition(selectedFile.getPosition() + 1);
		this.saveFile(this.getSelectedFile());
		this.reUpdateFiles();
		this.fileList.setSelectedIndex(this.fileList.indexOf(selectedFile));
		this.updateViewSelection();

	}
	
	public void deleteSelectedFile() {
		try {
			this.fileService.deleteAllChildrenOfPath(this.fileList.getSelectedFile().getChildrenPath());
		} catch (ServiceException e) {e.printStackTrace();}
		fileService.deleteFile(this.fileList.getSelectedFile());
	}
	
	public FileDTO getSelectedFile() {
		return this.fileList.getSelectedFile();
	}

	public String getCurrentPath() {
		return this.fileList.peekFromPathStack();
	}
	
	protected abstract void updateView();
	protected abstract void updateViewSelection();
	
	private List<FileDTO> getFilesByPath(){
		List<FileDTO> files = fileService.getFilesByPath(this.fileList.peekFromPathStack());
		files.add(0, upFile);
		return files;
	}
	
	private void goDeep(FileDTO file) {
		this.fileList.pushToPathStack(file.getChildrenPath());
		this.updatePanel();	
	}
	
	private void goHigh() {
		if (!this.fileList.peekFromPathStack().contentEquals(FileConstants.ROOT)) {
			this.fileList.popFromPathStack();
			this.updatePanel();		
		}
	}
	
	private void updatePanel() {
		this.fileList.setSelectedIndex(0);
		this.fileList.setFiles(this.getFilesByPath());
		this.updateView();
		this.updateViewSelection();
	}
	
	private void saveFile(FileDTO dto) {
		try {
			this.fileService.saveFile(dto);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}
