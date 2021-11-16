package com.rinit.gui.view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.rinit.debugger.server.dto.FileDTO;
import com.rinit.gui.event.IEventHandler;
import com.rinit.gui.model.viewModel.FilesListViewModel;
import com.rinit.gui.view.Colors;

public abstract class PanelView extends JPanel {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5893358789382661022L;

	protected IEventHandler eventHandler;
	
	 private static final Color BACKGROUND = new Color(0,65,100);
	 
	 private DefaultTableModel tableModel;
	 private JTable table;
	 private Object[] columnsNames = new String[] {"Name", "Extention", "Position"};
	 
	 private JScrollPane scroll;
	 
	 public PanelView(IEventHandler eventHandler) {
		 this.configureTable();
		 this.scroll = new JScrollPane(this.table);
		 
		 this.table.setBackground(Color.white);
		 this.table.setOpaque(true);
		 this.scroll.setBackground(Color.white);
		 this.scroll.setOpaque(true);
		 this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		 this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 this.scroll.setBorder(null);
		 
		 this.scroll.getViewport().setBackground(BACKGROUND);
		 this.table.setBackground(BACKGROUND);
		 
		 this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		 this.add(scroll);
		 this.eventHandler = eventHandler;
		 this.subscribeForListeners();
	 }
	 
	protected void updateFilesList(FilesListViewModel viewModel) {
		List<String[]> rows = this.dtosToRow(viewModel.getFiles());
		this.clearTable();
		for (String[] row : rows) {
			this.tableModel.addRow(row);
		}
		this.insertInitialElements();
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
			   /**
			 * 
			 */
			private static final long serialVersionUID = -7418983321157816641L;

			public boolean isCellEditable(int row, int column){
			        return false;
			   }	
			   
		       @Override
		       public Class<?> getColumnClass(int column) {
		        	return Double.class;
		       }
		       
		       public Component prepareRenderer( TableCellRenderer renderer, int row, int column) {
		           JComponent jc = (JComponent)super.prepareRenderer(renderer, row, column);
		    	   if (row == 0) {
		    		   jc.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colors.BORDER));
		    	   }
		           return jc;
		       }
		 };
		 this.table.setShowHorizontalLines(false);
		 this.table.setGridColor(Colors.BORDER);
		 this.table.setFocusable(false);
		 this.table.getTableHeader().setReorderingAllowed(false);
		 this.table.setRowHeight(20);
		 this.table.setFont(new Font("Consolal", Font.PLAIN, 13));
		 TableColumnModel columnModel = this.table.getColumnModel();
		 columnModel.getColumn(0).setPreferredWidth(100);
		 columnModel.getColumn(1).setPreferredWidth(500);
	 
		 this.table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		 
		 this.table.setDefaultRenderer(Double.class, new DefaultTableCellRenderer(){

            /**
			 * 
			 */
			private static final long serialVersionUID = -1000400183355825536L;

			@Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column) {
                Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                c.setForeground(Color.WHITE);
                return c;
            }
		 });
		 
		 JTableHeader header = table.getTableHeader();
		 header.setForeground(Color.YELLOW);
		 header.setFont(new Font("Consolal", Font.PLAIN, 14));
		 header.setOpaque(false);
		 header.setBackground(BACKGROUND); 
		 header.setDefaultRenderer(new HeaderRender(this.table));
		 this.insertInitialElements();
	}
	
	private void insertInitialElements() {
		for (int i = 0; i < 50; i++) {
			this.tableModel.addRow(new String[] {"", ""});
		}
	}
	 
	 private List<String[]> dtosToRow(List<FileDTO> dtos) {
		 List<String[]> rows = new ArrayList<String[]>();
		 for (FileDTO dto : dtos) {
			 String[] row = new String[] {dto.getName(),
					 					  dto.getExtention(),
					 					  Integer.toString(dto.getPosition())};
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
