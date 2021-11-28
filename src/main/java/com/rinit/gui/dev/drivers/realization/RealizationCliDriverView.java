package com.rinit.gui.dev.drivers.realization;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.RComboBox;


public class RealizationCliDriverView extends AbstractCliFileDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4235405014404107321L;

	private GroupLayout layout;
	
	private JLabel extentionLabel = new JLabel("ext");
	private RComboBox<String> extentions = new RComboBox<String>();
		
	private RealizationCliDriverSubmitData initialData;
	private RealizationCliDriverLogic logic;
	private AbstractCliFileDriverView realizationView;
	
	public RealizationCliDriverView(RealizationCliDriverLogic logic) {
		this.logic = logic;
		
		this.layout = new GroupLayout(this);
		this.setLayout(layout);

		this.getInitialData();
		this.constructGUI();
	}
	
	@Override
	public void setPopUp(JDialog popUp) {
		this.logic.setDialog(popUp);
		super.setPopUp(popUp);
		this.bindListeners();
	}
	
	private void getInitialData() {
		this.initialData = this.logic.getInitialData();
		this.setExtentions();
		this.realizationView = this.initialData.realizationView;
		this.extentions.setSelectedItem(this.initialData.selectedExtention);
	}
	
	private void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.layout.setAutoCreateContainerGaps(true);
		this.layout.setAutoCreateGaps(true);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(this.layout.createSequentialGroup()
												.addComponent(extentionLabel)
												.addComponent(this.extentions))
										.addComponent(this.realizationView)
										);
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
									.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(extentionLabel)
											.addComponent(extentions))
									.addComponent(this.realizationView));

	}
	
	private void bindListeners() {
		this.popUp.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				submit();
			}
		});
		
		this.extentions.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				changeSelectedExtention();
			}
		});
	}
	
	private void changeSelectedExtention() {
		this.logic.changeRealizationTo((String)this.extentions.getSelectedItem());
		this.realizationView = this.initialData.realizationView;
		this.removeAll();
		this.revalidate();
		this.repaint();
		this.constructGUI();
		this.realizationView.setPopUp(this.popUp);
		this.popUp.setSize(this.popUp.getWidth(), this.popUp.getPreferredSize().height);
	}
	
	private void submit() {
		this.initialData.selectedExtention = (String)this.extentions.getSelectedItem();
		this.logic.submit();
	}
	
	private void setExtentions() {
		Object[] elements = this.initialData.extentions.toArray();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(Arrays.copyOf(elements, elements.length, String[].class));
		this.extentions.setModel(model);
	}
	
	
}
