package com.rinit.gui.dev.drivers.parsedobject;

public class ValueViewModel {
	
	private String valueType;
	
	private String valueContent;
	
	public ValueViewModel(String valueType, String valueContent) {
		this.valueType = valueType;
		this.valueContent = valueContent;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getValueContent() {
		return valueContent;
	}

	public void setValueContent(String valueContent) {
		this.valueContent = valueContent;
	}
	
}
