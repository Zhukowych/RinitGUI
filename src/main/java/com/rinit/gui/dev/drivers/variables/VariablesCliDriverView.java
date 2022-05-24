package com.rinit.gui.dev.drivers.variables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import com.rinit.gui.dev.drivers.variables.driver.VariablesDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;
import com.rinit.gui.utils.TableView;
import com.rinit.gui.view.Colors;

public class VariablesCliDriverView extends AbstractCliDriverView {
	
	private GroupLayout layout;

	private TableView variablesTable = new TableView(new String[] {"name", "value"});
	
	private JButton addVariable = new JButton("Add variable");
	private JButton save = new JButton("Save");
	
	private VariablesCliDriverLogic logic;
	
	public VariablesCliDriverView(VariablesCliDriverLogic logic) {
		this.logic = logic;
		
		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateContainerGaps(true);
		this.layout.setAutoCreateGaps(true);
		this.setLayout(this.layout);
		
		this.constructGUI();
		this.bindListeners();
		this.bindInitialData();
	}
	
	private void bindInitialData() {
		VariablesDriver initialData = this.logic.getInitialData();
		for (Entry<String, String> entry : initialData.getValues().entrySet()) {
			this.variablesTable.addRow(new String[] {entry.getKey(), entry.getValue()});
		}
	}
	
	public void constructGUI() {

		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
				.addComponent(this.variablesTable)
				.addGroup(Alignment.CENTER, this.layout.createSequentialGroup()
						.addComponent(addVariable)
						.addComponent(save)));

		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
			.addComponent(this.variablesTable)
			.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(addVariable)
						.addComponent(save)));	

	}
	
	public void bindListeners() {
		
		this.addVariable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addVariable();
			}
		});
		
		this.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
	}
	
	private void addVariable() {
		this.variablesTable.addRow(new String[] {"", ""});
	}

	private void save() {
		VariablesDriver variables = new VariablesDriver();
		for (String[] row : this.variablesTable.getData()) {
			if(!row[0].isEmpty() && !row[1].isEmpty())
				variables.setVariable(row[0], row[1]);
		}
		this.logic.save(variables);
		this.popUp.dispose();
	}
	
}
