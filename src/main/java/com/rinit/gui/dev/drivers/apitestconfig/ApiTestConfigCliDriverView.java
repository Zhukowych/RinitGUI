package com.rinit.gui.dev.drivers.apitestconfig;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.MouseInputAdapter;

import com.rinit.gui.dev.drivers.apitestconfig.driver.ApiTestConfiigDriver;
import com.rinit.gui.dev.drivers.apitestconfig.driver.FileToParse;
import com.rinit.gui.model.fileDriver.AbstractCliDriverView;
import com.rinit.gui.utils.JFilePicker;
import com.rinit.gui.utils.TableView;
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

    private JFilePicker rootParseFolderPath = new JFilePicker("Parse root folder", "Browse");
    
	private TableView filesToParseTable = new TableView(new String[] {"name", "fileNameRegex", "keyRegex", "valueRegex"});
	private JButton addFileToParseButton = new JButton("Add file to parse");

	private JButton saveButton = new JButton("Save");
	
	private ApiTestConfigCliDriverLogic logic;
	
	public ApiTestConfigCliDriverView(ApiTestConfigCliDriverLogic logic) {
		this.logic = logic;

		this.layout = new GroupLayout(this);
		this.layout.setAutoCreateContainerGaps(true);
		this.layout.setAutoCreateGaps(true);
		this.setLayout(this.layout);
		
		this.constructGUI();
		this.bindListeners();
		this.bindInitialData();
	}
	
	public void constructGUI() {
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.rootParseFolderPath.setMode(JFilePicker.MODE_OPEN);
		
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING) 
						.addComponent(this.rootParseFolderPath)
						.addComponent(filesToParseTable)
						.addGroup(Alignment.CENTER, this.layout.createSequentialGroup()
								.addComponent(addFileToParseButton)
								.addComponent(saveButton)));
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addComponent(this.rootParseFolderPath)
				.addComponent(filesToParseTable)
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(addFileToParseButton)
							.addComponent(saveButton)));	
	
	}
	
	public void bindListeners() {
		
		this.addFileToParseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFileToParse();
			}			
		});	
		
		this.filesToParseTable.getTable().addMouseListener(new MouseInputAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = filesToParseTable.rowAtPoint(evt.getPoint());
		        int col = filesToParseTable.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		        	updateFileToParse(row);
		        }
		    }
		});
		
		this.saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submit();
			}		
		});
		
	}
	
	private void bindInitialData() {
		ApiTestConfiigDriver initialData = this.logic.getInitialData();
		this.rootParseFolderPath.setSelectedFilePath(initialData.getRootParseFolderPath());
		
		for (FileToParse fileToParse : initialData.getFilesToParse()) {
			this.filesToParseTable.addRow(new String[] {
				fileToParse.getName(),
				fileToParse.getFileNameRegex(),
				fileToParse.getKeyRegex(),
				fileToParse.getValueRegex()
			});
		}
		
	}
	
	public void addFileToParse() {
		FileToParseDialog fileToParseDialog = new FileToParseDialog();
		fileToParseDialog.setVisible(true);

		this.filesToParseTable.addRow(new String[] {
				fileToParseDialog.getFileTypeName().getText(),
				fileToParseDialog.getFileNameRegex().getText(),
				fileToParseDialog.getKeyRegex().getText(),
				fileToParseDialog.getValueRegex().getText()
		});
	}
	
	public void updateFileToParse(int tableRowInd) {
		FileToParseDialog fileToParseDialog = new FileToParseDialog();
		fileToParseDialog.getFileTypeName().setText(this.filesToParseTable.getDataAt(tableRowInd, 0));
		fileToParseDialog.getFileNameRegex().setText(this.filesToParseTable.getDataAt(tableRowInd, 1));
		fileToParseDialog.getKeyRegex().setText(this.filesToParseTable.getDataAt(tableRowInd, 2));
		fileToParseDialog.getValueRegex().setText(this.filesToParseTable.getDataAt(tableRowInd, 3));
		fileToParseDialog.setVisible(true);
		
		this.filesToParseTable.setValueAt(tableRowInd, 0, fileToParseDialog.getFileTypeName().getText());
		this.filesToParseTable.setValueAt(tableRowInd, 1, fileToParseDialog.getFileNameRegex().getText());
		this.filesToParseTable.setValueAt(tableRowInd, 2, fileToParseDialog.getKeyRegex().getText());
		this.filesToParseTable.setValueAt(tableRowInd, 3, fileToParseDialog.getValueRegex().getText());

	}
	
	private void submit() {
		ApiTestConfiigDriver apiTestConfig = new ApiTestConfiigDriver();
		apiTestConfig.setRootParseFolderPath(this.rootParseFolderPath.getSelectedFilePath());
		for (String[] fileToParseRow : this.filesToParseTable.getData()) {
			FileToParse fileToParse = new FileToParse();
			fileToParse.setName(fileToParseRow[0]);
			fileToParse.setFileNameRegex(fileToParseRow[1]);
			fileToParse.setKeyRegex(fileToParseRow[2]);
			fileToParse.setValueRegex(fileToParseRow[3]);
			apiTestConfig.addFileToParse(fileToParse);
		}
		this.logic.submit(apiTestConfig);
		this.popUp.dispose();
	}
	
	public class FileToParseDialog extends JDialog {
				
		private GroupLayout layout;
		
		private CLabel nameLabel = new CLabel("Name:");
		private RInput name = new RInput();

		private CLabel fileNameRegexLabel = new CLabel("File name regex");
		private RTextArea fileNameRegex = new RTextArea(5);
		
		private CLabel keyRegexLabel = new CLabel("Key regex");
		private RTextArea keyRegex = new RTextArea(5);

		private CLabel valueRegexLabel = new CLabel("Value regex");
		private RTextArea valueRegex = new RTextArea(5);		
		
		private JButton saveButton = new JButton("save");
				
		public FileToParseDialog() {
			super(popUp, "Add file to parse", true);	
			this.layout = new GroupLayout(this.getContentPane());
			this.layout.setAutoCreateContainerGaps(true);
			this.layout.setAutoCreateGaps(true);
			this.getContentPane().setLayout(this.layout);
			
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setSize(400, 600);
			
			final Toolkit toolkit = Toolkit.getDefaultToolkit();
			final Dimension screenSize = toolkit.getScreenSize();
			final int x = (int) ((screenSize.width - this.getWidth()) / 2);
			final int y = (int) ((screenSize.height - this.getHeight()) / 2);
			this.setLocation(x, y);
			
			this.constructGUI();
			this.bindListeners();

		}
		
		public RInput getFileTypeName() {
			return name;
		}
		
		public RTextArea getFileNameRegex() {
			return fileNameRegex;
		}

		public RTextArea getKeyRegex() {
			return keyRegex;
		}

		public RTextArea getValueRegex() {
			return valueRegex;
		}

		private void constructGUI() {
			this.setBackground(Colors.POPUP_BACKGROUND);
			
			this.layout.setHorizontalGroup(this.layout.createParallelGroup(Alignment.LEADING)
								.addGroup(this.layout.createSequentialGroup()
										  .addComponent(this.nameLabel)
										  .addComponent(this.name))
								.addComponent(fileNameRegexLabel)
								.addComponent(fileNameRegex)
								.addComponent(keyRegexLabel)
								.addComponent(keyRegex)
								.addComponent(valueRegexLabel)
								.addComponent(valueRegex)
								.addGroup(Alignment.CENTER, 
										this.layout.createSequentialGroup().addComponent(this.saveButton))
								);

			this.layout.setVerticalGroup(this.layout.createSequentialGroup()
									.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(this.nameLabel)
											.addComponent(this.name))
									.addComponent(fileNameRegexLabel)
									.addComponent(fileNameRegex)
									.addComponent(keyRegexLabel)
									.addComponent(keyRegex)
									.addComponent(valueRegexLabel)
									.addComponent(valueRegex)
									.addComponent(saveButton)
								);
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
			this.dispose();
		}
				
	}
		

		
	
}
