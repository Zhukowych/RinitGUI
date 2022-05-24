package com.rinit.gui.dev.drivers.codevalidator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;

import com.rinit.gui.dev.drivers.abstraction.AbstractionCliDriverView;
import com.rinit.gui.dev.drivers.codevalidator.dirver.CodeValidatorDriver;
import com.rinit.gui.dev.drivers.graphql.driver.GraphqlDriver;
import com.rinit.gui.utils.JFilePicker;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;

public class CodeValidatorCliDriverView extends AbstractionCliDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1540888820468144961L;

	private GroupLayout layout;

	private JFilePicker validatorClassFile = new JFilePicker("Validator class file", "Browse");

	private CLabel validatorClassNameLabel = new CLabel("Validator class name:");
	private RInput validatorClassName = new RInput();
	
	private JButton saveButton = new JButton("Save");
	
	private CodeValidatorCliDriverLogic logic;

	public CodeValidatorCliDriverView(CodeValidatorCliDriverLogic logic) {
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
		CodeValidatorDriver initialData = this.logic.getInitialData();
		this.validatorClassFile.setSelectedFilePath(initialData.getValidatorClassPath());
		this.validatorClassName.setText(initialData.getValidatroClassName());
	}
	
	public void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		this.validatorClassFile.setMode(JFilePicker.MODE_OPEN);
		this.validatorClassFile.selectOnlyFolders();

		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
				.addComponent(this.validatorClassFile)
				.addGroup(this.layout.createSequentialGroup()
						  .addComponent(this.validatorClassNameLabel)
						  .addComponent(this.validatorClassName))
				.addGroup(Alignment.CENTER, 
						this.layout.createSequentialGroup().addComponent(this.saveButton)));
		
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addComponent(this.validatorClassFile)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						  .addComponent(this.validatorClassNameLabel)
						  .addComponent(this.validatorClassName))
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
		CodeValidatorDriver codeValidator = new CodeValidatorDriver();
		codeValidator.setValidatorClassPath(this.validatorClassFile.getSelectedFilePath());
		codeValidator.setValidatroClassName(this.validatorClassName.getText());
		this.logic.save(codeValidator);
		this.popUp.dispose();
	}
	
}
