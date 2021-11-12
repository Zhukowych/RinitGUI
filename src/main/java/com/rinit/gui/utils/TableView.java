package com.rinit.gui.utils;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class TableView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6095729876794139903L;
	
	public static final String DELETE = "deleteqweq";
	
	private JTable table;
	private Object[] columns;
	private DefaultTableModel tableModel;
	
	public TableView() {}
	
	public TableView(String[] columns) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.columns = this.createColumns(columns);
		this.tableModel = new DefaultTableModel();
		this.tableModel.setColumnIdentifiers(this.columns);
		this.table = new JTable(this.tableModel);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.setFont(new Font("Arial", Font.BOLD, 12));
		this.add(new JScrollPane(this.table));	
		this.bindListeners();
	}
	
	public void addRow(String[] row) {
		Vector<String> cols = new Vector<String>();
		cols.addAll(Arrays.asList(row));
		this.tableModel.addRow(cols);
	}
	
	public void addAll(String[][] rows) {
		for (String[] row : rows) 
			this.addRow(row);
	}
	
	public String[][] getData(){
		int rowCount = this.tableModel.getRowCount();
		int colCount = this.tableModel.getColumnCount();
		String[][] tableData = new String[rowCount][colCount];
		for (int row = 0; row < rowCount; row++){
			for (int col = 0; col < colCount; col++)
				tableData[row][col] = this.tableModel.getValueAt(row, col).toString();
		}
		return tableData;
	}
	
	private Object[] createColumns(String[] columns) {
		ArrayList<String> newColumns = new ArrayList<String>();
		for (String column : columns) {
			newColumns.add(column);
		}
		return newColumns.toArray();
	}
	
	@SuppressWarnings("serial")
	private void bindListeners() {
		int condition = JComponent.WHEN_FOCUSED;
		InputMap inputMap = table.getInputMap(condition);
		ActionMap actionMap = table.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE);
		actionMap.put(DELETE, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				removeSelectedRow();
			}
		});
	}
	
	private void removeSelectedRow() {
		int selectedRow = this.table.getSelectedRow();
		if (selectedRow >= 0)
			this.tableModel.removeRow(selectedRow);
	}
	
}
