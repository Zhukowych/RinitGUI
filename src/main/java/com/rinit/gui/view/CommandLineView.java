package com.rinit.gui.view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.event.Mode;

public class CommandLineView extends JPanel {
	
	private static final int PREFERED_HEIGHT = 20;
	private Dimension preferedDimension = new Dimension((int)this.getMaximumSize().getWidth(), PREFERED_HEIGHT);
	private JTextField commandLine = new JTextField();
	private Font font = new Font("SansSerif", Font.BOLD, 13);
	
	private IEventHandler eventHandler;
	
	public CommandLineView(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setMaximumSize(preferedDimension);
		this.setPreferredSize(preferedDimension);
		this.commandLine.setFont(font);
		this.add(commandLine);
		this.subscribeForEvents();
	}
	
	private void subscribeForEvents() {
		this.eventHandler.subscribe(new IListener() {
			
			public void eventPerformed(IEventContext eventInfo) {
				focusOnInputField();
			}
			
		}, Event.SWITCH_TO_INPUT_MODE);
		
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				System.out.println(1323);
			}
		
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), Mode.INSERT);
	
	}
	
	private void focusOnInputField() {
		this.commandLine.requestFocus();
	}
}
