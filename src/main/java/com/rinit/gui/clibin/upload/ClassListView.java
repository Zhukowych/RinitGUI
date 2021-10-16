package com.rinit.gui.clibin.upload;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.rinit.gui.utils.TableView;

public class ClassListView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4047998938278638653L;
	
	private TableView table;
	
	public ClassListView() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.table = new TableView(new String[]{"name", "class path"});
		this.add(table);
	}
	
	
}
