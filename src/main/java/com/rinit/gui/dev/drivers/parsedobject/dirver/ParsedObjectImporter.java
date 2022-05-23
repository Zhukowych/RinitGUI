package com.rinit.gui.dev.drivers.parsedobject.dirver;

import com.rinit.debugger.server.utils.XMLReader;
import com.rinit.gui.dev.drivers.apitestconfig.driver.ApiTestConfiigDriver;
import com.rinit.gui.dev.drivers.apitestconfig.driver.FileToParse;

public class ParsedObjectImporter {

	private ParsedObjectDriver file;
	private XMLReader reader;
	
	public ParsedObjectImporter(ParsedObjectDriver file) {
		this.file = file;
		this.reader = new XMLReader(file.getContent());	
	}
	
	public void parse() {
		if (!this.reader.isOk())
			return;
		
		this.file.setKey(this.reader.getTagValueByName("key", "parsedObject"));
		String[][] values = this.reader.getTableData("value", new String[]{ "valueType", "valueContent"});
		
		for (String[] value : values) {
			this.file.addValue(value[0], value[1]);
		}
		
	}

	
}
