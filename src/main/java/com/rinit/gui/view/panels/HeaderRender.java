package com.rinit.gui.view.panels;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class HeaderRender implements TableCellRenderer {
	
  DefaultTableCellRenderer renderer;

    public HeaderRender(JTable table) {
        renderer = (DefaultTableCellRenderer)
            table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	

}
