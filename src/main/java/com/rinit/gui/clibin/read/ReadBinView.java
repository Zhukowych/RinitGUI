package com.rinit.gui.clibin.read;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriver;

public class ReadBinView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5023907430217964228L;

	private FileDTO readingFile;
	private AbstractCliFileDriver readingFileDriver;
	
	
	public ReadBinView(FileDTO readingFile, AbstractCliFileDriver readingFileDriver) {
		this.readingFile = readingFile;
		this.readingFileDriver = readingFileDriver;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(this.readingFileDriver.getView());
	}
	
	@Override
	public void setPopUp(JDialog popUp) {
		this.popUp = popUp;
		this.readingFileDriver.getView().setPopUp(popUp);
	}
}
