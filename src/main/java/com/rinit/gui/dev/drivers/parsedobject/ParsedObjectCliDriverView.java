package com.rinit.gui.dev.drivers.parsedobject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.rinit.gui.dev.drivers.parsedobject.dirver.ParsedObjectDriver;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;
import com.rinit.gui.view.ui.RTextArea;

public class ParsedObjectCliDriverView extends AbstractCliDriverView {
	
	private GroupLayout layout;
	
	private CLabel keyLabel = new CLabel("Key");
	private RInput key = new RInput();
	
	private List<ParsedObjectValueView> valueViews = new ArrayList<ParsedObjectValueView>();
	
	private JButton saveButton = new JButton("Save");
	
	private ParsedObjectCliDriverLogic logic;
	
	public ParsedObjectCliDriverView(ParsedObjectCliDriverLogic logic) {
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
		ParsedObjectDriver parsedObject = this.logic.getInitialData();
		
		this.key.setText(parsedObject.getKey());
		for (Entry<String, String> entry : parsedObject.getValues().entrySet()) {
			this.valueViews.add(
				new ParsedObjectValueView(
						new ValueViewModel(entry.getKey(), entry.getValue()
				)));
		}
	
	}
	
	private void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);

		ParallelGroup horizontalGroup = this.layout.createParallelGroup(Alignment.LEADING)
				.addGroup(this.layout.createSequentialGroup()
						  .addComponent(this.keyLabel)
						  .addComponent(this.key));
		for (ParsedObjectValueView view : this.valueViews) 
			horizontalGroup = horizontalGroup.addComponent(view);
		horizontalGroup = horizontalGroup.addGroup(Alignment.CENTER, this.layout.createSequentialGroup().addComponent(this.saveButton));
				
		this.layout.setHorizontalGroup(horizontalGroup);
		
		SequentialGroup verticalGroup = this.layout.createSequentialGroup()
							.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(this.keyLabel)
								.addComponent(this.key));
		
		for (ParsedObjectValueView view : this.valueViews) 
			verticalGroup = verticalGroup.addComponent(view);
		
		verticalGroup.addComponent(saveButton);
		
		this.layout.setVerticalGroup(verticalGroup);
	
		
	}
	
	private void bindListeners() {
		this.saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
	}
	
	private void save() {
		ParsedObjectDriver parsedObject = this.logic.getInitialData();
		for (ParsedObjectValueView view : this.valueViews) 
			parsedObject.addValue(view.getValue().getValueType(), view.getValue().getValueContent());
		this.logic.submit();
	}
	
	@SuppressWarnings("serial")
	public class ParsedObjectValueView extends JPanel {
		
		private GroupLayout layout;
		
		private CLabel valueLabel = new CLabel("");
		private RTextArea valueContent = new RTextArea(5);
		
		private ValueViewModel value;
		
		public ParsedObjectValueView(ValueViewModel value) {
			this.value = value;
	
			this.layout = new GroupLayout(this);
			this.layout.setAutoCreateGaps(true);
			this.setLayout(this.layout);
			
			this.constructGUI();
			this.bindInitialData();
		}
		
		private void bindInitialData() {
			this.valueLabel.setText(this.value.getValueType());
			this.valueContent.setText(this.value.getValueContent());
		}
		
		private void constructGUI() {
			this.setBackground(Colors.POPUP_BACKGROUND);

			this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
										.addComponent(this.valueLabel)
										.addComponent(this.valueContent));

			this.layout.setVerticalGroup(this.layout.createSequentialGroup()
										.addComponent(this.valueLabel)
										.addComponent(this.valueContent));
	
		}	
		
		public ValueViewModel getValue() {
			this.value.setValueContent(this.valueContent.getText());
			return this.value;
		}
		
	}
	
}
