package com.rinit.gui.view.ui;

import java.awt.Color;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import com.rinit.gui.view.Colors;

public class RComboBox<E> extends JComboBox<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7933711527532169246L;

	public RComboBox(E[] selection){
		super(selection);
		UIManager.put("ComboBox.background", new ColorUIResource(Colors.INPUT_BACKGROUND));
		UIManager.put("ComboBox.foreground", new ColorUIResource(Color.BLACK));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Colors.INPUT_BACKGROUND));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));

		this.setBackground(Colors.INPUT_BACKGROUND);
		this.setUI(RComboBoxUI.createUI(this));
		this.setBackground(Colors.INPUT_BACKGROUND);

	}
	
	static class RComboBoxUI extends BasicComboBoxUI {

	    public static ComboBoxUI createUI(JComponent c) {
	        return new RComboBoxUI();
	    }

	    @Override protected JButton createArrowButton() {
	    	return new BasicArrowButton(
	                BasicArrowButton.SOUTH,
	                Colors.INPUT_BACKGROUND, Colors.INPUT_BACKGROUND,
	                Colors.INPUT_BACKGROUND, Colors.INPUT_BACKGROUND);
	    }
	}
	
}

