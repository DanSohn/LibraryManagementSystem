package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import database.ResourceDatabase;
import database.UserDatabase;
import enums.UserType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.ResourceField;
import enums.ResourceType;
import enums.UserField;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class BookSearch {
	
	JFrame			frame;
	private static String	email	= null;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public void BookS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearch window = new BookSearch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public BookSearch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Style.mBlue);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0,screen.width,screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(0, 0, 1920, 250);
			
			textField = new JTextField();
			textField.setBounds(146, 344, 116, 22);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			
			JButton btnCheck = new JButton("Search");

			btnCheck.setBounds(286, 343, 97, 25);
			frame.getContentPane().add(btnCheck);
			
			JLabel lblNoSuchUser = new JLabel("No such user found");
			lblNoSuchUser.setForeground(Color.RED);
			lblNoSuchUser.setBounds(425, 347, 159, 16);
			frame.getContentPane().add(lblNoSuchUser);
			lblNoSuchUser.setVisible(false);
			
			JLabel lblUserFound = new JLabel("User found");
			lblUserFound.setBounds(425, 347, 97, 16);
			frame.getContentPane().add(lblUserFound);
			lblUserFound.setVisible(false);
			
			JTextPane textPane_0 = new JTextPane();
			textPane_0.setBounds(146, 414, 619, 22);
			frame.getContentPane().add(textPane_0);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(ResourceType.values()));
			comboBox.setBounds(146, 379, 116, 22);
			frame.getContentPane().add(comboBox);
			
			JLabel lblId_1 = new JLabel("id");
			lblId_1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblId_1.setBounds(78, 347, 56, 16);
			frame.getContentPane().add(lblId_1);
			
			
			btnCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textPane_0.setText(ResourceDatabase.getParameterOfResource(textField.getText(), (ResourceType) comboBox.getSelectedItem(), ResourceField.LOCATION));
				}
			});
		
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}