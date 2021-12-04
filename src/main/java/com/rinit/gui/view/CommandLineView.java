package com.rinit.gui.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.rinit.gui.event.Event;
import com.rinit.gui.event.IEventContext;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.event.IListener;
import com.rinit.gui.event.Mode;
import com.rinit.gui.model.viewModel.CommandViewModel;
import com.rinit.gui.model.viewModel.CurrentPathViewMode;

public class CommandLineView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1262559228177948852L;
	
	private static final int PREFERED_HEIGHT = 20;
	private Dimension preferedDimension = new Dimension((int)this.getMaximumSize().getWidth(), PREFERED_HEIGHT);
	private Font font = new Font("SansSerif", Font.PLAIN, 13);
	
	private GroupLayout layout;
	private JLabel currentPathLabel = new JLabel("123");
	private JTextField commandLine = new JTextField();
	
	
	private IEventHandler eventHandler;
	
	public CommandLineView(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
		this.layout = new GroupLayout(this);
		this.setLayout(this.layout);
		this.setMaximumSize(preferedDimension);
		this.setPreferredSize(preferedDimension);	
		this.setBackground(Color.BLACK);
		this.currentPathLabel.setForeground(Color.WHITE);
		this.currentPathLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
		this.commandLine.setFont(font);
		this.commandLine.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.commandLine.setBackground(Color.BLACK);
		this.commandLine.setBackground(Color.BLACK);
		this.commandLine.setForeground(Color.WHITE);
		this.commandLine.setCaretColor(Color.WHITE);

		
		this.layout.setHorizontalGroup(this.layout.createSequentialGroup()
				.addGap(5)
				.addComponent(this.currentPathLabel)
				.addGap(2)
				.addComponent(this.commandLine));
		
		this.layout.setVerticalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(this.currentPathLabel)
				.addComponent(this.commandLine));

		
		this.subscribeForEvents();
	}
	
	private void subscribeForEvents() {
		this.eventHandler.subscribe(new IListener() {
			
			public void eventPerformed(IEventContext eventInfo) {
				focusOnInputField();
			}
			
		}, Event.SWITCH_TO_INPUT_MODE);

		this.eventHandler.subscribe(new IListener() {
			
			public void eventPerformed(IEventContext eventInfo) {
				deFocusInputField();
			}
		
		}, Event.SWITCH_TO_DEFAULT_MODE);
		
		this.eventHandler.subscribeForKeyEvent(new IListener() {

			public void eventPerformed(IEventContext eventInfo) {
				submitCommand();
			}
		
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), Mode.INSERT);
	
		this.eventHandler.subscribe(new IListener() {
			
			@Override
			public void eventPerformed(IEventContext eventInfo) {
				updateCurrentPath((CurrentPathViewMode)eventInfo);
			}
			
		}, Event.CURRENT_PATH_UPDATE);
	}
	
	private void updateCurrentPath(CurrentPathViewMode viewModel) {
		this.currentPathLabel.setText(String.format("%s>", viewModel.currentPath));
	}
	
	private void submitCommand() {
		CommandViewModel viewModel = new CommandViewModel();
		viewModel.setCommand(this.commandLine.getText());
		this.eventHandler.performEvent(Event.SUBMIT_COMMAND, this, viewModel);
	}
	
	private void focusOnInputField() {
		this.commandLine.requestFocus();
	}
	
	private void deFocusInputField() {
		this.requestFocus();
	}
	
}
