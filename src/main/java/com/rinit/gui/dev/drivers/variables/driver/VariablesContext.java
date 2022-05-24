package com.rinit.gui.dev.drivers.variables.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariablesContext {
	
	private Map<String, String> variables = new HashMap<String, String>();
	
	public void addVariables(VariablesDriver variables) {
		this.variables.putAll(variables.getValues());
	}
	
	public String escapeString(String stringToEscape) {
		List<String> variablesNames = this.getVariablesNames(stringToEscape);
		for (String variableName : variablesNames) {
			stringToEscape = stringToEscape.replaceAll(String.format("\\$\\{%s\\}", variableName), this.variables.get(variableName));
		}
		return stringToEscape;
	}
	
	public List<String> getVariablesNames(String stringToEscape){
		List<String> variableNames = new ArrayList<String>();
		Matcher variableMatcher = Pattern.compile("\\$\\{([a-zA-Z]+)\\}").matcher(stringToEscape);
		while(variableMatcher.find())
			variableNames.add(variableMatcher.group(variableMatcher.groupCount()));
		return variableNames;
	}
	
}
 