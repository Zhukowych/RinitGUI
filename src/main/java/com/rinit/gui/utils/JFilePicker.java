package com.rinit.gui.utils;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rinit.gui.view.Colors;
import com.rinit.gui.view.ui.RInput;
 
public class JFilePicker extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3480111468892213262L;
	
	private String textFieldLabel;
    private String buttonLabel;
     
    private JLabel label;
    private RInput textField;
    private JButton button;
    
    private GroupLayout layout; 
    
    private JFileChooser fileChooser;
     
    private int mode;
    private String initialDirectory;
    
    public boolean selectOnlyDirectory = false;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;
    
    
    public JFilePicker(String textFieldLabel, String buttonLabel) {
    	this.setBackground(Colors.POPUP_BACKGROUND);
        this.layout = new GroupLayout(this);
        this.setLayout(this.layout);
        
    	this.textFieldLabel = textFieldLabel;
    	this.buttonLabel = buttonLabel;
         
        
        // creates the GUI
        label = new JLabel(this.textFieldLabel); 
        textField = new RInput();
        button = new JButton(this.buttonLabel);
         
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);            
            }
        });
         
        this.layout.setHorizontalGroup(this.layout.createSequentialGroup()
        		.addComponent(this.label)
        		.addGap(5)
        		.addComponent(this.textField)
        		.addGap(5)
        		.addComponent(this.button));
        
        this.layout.setVerticalGroup(this.layout.createSequentialGroup()
        		.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(this.label)
                		.addComponent(this.textField)
                		.addComponent(this.button)			
        				));

    }
     
    private void buttonActionPerformed(ActionEvent evt) {
        this.fileChooser = new JFileChooser(this.initialDirectory);
        if (this.selectOnlyDirectory)
        	this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }
 
    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }
     
    public void selectOnlyFolders() {
    	this.setMode(MODE_OPEN);
    	this.selectOnlyDirectory = true;
    }
    
    public void setInitialDirectory(String dirPath) {
    	this.initialDirectory = dirPath;
    }
    
    public void setMode(int mode) {
        this.mode = mode;
    }
     
    public String getSelectedFilePath() {
        return textField.getText();
    }
    
    public void setSelectedFilePath(String filePaths) {
    	this.textField.setText(filePaths);
    }
    
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
}