package com.rinit.gui.dev.bin.apitest.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.dev.bin.apitest.logic.ChangesActionLogic;
import com.rinit.gui.dev.drivers.change.driver.ChangeDriver;
import com.rinit.gui.utils.TableView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RTextArea;

public class ChangesActionView extends AbstractCliBinView {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1611153412030594754L;

	public static final String PROCESS_CHANGE = "process";
	public static final String SHOW_CHANGE = "show";
	
	
	private GroupLayout layout;
	
    private TableView changesTable = new TableView(new String[] {"Object key", "Message"});
    
	private ChangesActionLogic logic;
	
	public ChangesActionView(ChangesActionLogic logic) {
		this.logic = logic;

		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setLayout(this.layout);
		
		this.constructGUI();
		this.bindListeners();
		this.bindInitialData();
	}

	private void constructGUI() {
		this.setBackground(Colors.BACKGROUD);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
				.addComponent(this.changesTable));

		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addComponent(this.changesTable));
		
	}
	
	private void bindListeners() {
		this.changesTable.getTable().getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), PROCESS_CHANGE);
		this.changesTable.getTable().getActionMap().put(PROCESS_CHANGE, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processChange();
			}
		});
		
		this.changesTable.getTable().getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), SHOW_CHANGE);
		this.changesTable.getTable().getActionMap().put(SHOW_CHANGE, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showChange();
			}
		});
		
	}
	
	public void processChange() {
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you processed this change", "?", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			this.logic.deleteChangeAtInd(changesTable.getTable().getSelectedRow());
			this.changesTable.removeSelectedRow();
		}
	}
	
	private void bindInitialData() {
		for (ChangeDriver change : this.logic.getChanges()) {
			this.changesTable.addRow(new String[] {change.getChangedObjectPath(), change.getMessage()} );
		}
	}
	
	private void showChange() {
		ChangeView view = new ChangeView(this.logic.getChangeAt(this.changesTable.getTable().getSelectedRow()));
		view.setVisible(true);
	}
	
	public class ChangeView extends JDialog {
		
		private GroupLayout layout;
		
		private CLabel previousLabel = new CLabel("Previous");
		private RTextArea preious = new RTextArea(10);
		
		private CLabel currentLabel = new CLabel("Current");
		private RTextArea current = new RTextArea(10);
		
		
		private ChangeDriver change;
		
		public ChangeView(ChangeDriver change) {	
			super(popUp, "Change", true);	
			
			this.layout = new GroupLayout(this.getContentPane());
			this.layout.setAutoCreateContainerGaps(true);
			this.layout.setAutoCreateGaps(true);
			this.getContentPane().setLayout(this.layout);
			
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setSize(400, 600);
			
			final Toolkit toolkit = Toolkit.getDefaultToolkit();
			final Dimension screenSize = toolkit.getScreenSize();
			final int x = (int) ((screenSize.width - this.getWidth()) / 2);
			final int y = (int) ((screenSize.height - this.getHeight()) / 2);
			this.setLocation(x, y);
		
			this.change = change;
			
			this.constructGUI();
			this.bindInitialData();
			
		}
		
		public RTextArea getPreious() {
			return preious;
		}

		public RTextArea getCurrent() {
			return current;
		}

		private void constructGUI() {
			this.setBackground(Colors.POPUP_BACKGROUND);
			
			this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
								.addComponent(this.previousLabel)
								.addComponent(this.preious)
								.addComponent(this.currentLabel)
								.addComponent(this.current));

			this.layout.setVerticalGroup(this.layout.createSequentialGroup()
								.addComponent(this.previousLabel)
								.addComponent(this.preious)
								.addComponent(this.currentLabel)
								.addComponent(this.current));
		}
		
		private void bindInitialData() {
			this.preious.setText(this.change.getPreviousValue());
			this.current.setText(this.change.getCurrentValue());	
		}
		
		
		
	}
	
}
