package com.rinit.gui.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.rinit.gui.event.IEventHandler;

public class KeyModel implements KeyListener{
	
	private IEventHandler eventHandler;
	
	public KeyModel(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}
	
	public void keyTyped(KeyEvent e) {
		System.out.println(123);
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(123);
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(123);
	}
	

}
