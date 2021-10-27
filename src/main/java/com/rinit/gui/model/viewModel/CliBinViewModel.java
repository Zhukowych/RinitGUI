package com.rinit.gui.model.viewModel;

import java.awt.Dimension;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.event.IEventContext;

public class CliBinViewModel implements IEventContext {
	
	public boolean isPopup;
	public Dimension popupSize;
	public AbstractCliBinView view;
	
}
