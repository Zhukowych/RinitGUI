package com.rinit.gui.view.panels;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.model.viewModel.FilesListViewModel;

public class PanelsView extends JPanel {
	
	private IEventHandler eventHandler;
	private GridLayout experimentLayout = new GridLayout(0,2);
	
	private PanelView leftPanel;
	private PanelView rightPanel;
	
	public PanelsView(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
		this.leftPanel = new LeftPanelView(eventHandler);
		this.rightPanel = new RightPanelView(eventHandler);
		this.setLayout(experimentLayout);
		this.add(this.leftPanel);
		this.add(this.rightPanel);
	}
		
}
