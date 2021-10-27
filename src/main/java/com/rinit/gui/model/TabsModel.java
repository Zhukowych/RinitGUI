package com.rinit.gui.model;

import java.util.ArrayList;
import java.util.List;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.viewModel.CliBinViewModel;
import com.rinit.gui.model.viewModel.SelectTabModelView;

public class TabsModel extends AbstractModel {
	
	private int selectedTabInd = 0;
	private List<AbstractCliBin> tabs = new ArrayList<AbstractCliBin>();
	
	public TabsModel(IEventHandler eventHandler) {
		super(eventHandler);
	}
	
	public void addTab(AbstractCliBin bin) {
		this.tabs.add(bin);
		CliBinViewModel viewModel = new CliBinViewModel();
		viewModel.view = bin.getView();
		viewModel.isPopup = bin.isPopUp();
		viewModel.popupSize = bin.getPopUpSize();
		this.selectedTabInd = this.tabs.size();
		this.eventHandler.performEvent(Event.OPEN_TAB, this, viewModel);
	}
	
	public void closeTab() {
		if (this.selectedTabInd != 0) {
			this.tabs.remove(selectedTabInd - 1);
			this.selectedTabInd--;
			this.eventHandler.performEvent(Event.CLOSE_TAB, this, null);
		}
	}
	
	public void moveLeft() {
		if (this.selectedTabInd != 0) {
			this.selectedTabInd--;
			this.updateSelectedTabSelection();
		}
	}
	
	public void moveRight() {
		if (this.selectedTabInd != this.tabs.size()) {
			this.selectedTabInd++;
			this.updateSelectedTabSelection();
		}
	}
	
	private void updateSelectedTabSelection() {
		SelectTabModelView modelView = new SelectTabModelView();
		modelView.selectedTabInd = this.selectedTabInd;
		this.eventHandler.performEvent(Event.SELECT_TAB, this, modelView);
	}
	
}
