
package com.rinit.gui.view;

import javax.swing.BoxLayout;

import javax.swing.JFrame;

import com.github.weisj.darklaf.LafManager;
import com.rinit.gui.event.IEventHandler;

public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1151303248940123808L;
	
	private IEventHandler eventHandler;
	
	public MainView(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
		this.setTitle("Runit GUI client");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLookAndFeel();
		this.setSize(1000, 600);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.addElements();
		this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);		
    	this.setFocusable(true);
	}	
	
	public void open() {
		this.setVisible(true);
	}
	
	private void addElements() {
		TabsView tabsView = new TabsView(this.eventHandler);
		CommandLineView commandLineView = new CommandLineView(this.eventHandler);
		HelpBarView helpBarView = new HelpBarView();
		this.add(tabsView);
		this.add(commandLineView);
		this.add(helpBarView);
	}
	
	private void setLookAndFeel() {
		LafManager.install();		
	}
	
}
