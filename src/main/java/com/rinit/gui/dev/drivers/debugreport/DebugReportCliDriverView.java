package com.rinit.gui.dev.drivers.debugreport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.fasterxml.jackson.databind.type.LogicalType;
import com.rinit.gui.dev.bin.debugger.bin.interfaces.RequestReportCallBack;
import com.rinit.gui.dev.bin.debugger.bin.report.ReportItem;
import com.rinit.gui.dev.drivers.debugreport.driver.DebugReportDriver;
import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;
import com.rinit.gui.utils.TableView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;


public class DebugReportCliDriverView extends AbstractCliFileDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8018074753308538186L;

	private GroupLayout layout;
	
	private CLabel nameLabel = new CLabel("Name");
	private RInput name = new RInput();
	
	private CLabel typeLabel = new CLabel("Type");	
	private RInput type = new RInput();
	
	private JButton filter = new JButton("Filter");
	private JButton clearFilter = new JButton("Clear filter");
	
	private ReportsTable table = new ReportsTable();
	
	private DebugReportCliDriverLogic logic;
	
	public DebugReportCliDriverView(DebugReportCliDriverLogic logic) {
		this.logic = logic;
		
		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateContainerGaps(true);
		this.layout.setAutoCreateGaps(true);
		this.setLayout(this.layout);
		
		this.getInitialData();
		this.constructGUI();
		this.bindListener();
	}
	
	private void getInitialData() {
		try{
			DebugReportDriver report = this.logic.getInitialData();
			for (ReportItem item : report.getReportItems()) {
				this.table.addReport(item);
			}
		}catch (Exception e) {}
	}
	
	private void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
									.addGroup(this.layout.createSequentialGroup()
											.addComponent(this.nameLabel)
											.addComponent(this.name)
											.addComponent(this.typeLabel)
											.addComponent(this.type)
											.addComponent(this.filter)
											.addComponent(this.clearFilter))
									.addComponent(this.table));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
									.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(this.nameLabel)
											.addComponent(this.name)
											.addComponent(this.typeLabel)
											.addComponent(this.type)
											.addComponent(this.filter)
											.addComponent(this.clearFilter))
									.addComponent(this.table));
	}
	
	public void bindListener() {
		this.filter.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				filter();
			}
		});
		this.clearFilter.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				clearFilter();
			}
		});
	}
	
	public RequestReportCallBack getTableCallBack() {
		return this.table;
	}
	
	private void filter() {
		this.table.removeAll();
		for (ReportItem item : this.table.getItems()) {
			if (item.elementName.contains(this.name.getText()) && item.elementType.contains(this.type.getText()))
				this.table.addVisibleItems(item);
		}
	}
	
	private void clearFilter() {
		this.table.removeAll();
		this.name.setText("");
		this.type.setText("");
		for (ReportItem item : this.table.getItems()) {
			this.table.addVisibleItems(item);
		}
	}
	
	@SuppressWarnings("serial")
	public class ReportsTable extends TableView implements RequestReportCallBack {
			
		
		private static final String OPEN_FULL_REPORT = "OPEN_FULL_REPORT";
		private static final String OPEN_ELEMENT = "OPEN_ELEMENT";		
		
		private List<ReportItem> reportItems = new ArrayList<ReportItem>();
		
		public ReportsTable() {
			super(new String[] {"Element Name", "Element Type", "short report", "time"});
			this.bindListeners();
		}

		@Override
		public void addReport(ReportItem reportItem) {
			this.reportItems.add(reportItem);
			this.addRow(reportItem.toRow());
		}
		
		public void addVisibleItems(ReportItem item) {
			this.addRow(item.toRow());
		}
		
		public List<ReportItem> getItems(){
			return this.reportItems;
		}

		public void bindListeners() {
			int condition = JComponent.WHEN_FOCUSED;
			InputMap inputMap = table.getInputMap(condition);
			ActionMap actionMap = table.getActionMap();

			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), OPEN_FULL_REPORT);
			actionMap.put(OPEN_FULL_REPORT, new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() >= 0) openFullReport();
				}
			});

			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK), OPEN_ELEMENT);
			actionMap.put(OPEN_ELEMENT, new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() >= 0) openElement();
				}
			});
			
		}
		
		private void openFullReport() {
			ReportItem reportItem = this.reportItems.get(this.table.getSelectedRow());
		
		}
		
		private void openElement() {
			ReportItem reportItem = this.reportItems.get(this.table.getSelectedRow());
			logic.openFile(reportItem);
		}
		
	}
	
}
