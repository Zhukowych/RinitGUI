
package com.rinit.gui.clibin.newfile;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.rinit.gui.clibin.AbstractCliBin;
import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.clibin.newfile.NewFileLogic.LogicType;
import com.rinit.gui.model.ModelFacade;

public class NewFileBin extends AbstractCliBin {

	public static final String NAME = "new";

	private NewFileLogic logic;
	
	public NewFileBin() {}
	
	public NewFileBin(String[] params, ModelFacade modelFacade) {
		super(params, modelFacade);
		this.logic = new NewFileLogic(modelFacade, LogicType.NEW);
	}

	@Override
	public AbstractCliBinView getView() {
		return new NewFileBinView(this.logic);
	}
	
	@Override
	public boolean isPopUp() {
		return true;
	}

	@Override
	public Dimension getPopUpSize() {
		return new Dimension(600, 200);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public KeyStroke getKeyBinding() {
		return KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
	}

}
