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

import database.UserDatabase;
import enums.UserType;
import users.Clerk;
import utils.CheckoutBook;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ClerkWindow {
	
	private JFrame			frame;
	
	/**
	 * Launch the application.
	 */
	public void ClerkS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClerkWindow window = new ClerkWindow();
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
	public ClerkWindow() {
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
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginWindow newWin = new LoginWindow();
				newWin.frame.setVisible(true);
			}
		});
		btnLogOut.setBackground(new Color(255, 102, 102));
		btnLogOut.setBounds(1743, 281, 116, 25);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnAdd = new JButton("Book Manage");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new BookSignOut().SignOutS();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAdd.setBounds(25, 402, 223, 38);
		btnAdd.setForeground(Style.dBlue);
		btnAdd.setBackground(Style.lBlue);
		frame.getContentPane().add(btnAdd);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AddUser().AddUserS();
			}
		});
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAddUser.setBounds(25, 453, 223, 38);
		btnAddUser.setForeground(Style.dBlue);
		btnAddUser.setBackground(Style.lBlue);
		frame.getContentPane().add(btnAddUser);

		JLabel lblClerk = new JLabel("Clerk");
		lblClerk.setForeground(new Color(3, 51, 89));
		lblClerk.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblClerk.setBounds(25, 263, 223, 73);
		frame.getContentPane().add(lblClerk);
		
		JLabel label = new JLabel("Home");
		label.setFont(new Font("Tahoma", Font.PLAIN, 34));
		label.setBounds(26, 328, 461, 60);
		frame.getContentPane().add(label);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(0, 0, 1920, 250);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
