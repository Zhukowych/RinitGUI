package com.rinit.gui.dev.drivers.variables.driver;

import com.rinit.debugger.server.utils.XMLReader;

public class VariablesImporter {

	private VariablesDriver file;
	private XMLReader reader;
	
	public VariablesImporter(VariablesDriver file) {
		this.file = file;
		this.reader = new XMLReader(file.getContent());
		System.out.println(file.getContent());
	}
	
	public void parse() {
		if(!this.reader.isOk())
			return;
		String[][] table = reader.getTableData("value", new String[] {"variableName", "varianleValue"});
		for(String[] row : table) {
			this.file.setVariable(row[0], row[1]);
		}
	}

}
