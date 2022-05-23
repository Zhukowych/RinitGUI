package com.rinit.gui.dev.bin.apitest;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import com.rinit.gui.AbstractCliBinLogic;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.dev.bin.apitest.logic.ChangesActionLogic;
import com.rinit.gui.dev.bin.apitest.logic.InitActionLogic;
import com.rinit.gui.dev.bin.apitest.logic.ParseActionLogic;
import com.rinit.gui.dev.bin.apitest.view.ChangesActionView;
import com.rinit.gui.dev.bin.apitest.view.InitActionView;
import com.rinit.gui.dev.bin.apitest.view.ParseActionView;
import com.rinit.gui.model.ModelFacade;

public enum ApiTestAction {
	INIT(InitActionView.class, InitActionLogic.class, true, new Dimension(400, 200), true),
	PARSE(ParseActionView.class, ParseActionLogic.class, true, new Dimension(600, 600), true),
	CHANGES(ChangesActionView.class, ChangesActionLogic.class, true, new Dimension(800, 600), true);
	
	private Class<? extends AbstractCliBinView> actionView;
	private Class<? extends AbstractCliBinLogic> actionLogic;
	
	private boolean isPopup;
	private Dimension popupSize;
	
	private boolean isVisible;
	
	private ApiTestAction(Class<? extends AbstractCliBinView> actionView, Class<? extends AbstractCliBinLogic> actionLogic, 
						  boolean isPopup, Dimension popupSize, boolean isVisible) {
		this.actionView = actionView;
		this.actionLogic = actionLogic;
		
		this.isPopup = isPopup;
		this.popupSize = popupSize;
		
		this.isVisible = isVisible;
		
	}
	
	public Class<? extends AbstractCliBinLogic> getActionLogic() {
		return actionLogic;
	}

	public void setActionLogic(Class<? extends AbstractCliBinLogic> actionLogic) {
		this.actionLogic = actionLogic;
	}
	
	public Class<? extends AbstractCliBinView> getActionView() {
		return actionView;
	}

	public void setActionView(Class<? extends AbstractCliBinView> actionView) {
		this.actionView = actionView;
	}
	
	public boolean isPopup() {
		return isPopup;
	}

	public void setPopup(boolean isPopup) {
		this.isPopup = isPopup;
	}

	public Dimension getPopupSize() {
		return popupSize;
	}

	public void setPopupSize(Dimension popupSize) {
		this.popupSize = popupSize;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public AbstractCliBinView createViewInstance(String[] params, ModelFacade modelFacade) {
		AbstractCliBinLogic logic = this.createInstanceOfLogic(params, modelFacade);
		
		try {
			return this.actionView.getDeclaredConstructor(logic.getClass()).newInstance(logic);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private AbstractCliBinLogic createInstanceOfLogic(String[] params, ModelFacade modelFacade) {
		try {
			return this.actionLogic.getDeclaredConstructor(String[].class, ModelFacade.class).newInstance(params, modelFacade);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static ApiTestAction getActionByName(String actionName) {
		for (ApiTestAction action : ApiTestAction.values()) {
			if (action.name().equals(actionName.toUpperCase()))
				return action;
		}
		return null;
	}
	
}
