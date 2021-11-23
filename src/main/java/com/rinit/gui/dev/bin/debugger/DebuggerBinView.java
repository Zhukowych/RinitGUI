package com.rinit.gui.dev.bin.debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JProgressBar;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.dev.drivers.debugreport.DebugReportCliDriverView;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;

public class DebuggerBinView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5954712694648402759L;


    private GroupLayout layout;
    
    private CLabel debugRunLabel = new CLabel("Debug run name");
    private RInput debugRun = new RInput();
    
    private JButton runButton = new JButton("Run");
    private JButton stopButton = new JButton("Stop");
    
	private DebugReportCliDriverView table;
    
	private DebuggerBinLogic logic;
	
	public DebuggerBinView(DebuggerBinLogic logic) {
		this.logic = logic;

		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.setLayout(this.layout);

		this.setInititalData();
		this.constructGUI();
		this.bindListeners();
	
	}
	
	public void setInititalData() {
		
	}
	
	public void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		this.table = new DebugReportCliDriverView(null);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(this.layout.createSequentialGroup()
						 .addComponent(this.debugRunLabel)
						 .addComponent(this.debugRun))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.table))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.runButton)
						.addComponent(this.stopButton))
				);
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.debugRunLabel)
						.addComponent(this.debugRun))
				.addComponent(this.table)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.runButton)
						.addComponent(this.stopButton))
				);
	}
	
	public void bindListeners() {
		
		this.runButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				runTest();
			}
		
		});
		
	}
	
	private void runTest() {
		RunData runData = new RunData(this.table.getTableCallBack(), this.debugRun.getText());
		this.logic.runTest(runData);
	}
	
	@SuppressWarnings("serial")
	public class TestProgressBar extends JProgressBar {
		
		private int currentValue = 0;
		
		public TestProgressBar() {
			this.setStringPainted(true);
			this.setMinimum(0);
		}
		
		public void increment() {
			this.currentValue++;
			this.setValue(this.currentValue);
		}
		
	}
	
}
