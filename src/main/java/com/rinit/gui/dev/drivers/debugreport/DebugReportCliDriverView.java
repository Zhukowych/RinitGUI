package com.rinit.gui.dev.drivers.debugreport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.rinit.gui.dev.bin.debugger.bin.RequestReportCallBack;
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
	public static class ReportsTable extends TableView implements RequestReportCallBack {
		
		private List<ReportItem> reportItems = new ArrayList<ReportItem>();
		private static final String[] COLUMNS = new String[] {"Element Name", "Element Type", "short report", "time"};
		
		public ReportsTable() {
			super(COLUMNS);
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
			this.table.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			        int row = table.rowAtPoint(evt.getPoint());
			        if (row >= 0 ) {
			        	JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(table),  reportItems.get(row).fullReport);
			        }
			    }
			});
		}
		
	}
	
}
