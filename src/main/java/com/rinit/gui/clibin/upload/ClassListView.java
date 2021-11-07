package com.rinit.gui.clibin.upload;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.rinit.gui.utils.TableView;

public class ClassListView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4047998938278638653L;
	
	private TableView table;
	private JButton addClassButton = new JButton("Add");
	
	
	public ClassListView() {
		this.constructGUI();
		this.bindListeners();
	}
	
	private void constructGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.table = new TableView(new String[]{"name", "class path"});
		this.table.setFont(new Font("Serif", Font.BOLD, 40));
		this.addClassButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.table.setAlignmentX(Component.LEFT_ALIGNMENT);

		this.add(this.addClassButton);
		this.add(table);
	}
	
	private void bindListeners() {
		this.addClassButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				addClass();
			}
		
		});
	}
	
	private void addClass() {
		this.table.addRow(new String[] {""});
	}
	
	private Vector<String> createEmptyVector(){
		Vector<String> row = new Vector<String>();
		return row;
	}
	
}
