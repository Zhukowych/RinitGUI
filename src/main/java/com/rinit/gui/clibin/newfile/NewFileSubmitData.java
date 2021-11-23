package com.rinit.gui.clibin.newfile;

public class NewFileSubmitData {
	
	public String fileName;
	public String fileExtention;
	public int filePosition;
	
	public NewFileSubmitData() {}
	
	public NewFileSubmitData(String fileName, String fileExtention, int filePosition) {
		this.fileName = fileName;
		this.fileExtention = fileExtention;
		this.filePosition = filePosition;
	}
	
}
