package com.rinit.gui.view.panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.viewModel.FilesListViewModel;
import com.rinit.gui.model.viewModel.SelectionViewModel;
import com.rinit.gui.utils.ConvertionUtils;

public abstract class PanelView extends JPanel {
	
	 protected IEventHandler eventHandler;
	
	 private DefaultTableModel tableModel;
	 private JTable table;
	 private Object[] columnsNames = new String[] {"Id", "name", "extention"};
	 
	 public PanelView(IEventHandler eventHandler) {
		 this.configureTable();
		 this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		 this.add(new JScrollPane(this.table));
		 this.eventHandler = eventHandler;
		 this.subscribeForListeners();
	 }
	 
	protected void updateFilesList(FilesListViewModel viewModel) {
		List<String[]> rows = this.dtosToRow(viewModel.getFiles());
		this.clearTable();
		for (String[] row : rows) {
			this.tableModel.addRow(row);
		}
	}
	
	protected void selectTable(FilesListViewModel viewModel) {
		this.table.setRowSelectionInterval(viewModel.getSelectedIndex(), viewModel.getSelectedIndex());
	}
	
	protected void unSelectTable() {
		this.table.clearSelection();
	}
	
	abstract void subscribeForListeners();
	
	private void configureTable() {
		 this.tableModel = new DefaultTableModel();
		 this.tableModel.setColumnIdentifiers(columnsNames);
		 this.table = new JTable(tableModel) {	
			   public boolean isCellEditable(int row, int column){
			        return false;
			   }	
		 };
		 this.table.setFocusable(false);
		 this.table.getTableHeader().setReorderingAllowed(false);
		 this.table.setShowHorizontalLines(false);
		 TableColumnModel columnModel = this.table.getColumnModel();
		 columnModel.getColumn(0).setPreferredWidth(100);
		 columnModel.getColumn(1).setPreferredWidth(500);
		 columnModel.getColumn(2).setPreferredWidth(500);
	 }
	 
	 private List<String[]> dtosToRow(List<FileDTO> dtos) {
		 List<String[]> rows = new ArrayList<String[]>();
		 for (FileDTO dto : dtos) {
			 String[] row = new String[] {ConvertionUtils.toString(dto.getId()),
					 					  dto.getName(),
					 					  dto.getExtention()};
			 rows.add(row);
		 }
		 return rows;
	 }
	 
	 private void clearTable() {
		int rowCount = this.tableModel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    this.tableModel.removeRow(i);
		}
	 }
	 
}
