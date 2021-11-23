package com.rinit.gui.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rinit.gui.view.ui.BorderedPanel;

public class SetupView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7277681233834948119L;

	private SetupPanel setupPane = new SetupPanel();
	private JPanel mainPane = new JPanel();

	public SetupView() {
		
		
		this.setTitle("Runit GUI setup");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(Colors.POPUP_BACKGROUND);
        this.setUndecorated(true);
        
        mainPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPane.add(this.setupPane);
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        mainPane.setBackground(Colors.POPUP_BACKGROUND);
        this.add(mainPane);
        
        this.setSize(600, this.getPreferredSize().height);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
	}
	
	public String getSelectedHost() {
		return this.setupPane.getSelectedHost();
	}
	
	public class SetupPanel extends BorderedPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1395314950491083792L;

		private GroupLayout layout;
		
		private String[] hosts;
		
		private JComboBox<String> hostsBox;
		private JButton selectButton = new JButton("select");
		
		private String selectedHost;
		
		public SetupPanel() {
			super("Setup");
			try {
				this.hosts = this.readHosts();
				this.hostsBox = new JComboBox<String>(this.hosts);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			this.layout = new GroupLayout(this);
			this.setLayout(this.layout);
			
	        this.layout.setAutoCreateContainerGaps(true);
	        this.layout.setAutoCreateGaps(true);
	        
	        this.layout.setHorizontalGroup(this.layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        		.addComponent(hostsBox)
	        		.addGroup(GroupLayout.Alignment.CENTER, 
	        					this.layout.createSequentialGroup()
	        					.addComponent(selectButton)));
	        
	        this.layout.setVerticalGroup(this.layout.createSequentialGroup()
	        		.addComponent(hostsBox)
	        		.addComponent(selectButton));
	        
	        this.hostsBox.setEditable(true);
	        
	        this.bindListeners();
	         
		}
		
		private void bindListeners() {
			this.selectButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					select();
				}
			
			});
		}
		
		private void select() {
			this.selectedHost = (String)this.hostsBox.getSelectedItem();
			try {
				this.writeHost();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SwingUtilities.getWindowAncestor(this).dispose();
		}
		
		@SuppressWarnings("null")
		private String[] readHosts() throws IOException {
			BufferedReader reader = null;
			String[] result = null;
			File file = new File("conf/", "hosts");
			
			if(!file.isFile()) {
				try {
					file.createNewFile();
				} catch (IOException e) {e.printStackTrace();}
			}
			
			try {
				reader = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e1) {reader.close(); e1.printStackTrace();}
			
			try {
				result = reader.readLine().split(",");
			} catch (IOException e) {e.printStackTrace();}

			return result;
		
		}
		
		private void writeHost() throws IOException {
			if (Arrays.stream(this.hosts).anyMatch(this.selectedHost::equals)) 
				return;
			File file = new File("conf/", "hosts");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.append(String.format("%s,", this.selectedHost));
			writer.close();
		}

		public String getSelectedHost() {
			return selectedHost;
		}
		
		
	}
	
	
}
