
package com.rinit.gui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.view.panels.PanelsView;

public class MainView extends JFrame {
	
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
		PanelsView panelsView = new PanelsView(this.eventHandler);
		CommandLineView commandLineView = new CommandLineView(this.eventHandler);
		HelpBarView helpBarView = new HelpBarView();
		this.add(panelsView);
		this.add(commandLineView);
		this.add(helpBarView);
	}
	
	private void setLookAndFeel() {
	    try {
	        UIManager.setLookAndFeel(
	                UIManager.getSystemLookAndFeelClassName());
	    }catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	}
	
}
