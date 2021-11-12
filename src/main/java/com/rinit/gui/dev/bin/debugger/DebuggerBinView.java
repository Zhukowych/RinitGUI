package com.rinit.gui.dev.bin.debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JProgressBar;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.dev.bin.debugger.bin.RequestReportCallBack;
import com.rinit.gui.dev.bin.debugger.bin.ShortReport;
import com.rinit.gui.utils.TableView;

public class DebuggerBinView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5954712694648402759L;


    private GroupLayout layout;
    
    private JButton runButton = new JButton("Run");
    private JButton stopButton = new JButton("Stop");
    private ProgressTable progressTable = new ProgressTable();
    private TestProgressBar progressBar = new TestProgressBar();
	
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
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.progressBar)
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.progressTable))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.runButton)
						.addComponent(this.stopButton))
				);
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addComponent(this.progressBar)
				.addComponent(this.progressTable)
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
		this.logic.runTest(this.progressTable);
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
	
	@SuppressWarnings("serial")
	public static class ProgressTable extends TableView implements RequestReportCallBack {

		private static final String[] COLUMNS = new String[] {"Element Name", "Element Type", "short report", "time"};
		
		public ProgressTable() {
			super(COLUMNS);
		}

		@Override
		public void addReport(ShortReport shortReport) {
			System.out.println(shortReport);
		}
		
	}
}
