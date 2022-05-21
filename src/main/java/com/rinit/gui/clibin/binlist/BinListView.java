package com.rinit.gui.clibin.binlist;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;
import com.rinit.gui.view.ui.RList;

public class BinListView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String INPUT_FOCUSED_KEY_DOWN = "INPUT_FOCUSED_KEY_DOWN";

	private static final String LIST_FOCUSED_KEY_UP = "LIST_FOCUSED_KEY_UP";
	
	private static final String LIST_FOCUSED_ENTER = "LIST_FOCUSED_ENTER";
	
	
    private GroupLayout layout;
	
    private CLabel searchBinLabel = new CLabel("Bin name:");
    private RInput searchBinInput = new RInput();
    
    DefaultListModel<String> binListModel = new DefaultListModel<String>();
    private RList binList = new RList(binListModel);
    
    private JScrollPane scroll;
    
	private BinListLogic logic;
	
	public BinListView(BinListLogic binListLogic) {
		this.logic = binListLogic;

		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setLayout(this.layout);
		
		this.constructGUI(); 
		this.bindListener();
	}
	
	public void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.binListModel.addAll(this.logic.getBinNamesList());
		this.scroll = new JScrollPane(this.binList);
		
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
									.addGroup(this.layout.createSequentialGroup()
											.addComponent(this.searchBinLabel)
											.addComponent(this.searchBinInput))
									.addComponent(this.scroll));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
									.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(this.searchBinLabel)
											.addComponent(this.searchBinInput))
									.addComponent(this.scroll));
		
	}
	
	@SuppressWarnings("serial")
	public void bindListener() {
		
		this.searchBinInput.getDocument().addDocumentListener(new DocumentListener() {
			
			  public void changedUpdate(DocumentEvent e) {
				  reInsertBinList();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  reInsertBinList();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  reInsertBinList();
			  }

		});

		this.searchBinInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), BinListView.INPUT_FOCUSED_KEY_DOWN);
		this.searchBinInput.getActionMap().put(BinListView.INPUT_FOCUSED_KEY_DOWN, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				binList.requestFocus();
			}

		});
		
		
		this.binList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), BinListView.LIST_FOCUSED_ENTER);
		this.binList.getActionMap().put(BinListView.LIST_FOCUSED_ENTER, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				runSelecterBin();
			}

		});
		
		
		String UP_LIST_ACTION = (String)this.binList.getInputMap().get(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		
		this.binList.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), BinListView.LIST_FOCUSED_KEY_UP);
		this.binList.getActionMap().put(BinListView.LIST_FOCUSED_KEY_UP, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				binList.getActionMap().get(UP_LIST_ACTION).actionPerformed(e);
				if (binList.getSelectedIndex() == 0)
					searchBinInput.requestFocus();
			}

		});
		
	}
	
	public void runSelecterBin() {
		this.popUp.dispose();
		this.logic.runBinWithName(this.binListModel.getElementAt(this.binList.getSelectedIndex()));
	}
	
	private void reInsertBinList() {
		this.binListModel.removeAllElements();
		this.binListModel.addAll(this.logic.filterBinNames(this.searchBinInput.getText()));
	}
	
}
