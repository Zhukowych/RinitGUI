package com.rinit.gui.model;

import java.util.ArrayList;
import java.util.List;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.viewModel.CliBinViewModel;

public class TabsModel extends AbstractModel {
	
	private int selectedTabInd = 0;
	private List<AbstractCliBin> tabs = new ArrayList<AbstractCliBin>();
	
	public TabsModel(IEventHandler eventHandler) {
		super(eventHandler);
	}
	
	public void addTab(AbstractCliBin bin) {
		this.tabs.add(bin);
		CliBinViewModel viewModel = new CliBinViewModel();
		viewModel.setView(bin.getView());
		this.selectedTabInd = this.tabs.size();
		this.eventHandler.performEvent(Event.OPEN_TAB, this, viewModel);
	}
	
	public void closeTab() {
		this.tabs.remove(selectedTabInd - 1);
		this.selectedTabInd--;
		this.eventHandler.performEvent(Event.CLOSE_TAB, this, null);
	}

}
