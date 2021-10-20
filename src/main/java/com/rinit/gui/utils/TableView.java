package com.rinit.gui.utils;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

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
		this.table.setFont(new Font("Arial", Font.BOLD, 12));
		this.add(new JScrollPane(this.table));	
		this.configureColumnsWidth();
	}
	
	public void addRow(Vector<String> row) {
		row.add(0, "123");
		this.tableModel.addRow(row);
	}
	
	private void configureColumnsWidth() {
		 TableColumnModel columnModel = this.table.getColumnModel();
		 columnModel.getColumn(0).setPreferredWidth(50);
		 for (int i = 1; i < this.table.getColumnCount(); i++) {
			 columnModel.getColumn(i).setPreferredWidth(1000);
		 }

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
