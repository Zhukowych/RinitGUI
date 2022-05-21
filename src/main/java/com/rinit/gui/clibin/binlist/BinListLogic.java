package com.rinit.gui.clibin.binlist;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.rinit.gui.model.BinModel;
import com.rinit.gui.model.ModelFacade;

public class BinListLogic {
	
	private ModelFacade modelFacade;
	
	private BinModel binModel;
	
	public BinListLogic(ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
		this.binModel = this.modelFacade.getBinModel();
	}
	
	public Set<String> getBinNamesList() {
		return this.binModel.getBins().keySet();
	}
	
	public List<String> filterBinNames(String searchText){
		return this.getBinNamesList().stream().filter(binName -> binName.startsWith(searchText)).collect(Collectors.toList());
	}
	
	public void runBinWithName(String binName) {
		this.binModel.execute(binName);
	}
	
}
