package com.rinit.gui.dev.drivers.realization;

import java.util.ArrayList;
import java.util.List;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;

public class RealizationCliDriverSubmitData {
	
	public String selectedExtention;
	public List<String> extentions = new ArrayList<String>();
	public AbstractCliFileDriverView realizationView;
	
	public RealizationCliDriverSubmitData() {}
	
	public RealizationCliDriverSubmitData(String extention) {
		this.selectedExtention = extention;
	}
	
}
