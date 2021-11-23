package com.rinit.gui.utils.focurmanager;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class FocusManager {
	
	private static final String UP = "up";
	private static final String DOWN = "down";
	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	
	private JComponent parent;
	private FocusGroup group;
	
	private int vertical = -1, horizontal = -1;
	
	public FocusManager(JComponent parent) {
		this.parent = parent;
	}
	
	public FocusGroup createGroup() {
		return new FocusGroup();
	}
	
	public void setGroup(FocusGroup group) {
		this.group =  group;
		this.bindListeners();
	}
	
	@SuppressWarnings("serial")
	private void bindListeners() {
		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = this.parent.getInputMap(condition);
		ActionMap actionMap = this.parent.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
		actionMap.put(UP, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				up();
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
		actionMap.put(DOWN, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				down();
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
		actionMap.put(LEFT, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				left();
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
		actionMap.put(RIGHT, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				right();
			}
		});

	}

	private void up() {
		this.vertical--;
		 try {
			updateFocus();
		} catch (OutOfLLayoutException e) {
			this.vertical++;
		}
	}
	
	private void down() {
		this.vertical++;
		 try {
			updateFocus();
		} catch (OutOfLLayoutException e) {
			this.vertical--;
		}
	}
	
	private void left() {
		this.horizontal--;
		 try {
			updateFocus();
		} catch (OutOfLLayoutException e) {
			this.horizontal++;
		}
	}
	
	private void right() {
		System.out.println(4);
		this.horizontal++;
		 try {
			updateFocus();
		} catch (OutOfLLayoutException e) {
			this.horizontal--;
		}
	}
	
	public void updateFocus() throws OutOfLLayoutException {
		this.group.focusOn(vertical, horizontal);
	}
	
}
