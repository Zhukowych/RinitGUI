package com.rinit.gui.clibin.read;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;

public class ReadBinView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5023907430217964228L;

	private AbstractCliDriver readingFileDriver;
	
	public ReadBinView() {}
	
	public ReadBinView(FileDTO readingFile, AbstractCliDriver readingFileDriver) {
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
