package com.rinit.gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.model.viewModel.CliBinViewModel;
import com.rinit.gui.model.viewModel.SelectTabModelView;
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
		this.setBackground(Color.BLACK);
		this.tabbedPane.setBorder(null);
	}	
	
	public void configureTabbedPane() {
		this.tabbedPane.addTab("File explorer", panelsView);
		this.add(this.tabbedPane);
	}
	
	private void openTab(CliBinViewModel viewModel) {
		if (viewModel.isPopup == true) {
			this.openPopUp(viewModel);
		} else {
			this.tabbedPane.addTab("test", viewModel.view);
			this.tabbedPane.setSelectedIndex(this.tabbedPane.getTabCount()-1);
		}
	}
	
	private void openPopUp(CliBinViewModel viewModel) {
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (int) ((screenSize.width - viewModel.popupSize.getWidth()) / 2);
		final int y = (int) ((screenSize.height - viewModel.popupSize.getHeight()) / 2);
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		JDialog popUp = new JDialog(frame, "test", true);
		viewModel.view.setPopUp(popUp);
		popUp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		popUp.setLocation(x, y);
		popUp.add(viewModel.view);
		popUp.pack();
		popUp.setSize(viewModel.popupSize.width, popUp.getPreferredSize().height);
		popUp.setVisible(true);
	}
	
	private void closeTab() {
		this.tabbedPane.remove(this.tabbedPane.getSelectedIndex());
	}
	
	private void selectTab(SelectTabModelView modelView) {
		this.tabbedPane.setSelectedIndex(modelView.selectedTabInd);
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
	
		this.eventHandler.subscribe(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				selectTab((SelectTabModelView)eventInfo);
			}
			
		} , Event.SELECT_TAB);
	}
	
}
