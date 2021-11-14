package com.rinit.gui.utils;

import java.util.List;

public class XMLBuilder {
	
	public String addTag(String tagName, String tagValue) {
		StringBuilder builder = new StringBuilder();
		if (tagValue.isEmpty()) {
			builder.append(String.format("<%s/>", tagName));
		} else {
			builder.append(String.format("<%s>", tagName));
			builder.append(tagValue);
			builder.append(String.format("</%s>", tagName));
		}
		return builder.toString();
	}
	
	public String addGroup(String...tags) {
		StringBuilder builder = new StringBuilder();
		for(String tag : tags) {
			builder.append(tag);
		}
		return builder.toString();
	}
	
	public<T> String concat(List<T> objects) {
		StringBuilder builder = new StringBuilder();
		for (Object obj : objects)
			builder.append(obj.toString());
		return builder.toString();
	}
	
	public String addTable(String rowName, String[] colsNames, String[][] tableData) {
		int rowCount = tableData.length;
		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < rowCount; row++){
			builder.append(this.addTag(rowName, this.creteCols(colsNames, tableData[row])));
		}
		return builder.toString();
	}
	
	private String creteCols(String[] colsNames, String[] cols) {
		int colCount = cols.length;
		StringBuilder builder = new StringBuilder();
		for(int col = 0; col < colCount; col++) {
			builder.append(this.addTag(colsNames[col], cols[col]));
		}
		return builder.toString();
	}
				
}

