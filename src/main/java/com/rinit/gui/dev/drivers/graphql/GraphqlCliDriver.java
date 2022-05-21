package com.rinit.gui.dev.drivers.graphql;

import java.awt.Dimension;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.debugger.server.file.AbstractDriver;
import com.rinit.gui.dev.drivers.graphql.driver.GraphqlDriver;
import com.rinit.gui.model.ModelFacade;
import com.rinit.gui.model.fileDriver.AbstractCliDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;

public class GraphqlCliDriver extends AbstractCliDriver {

	private static final long serialVersionUID = 1780372510614788486L;

	public static final String NAME = "graphql";
	
	private GraphqlCliDriverLogic logic;
	private AbstractCliDriverView view;
	
	public GraphqlCliDriver() {}
	
	public GraphqlCliDriver(FileDTO readingFile, ModelFacade modelFacade) {
		super(readingFile, modelFacade);
		this.logic = new GraphqlCliDriverLogic(readingFile, modelFacade);
		this.view = new GraphqlCliDriverView(logic);
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public AbstractCliDriverView getView() {
		return this.view;
	}

	@Override
	public boolean isPopup() {
		return true;
	}

	@Override
	public Dimension getPopUpSize() {
		return null;
	}
	
	@Override
	public boolean isDirable() {
		return true;
	}

	@Override
	public Class<? extends AbstractDriver> getDriver() {
		return GraphqlDriver.class;
	}
	
}
