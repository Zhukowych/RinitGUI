package com.rinit.gui.view.panels;

import java.awt.GridLayout;
import javax.swing.JPanel;
import com.rinit.gui.event.IEventHandler;

public class PanelsView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1178419312089791874L;
	
	private GridLayout experimentLayout = new GridLayout(0,2);
	
	private PanelView leftPanel;
	private PanelView rightPanel;
	
	public PanelsView(IEventHandler eventHandler) {
		this.leftPanel = new LeftPanelView(eventHandler);
		this.rightPanel = new RightPanelView(eventHandler);
		this.setLayout(experimentLayout);
		this.add(this.leftPanel);
		this.add(this.rightPanel);
	}
		
}
