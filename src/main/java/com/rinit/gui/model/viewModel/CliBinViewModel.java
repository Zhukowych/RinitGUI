package com.rinit.gui.model.viewModel;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.event.IEventContext;

public class CliBinViewModel implements IEventContext {
	
	private AbstractCliBinView view;

	public AbstractCliBinView getView() {
		return view;
	}

	public void setView(AbstractCliBinView view) {
		this.view = view;
	}
	
}
