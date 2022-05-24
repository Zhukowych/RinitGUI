package com.rinit.gui.dev.bin.apitest.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.dev.bin.apitest.logic.ParseActionLogic;
import com.rinit.gui.utils.JFilePicker;
import com.rinit.gui.view.Colors;

public class ParseActionView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7627742254341574109L;

    private GroupLayout layout;
	
    private JFilePicker parseRootFolder = new JFilePicker("Parse root folder", "Select");
    
    private JButton parseButton = new JButton("Parse data");
    
	private ParseActionLogic logic;
	
	public ParseActionView(ParseActionLogic logic) {
		this.logic = logic;
	
		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setLayout(this.layout);
		
		this.constructGUI();
		this.bindListeners();
	}
	
	private void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.parseRootFolder.setInitialDirectory("/home/zhukowych/proj/learn/man/books");
		this.parseRootFolder.selectOnlyFolders();
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
									.addComponent(parseRootFolder)
									.addGroup(Alignment.CENTER, this.layout.createSequentialGroup()
											.addComponent(parseButton)));

		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
									.addComponent(parseRootFolder)
									.addComponent(parseButton));
		
		
		
	}
	
	private void bindListeners() {
		this.parseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				parse();
			}
		});
	}
	
	private void parse() {
		this.logic.parse(this.parseRootFolder.getSelectedFilePath());
		JOptionPane.showMessageDialog(this.popUp, "<html><h2>Parsing completed</h2>");
	}
	
}
