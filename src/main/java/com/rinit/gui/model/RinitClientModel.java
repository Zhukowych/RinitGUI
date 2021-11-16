package com.rinit.gui.model;

import com.rinit.debugger.server.client.RinitClient;
import com.rinit.gui.Main;
import com.rinit.gui.event.IEventHandler;

public class RinitClientModel extends AbstractModel {

	private String rinitServerHost = Main.host;
	
	public RinitClientModel(IEventHandler eventHandler) {
		super(eventHandler);
	}

	
	public RinitClient getClient() {
		return new RinitClient(rinitServerHost);
	}
	
}
