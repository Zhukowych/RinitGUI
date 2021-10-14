package com.rinit.gui.model.viewModel;

import com.rinit.gui.event.IEventContext;

public class SelectionViewModel implements IEventContext {
	
	private int selectedIndex;
	private int maxSelectionIndex;
	
	public void setMaxSelectionIndex(int maxSelectionIndex) {
		this.maxSelectionIndex = maxSelectionIndex;
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	
	public void increment() {
		if (this.selectedIndex != this.maxSelectionIndex - 1)
			this.selectedIndex++;
	}
	
	public void decrement() {
		if (this.selectedIndex != 0)
			this.selectedIndex--;
	}
}
