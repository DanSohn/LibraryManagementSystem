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
import enums.UserType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.ResourceType;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.beans.PropertyChangeEvent;
import javax.swing.border.LineBorder;

public class AdminWindow {
	
	private JFrame			frame;
	private static String	email	= null;
	
	/**
	 * Launch the application.
	 */
	public void AdminS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow();
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
	public AdminWindow() {
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
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginWindow newWin = new LoginWindow();
				newWin.frame.setVisible(true);
			}
		});
		btnLogOut.setBackground(new Color(255, 102, 102));
		btnLogOut.setBounds(1730, 299, 129, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblLibraryStaff = new JLabel("Admin");
		lblLibraryStaff.setForeground(Style.dBlue);
		lblLibraryStaff.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblLibraryStaff.setBounds(10, 263, 223, 73);
		frame.getContentPane().add(lblLibraryStaff);
		
		JButton btnAdd = new JButton("Add Item");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ItemAdd().ItemS();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAdd.setForeground(Style.dBlue);
		btnAdd.setBackground(Style.lBlue);
		btnAdd.setBounds(98, 416, 201, 35);
		frame.getContentPane().add(btnAdd);
		
		JButton btnSearch_1 = new JButton("User Search");
		btnSearch_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new FindUser().FindS();
			}
		});
		
		btnSearch_1.setBounds(98, 464, 201, 35);
		frame.getContentPane().add(btnSearch_1);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(0, 0, 1920, 250);
			
			JLabel lblHome = new JLabel("Home");
			lblHome.setFont(new Font("Tahoma", Font.PLAIN, 34));
			lblHome.setBounds(10, 321, 461, 60);
			frame.getContentPane().add(lblHome);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}