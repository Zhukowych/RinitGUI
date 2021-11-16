package com.rinit.gui.clibin.newfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.rinit.gui.clibin.AbstractCliBinView;
import com.rinit.gui.exceptions.LogicException;

public class NewFileBinView extends AbstractCliBinView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6483883371320067253L;

	private NewFileLogic logic;
	
    private GroupLayout layout;
    private JLabel currentPathLabel = new JLabel("Add file to: ");
    private JLabel currentPath;
    
    private JLabel nameLabel = new JLabel("name");
    private JTextField fileName = new JTextField(); 
    
    private JLabel extentionLabel = new JLabel("ext");
    private JTextField fileExtention = new JTextField(); 
	
    private JLabel positionLabel = new JLabel("pos");
    private JTextField filePosition = new JTextField(); 
	
   
    private JButton submit = new JButton("submit");
    
	public NewFileBinView(NewFileLogic logic) {
		this.logic = logic;
		this.layout = new GroupLayout(this);
		this.setLayout(this.layout);
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
		
		this.currentPath = new JLabel(this.logic.getPathWhereAddFile());
		
		this.constructGUI();
		this.bindListeners();
		this.getInitialData();
	}
	
	private void getInitialData() {
		NewFileSubmitData initialData = this.logic.getInitialData();
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
				.addGroup(this.layout.createSequentialGroup()
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
