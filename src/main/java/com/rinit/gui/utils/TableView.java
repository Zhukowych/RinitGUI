package com.rinit.gui.utils;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class TableView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6095729876794139903L;
	
	private JTable table;
	private Object[] columns;
	private DefaultTableModel tableModel;
	
	
	public TableView(String[] columns) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.columns = this.createColumns(columns);
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(this.columns);
		this.table = new JTable(this.tableModel);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.add(new JScrollPane(this.table));	
		this.configureColumnsWidth();
	}
	
	private void configureColumnsWidth() {
		 TableColumnModel columnModel = this.table.getColumnModel();
		 System.out.println(this.getMaximumSize().getWidth());
	}
	
	private Object[] createColumns(String[] columns) {
		ArrayList<String> newColumns = new ArrayList<String>();
		newColumns.add("");
		for (String column : columns) {
			newColumns.add(column);
		}
		return newColumns.toArray();
	}
	
}
