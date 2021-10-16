package com.rinit.gui.utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
 
public class JFilePicker extends JPanel {
    
	private static final int PREFERED_HEIGHT = 40;
	private Dimension preferedDimension = new Dimension((int)this.getMaximumSize().getWidth() - 30, PREFERED_HEIGHT);
	
	private String textFieldLabel;
    private String buttonLabel;
     
    private JLabel label;
    private JTextField textField;
    private JButton button;
    
    private GroupLayout layout; 
     
    private JFileChooser fileChooser;
     
    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;
     
    public JFilePicker(String textFieldLabel, String buttonLabel) {
        this.layout = new GroupLayout(this);
        this.layout.setAutoCreateGaps(true); 
        this.layout.setAutoCreateContainerGaps(true); 
        this.setLayout(this.layout);
        
    	this.textFieldLabel = textFieldLabel;
    	this.buttonLabel = buttonLabel;
		 
        this.fileChooser = new JFileChooser();
         
        
        // creates the GUI
        label = new JLabel(this.textFieldLabel); 
        textField = new JTextField();
        button = new JButton(this.buttonLabel);
         
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonActionPerformed(evt);            
            }
        });
         
        this.layout.setHorizontalGroup(this.layout.createSequentialGroup()
        		.addComponent(this.label)
        		.addComponent(this.textField)
        		.addComponent(this.button));
        
        this.layout.setVerticalGroup(this.layout.createSequentialGroup()
        		.addGroup(this.layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(this.label)
                		.addComponent(this.textField)
                		.addComponent(this.button)			
        				));

    }
     
    private void buttonActionPerformed(ActionEvent evt) {
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
     
    public void setMode(int mode) {
        this.mode = mode;
    }
     
    public String getSelectedFilePath() {
        return textField.getText();
    }
     
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
}