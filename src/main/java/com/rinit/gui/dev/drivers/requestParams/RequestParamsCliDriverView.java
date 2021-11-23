package com.rinit.gui.dev.drivers.requestParams;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import com.rinit.gui.model.fileDriver.AbstractCliFileDriverView;
import com.rinit.gui.utils.TableView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.RComboBox;


public class RequestParamsCliDriverView extends AbstractCliFileDriverView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4887490744295952891L;

	private String[] methodNames = {"GET", "POST"};
	
    private GroupLayout layout;

    private JLabel methodLabel = new JLabel("Method");
    private RComboBox<String> methodSelect;
    private ParamsTabs paramsTabs = new ParamsTabs();
	private JButton addParamButton = new JButton("add paramener");
    private JButton submitButton = new JButton("save");
	
	private RequestParamsCliDriverLogic logic;
	
	public RequestParamsCliDriverView(RequestParamsCliDriverLogic logic) {
		this.logic = logic;

		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);

		this.setLayout(this.layout);
		
		this.constructGUI();
		this.bindListeners();	
		this.setInitialData();
		
	}	
	
	static class ColorArrowUI extends BasicComboBoxUI {

	    public static ComboBoxUI createUI(JComponent c) {
	        return new ColorArrowUI();
	    }

	    @Override protected JButton createArrowButton() {
	    	return new BasicArrowButton(
	                BasicArrowButton.SOUTH,
	                Colors.INPUT_BACKGROUND, Colors.INPUT_BACKGROUND,
	                Colors.INPUT_BACKGROUND, Colors.INPUT_BACKGROUND);
	    }
	}
	
	private void setInitialData() {
		RequestParamsSubmitData initialData = this.logic.getInitialData();
		this.methodSelect.setSelectedItem(initialData.method);
		this.paramsTabs.setInitialData(initialData);
	}

	private void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.methodSelect = new RComboBox<String>(this.methodNames);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.methodLabel)
						.addComponent(this.methodSelect))
				.addComponent(this.paramsTabs)
				.addGroup(GroupLayout.Alignment.TRAILING, this.layout.createSequentialGroup()
						.addComponent(this.addParamButton)
						.addComponent(this.submitButton)
						));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.methodLabel)
						.addComponent(this.methodSelect))
				.addComponent(this.paramsTabs)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.addParamButton)
						.addComponent(this.submitButton)
						));
		
	}
	
	private void bindListeners() {
		this.addParamButton.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					addParam();
				}
			}
		);
		
		this.submitButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					submit();
				}
			}
		);
	}
	
	private void addParam() {
		this.paramsTabs.addParam();
	}
	
	private void submit() {
		RequestParamsSubmitData submitData = new RequestParamsSubmitData(this.methodSelect.getSelectedItem().toString(), 
				this.paramsTabs.getGetParams(), this.paramsTabs.getPostParams());
		this.logic.submit(submitData);
		this.popUp.dispose();
	}
	
	@SuppressWarnings("serial")
	public class ParamsTabs extends JTabbedPane {
		
		private static final String GET = "GET";
		private static final String POST = "POST";
		
		public static final int GET_IND = 0;
		public static final int POST_IND = 1;
	
		private TableView getParamsTable = new TableView(new String[] {"key", "value"}) ;
		private TableView postParamsTable = new TableView(new String[] {"key", "value"});
		
		public ParamsTabs() {
			this.addTab(GET, this.getParamsTable);
			this.addTab(POST, this.postParamsTable);
		}
		
		public void addParam() {
			TableView selectedTab = this.getCurrenctTable();
			selectedTab.addRow(new String[] {"", ""});
		}
		
		public String[][] getGetParams(){
			return this.getParamsTable.getData();
		}
		
		public String[][] getPostParams(){
			return this.postParamsTable.getData();
		}
		
		public void setInitialData(RequestParamsSubmitData initialData) {
			if (initialData.getParams != null)
				this.getParamsTable.addAll(initialData.getParams);
			if (initialData.postParams != null)
				this.postParamsTable.addAll(initialData.postParams);
		}
		
		private TableView getCurrenctTable() {
			int selectedInd = this.getSelectedIndex();
			if (selectedInd == ParamsTabs.GET_IND)
				return this.getParamsTable;
			else
				return this.postParamsTable;
		}
		
	}
	
}
