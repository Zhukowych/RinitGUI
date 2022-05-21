package com.rinit.gui.dev.drivers.apitestconfig;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.rinit.gui.model.fileDriver.AbstractCliDriverView;
import com.rinit.gui.utils.JFilePicker;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;
import com.rinit.gui.view.ui.RTextArea;

public class ApiTestConfigCliDriverView extends AbstractCliDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3924718759612930267L;

	private GroupLayout layout;

    private JFilePicker queryFindFolder = new JFilePicker("Query find folder", "Browse");
    
    private CLabel queryesFileNameLabel = new CLabel("Queries file name");
	private RInput queryesFileName = new RInput();	
	
	private CLabel variablesFileNameLabel = new CLabel("Variables file name");
	private RInput variablesFileName = new RInput();	
	
    private CLabel queryNameParseRegexLabel = new CLabel("Query name parse regex:");
    private RTextArea requiredContent = new RTextArea();
	
	private ApiTestConfigCliDriverLogic logic;
	
	public ApiTestConfigCliDriverView(ApiTestConfigCliDriverLogic logic) {
		this.logic = logic;

		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateContainerGaps(true);
		this.layout.setAutoCreateGaps(true);
		this.setLayout(this.layout);
		
		this.constructGUI();
		this.bindListeners();
	
	}
	
	public void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.queryFindFolder.setMode(JFilePicker.MODE_SAVE);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
						.addComponent(this.queryFindFolder)
						.addGroup(this.layout.createSequentialGroup()
								.addComponent(this.queryesFileNameLabel)
								.addComponent(this.queryesFileName))
						.addGroup(this.layout.createSequentialGroup()
								.addComponent(this.variablesFileNameLabel)
								.addComponent(this.variablesFileName)));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addComponent(queryFindFolder)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.queryesFileNameLabel)
						.addComponent(this.queryesFileName))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.variablesFileNameLabel)
						.addComponent(this.variablesFileName)));	
	
	}
	
	public void bindListeners() {
		
	}
	
}
