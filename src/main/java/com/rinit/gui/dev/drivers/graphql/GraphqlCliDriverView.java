package com.rinit.gui.dev.drivers.graphql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import com.rinit.gui.dev.drivers.graphql.driver.GraphqlDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;

public class GraphqlCliDriverView extends AbstractCliDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4208716911045525024L;
	
	private GroupLayout layout;

	private CLabel queryNameLabel = new CLabel("Query name:");
	private RInput queryName = new RInput();

	private CLabel apiUrlLabel = new CLabel("Api url:");
	private RInput apiUrl = new RInput();	
	
	private JButton saveButton = new JButton("Save");
	
	private GraphqlCliDriverLogic logic;
	
	public GraphqlCliDriverView(GraphqlCliDriverLogic logic) {
		this.logic = logic;

		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateContainerGaps(true);
		this.layout.setAutoCreateGaps(true);
		this.setLayout(this.layout);	

		this.bindInitialData();
		this.constructGUI();
		this.bindListeners();
	}

	private void bindInitialData() {
		GraphqlDriver initialData = this.logic.getReadingFile();
		this.apiUrl.setText(initialData.getApiUrl());
		this.queryName.setText(initialData.getQueryName());	
	}
	
	public void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);

		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
				.addGroup(this.layout.createSequentialGroup()
						  .addComponent(this.queryNameLabel)
						  .addComponent(this.queryName))
				.addGroup(this.layout.createSequentialGroup()
						  .addComponent(this.apiUrlLabel)
						  .addComponent(this.apiUrl))
				.addGroup(Alignment.CENTER, 
						this.layout.createSequentialGroup().addComponent(this.saveButton)));
		
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.queryNameLabel)
						.addComponent(this.queryName))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.apiUrlLabel)
						.addComponent(this.apiUrl))
				.addComponent(saveButton));
	}
	
	public void bindListeners() {
		this.saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
	}
	
	private void save() {
		GraphqlDriver graphql = new GraphqlDriver();
		graphql.setApiUrl(this.apiUrl.getText());
		graphql.setQueryName(this.queryName.getText());
		this.logic.save(graphql);
		this.popUp.dispose();
	}
	
}
