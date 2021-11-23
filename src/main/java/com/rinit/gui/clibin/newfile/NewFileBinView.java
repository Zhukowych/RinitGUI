package com.rinit.gui.clibin.newfile;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.exceptions.LogicException;
import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.CLabel;
import com.rinit.gui.view.ui.RInput;

public class NewFileBinView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6483883371320067253L;

	private NewFileLogic logic;
		
    private GroupLayout layout;
    private CLabel currentPathLabel = new CLabel("Add file to: ");
    private CLabel currentPath;
    
    private CLabel nameLabel = new CLabel("name");
    private RInput fileName = new RInput(); 
    
    private CLabel extentionLabel = new CLabel("ext");
    private RInput fileExtention = new RInput(); 
	
    private CLabel positionLabel = new CLabel("pos"); 
    private RInput filePosition = new RInput(); 
	
   
    private JButton submit = new JButton("submit");
    
	public NewFileBinView(NewFileLogic logic) {
		this.logic = logic;
		this.layout = new GroupLayout(this);
		this.setLayout(this.layout);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.currentPath = new CLabel(this.logic.getPathWhereAddFile());
		
		this.setBackground(Colors.POPUP_BACKGROUND);
		
		this.constructGUI();
		this.bindListeners();
		this.getInitialData();
	}
	
	private void getInitialData() {
		NewFileSubmitData initialData = this.logic.getInitialData();
		this.filePosition.setText("0");
		if (initialData == null)
			return;
		this.fileName.setText(initialData.fileName);
		this.fileExtention.setText(initialData.fileExtention);
		this.filePosition.setText(Integer.toString(initialData.filePosition));
	}
	
	private void constructGUI() {
		this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.currentPathLabel)
						.addComponent(this.currentPath))	
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.nameLabel)
						.addComponent(this.fileName))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.extentionLabel)
						.addComponent(this.fileExtention))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.positionLabel)
						.addComponent(this.filePosition))
				.addGroup(GroupLayout.Alignment.CENTER, this.layout.createSequentialGroup()
						.addComponent(this.submit))
				);
		
		this.layout.setVerticalGroup(this.layout.createSequentialGroup()
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.currentPathLabel)
						.addComponent(this.currentPath))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.nameLabel)
						.addComponent(this.fileName))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.extentionLabel)
						.addComponent(this.fileExtention))
				.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.positionLabel)
						.addComponent(this.filePosition))
				.addGroup(this.layout.createSequentialGroup()
						.addComponent(this.submit))
				);
	}
	
	private void bindListeners() {
		this.submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				submit();
			}
		
		});
	}
	
	private void submit() {
		try {
			this.logic.submit(new NewFileSubmitData(this.fileName.getText(), this.fileExtention.getText(), Integer.parseInt(this.filePosition.getText())));
			this.popUp.dispose();
		} catch (LogicException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
}
