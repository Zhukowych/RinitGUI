package com.rinit.gui.view;

import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.view.panels.PanelsView;

public class TabsView extends AbstractView {
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	private PanelsView panelsView;
	
	public TabsView(IEventHandler eventHandler) {
		super(eventHandler);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.panelsView = new PanelsView(eventHandler);
		this.configureTabbedPane();
	}	
	
	public void configureTabbedPane() {
		this.tabbedPane.addTab("File explorer", panelsView);
		this.add(this.tabbedPane);
	}
	
}
