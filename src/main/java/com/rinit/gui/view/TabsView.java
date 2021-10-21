package com.rinit.gui.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.model.viewModel.CliBinViewModel;
import com.rinit.gui.view.panels.PanelsView;

public class TabsView extends AbstractView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1647809133407144350L;

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
	
	private void openTab(CliBinViewModel viewModel) {
		this.tabbedPane.addTab("test", viewModel.getView());
		this.tabbedPane.setSelectedIndex(this.tabbedPane.getTabCount()-1);
	}
	
	private void closeTab() {
		this.tabbedPane.remove(this.tabbedPane.getSelectedIndex());
	}

	@Override
	protected void subscribeForEvents() {
		this.eventHandler.subscribe(new IListener() {
			
			public void eventPerformed(IEventContext eventInfo) {
				openTab((CliBinViewModel)eventInfo);
			}
			
		}, Event.OPEN_TAB);
		
		this.eventHandler.subscribe(new IListener() {
			
			public void eventPerformed(IEventContext eventInfo) {
				closeTab();
			}
			
		}, Event.CLOSE_TAB);
	
	}
	
}
