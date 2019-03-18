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
	private static String	from	= null;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public void BookS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearch window = new BookSearch(email, from);
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
	public BookSearch(String email, String from) {
		this.email = email;
		this.from = from;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Style.backBlue);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0,screen.width,screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setBounds(146, 466, 228, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSearch.setForeground(Style.dBlue);
		btnSearch.setBackground(Style.lBlue);

		btnSearch.setBounds(386, 466, 122, 33);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblNoSuchUser = new JLabel("No such user found");
		lblNoSuchUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNoSuchUser.setForeground(Color.RED);
		lblNoSuchUser.setBounds(520, 471, 257, 24);
		frame.getContentPane().add(lblNoSuchUser);
		lblNoSuchUser.setVisible(false);
		
		JLabel lblUserFound = new JLabel("User found");
		lblUserFound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserFound.setBounds(513, 466, 122, 28);
		frame.getContentPane().add(lblUserFound);
		lblUserFound.setVisible(false);
		
		JTextPane textPane_0 = new JTextPane();
		textPane_0.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane_0.setBounds(146, 610, 619, 33);
		frame.getContentPane().add(textPane_0);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel(ResourceType.values()));
		comboBox.setBounds(146, 512, 194, 38);
		frame.getContentPane().add(comboBox);
		
		JLabel lblId_1 = new JLabel("ID #");
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId_1.setBounds(87, 476, 56, 16);
		frame.getContentPane().add(lblId_1);
		
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane_0.setText(ResourceDatabase.getParameterOfResource(textField.getText(), (ResourceType) comboBox.getSelectedItem(), ResourceField.LOCATION));
			}
		});
			
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(0, 0, 1920, 250);	
			
			JLabel lblType = new JLabel("Type");
			lblType.setHorizontalAlignment(SwingConstants.TRAILING);
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblType.setBounds(87, 514, 56, 38);
			frame.getContentPane().add(lblType);
			
			JLabel lblLocation = new JLabel("Location:");
			lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
			lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLocation.setBounds(146, 575, 97, 38);
			frame.getContentPane().add(lblLocation);
			
			JLabel lblStudent = new JLabel(from);
			lblStudent.setForeground(new Color(3, 51, 89));
			lblStudent.setFont(new Font("Tahoma", Font.BOLD, 44));
			lblStudent.setBounds(20, 252, 223, 73);
			frame.getContentPane().add(lblStudent);
			
			JLabel lblBookLocationSearch = new JLabel("Item Location Search");
			lblBookLocationSearch.setFont(new Font("Tahoma", Font.PLAIN, 34));
			lblBookLocationSearch.setBounds(20, 318, 461, 60);
			frame.getContentPane().add(lblBookLocationSearch);
			
			JButton button = new JButton("Back");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (from.equals("Student")){
						new StudentWindow(email).StudentS();
					}
					else {//from == Faculty
						new FacultyWindow(email).FacultyS();
					}
					
					frame.dispose();
				}
			});
			button.setFont(new Font("Tahoma", Font.PLAIN, 20));
			button.setBackground(new Color(255, 140, 0));
			button.setBounds(1734, 281, 97, 32);
			frame.getContentPane().add(button);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}