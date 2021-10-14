package com.rinit.gui.view.panels;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.model.viewModel.FilesListViewModel;
import com.rinit.gui.model.viewModel.SelectionViewModel;

public class RightPanelView extends PanelView {

	public RightPanelView(IEventHandler eventHandler) {
		super(eventHandler);
	}
	
	@Override
	void subscribeForListeners() {
		this.eventHandler.subscribe(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				updateFilesList((FilesListViewModel) eventInfo);
			}
			
		}, Event.RIGHT_PANEL_UPDATE_FILES);		
		
		this.eventHandler.subscribe(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				unSelectTable();
			}
			
		}, Event.CLEAR_SELECTION);		

		this.eventHandler.subscribe(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				selectTable((FilesListViewModel)eventInfo);
			}
			
		}, Event.GO_RIGHT_PANEL);
		
	}
}
