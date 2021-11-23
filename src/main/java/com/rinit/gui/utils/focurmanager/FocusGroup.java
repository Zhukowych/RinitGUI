package com.rinit.gui.utils.focurmanager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class FocusGroup {
	
	private List<Object> objects = new ArrayList<Object>();
	
	public FocusGroup addGroup(FocusGroup group) {
		this.objects.add(group);
		return this;
	}
	
	public FocusGroup addComponent(JComponent component) {
		this.objects.add(component);
		return this;
	}
	
	public void focusOn(int vertical, int horizontal) throws OutOfLLayoutException {
		if (!(vertical >= 0 && vertical < objects.size()))
	  		throw new OutOfLLayoutException();
		Object element = objects.get(vertical);
		if (element.getClass().equals(FocusGroup.class)) {
		 	FocusGroup focusGroup = (FocusGroup) element;
		 	focusGroup.focusOn(horizontal);
		} else {
			JComponent component = (JComponent) element;
			component.requestFocus();
		}
	}
	
	public void focusOn(int horizontal) throws OutOfLLayoutException {
		if (!(horizontal >= 0 && horizontal < objects.size())) 
			throw new OutOfLLayoutException();
     	Object element = this.objects.get(horizontal);
		JComponent component = (JComponent) element;
		component.requestFocus();
	}

}
