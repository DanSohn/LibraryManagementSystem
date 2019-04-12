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
import javax.swing.SwingConstants;

import utils.CheckoutBook;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;

public class AdminWindow {
	
	private JFrame			frame;
	private JTextField usetTxt;
	private JTextField bookTxt;
	private static String	email	= null;
	
	/**
	 * Launch the application.
	 */
	public static void AdminS() {
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

		usetTxt = new JTextField();
		usetTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		usetTxt.setColumns(10);
		usetTxt.setBounds(782, 430, 337, 47);
		frame.getContentPane().add(usetTxt);
		
		bookTxt = new JTextField();
		bookTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bookTxt.setColumns(10);
		bookTxt.setBounds(782, 530, 337, 47);
		frame.getContentPane().add(bookTxt);
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserId.setBounds(782, 400, 94, 21);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblBookId = new JLabel("Book ID:");
		lblBookId.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBookId.setBounds(782, 500, 97, 34);
		frame.getContentPane().add(lblBookId);
		
		JLabel lblDone = new JLabel("Successful!");
		lblDone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDone.setForeground(Color.GREEN);
		lblDone.setBounds(1012, 650, 123, 34);
		frame.getContentPane().add(lblDone);
		lblDone.setVisible(false);
		
		JLabel lblDone_2 = new JLabel("Successful!");
		lblDone_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDone_2.setForeground(Color.GREEN);
		lblDone_2.setBounds(1012, 599, 107, 38);
		frame.getContentPane().add(lblDone_2);
		lblDone_2.setVisible(false);
		
		
		JButton btnNewButton = new JButton("Check out");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(CheckoutBook.Checkout("UserDatabase.txt", "ItemDatabase.txt", usetTxt.getText(), bookTxt.getText()) > 0) {
						lblDone.setVisible(true);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setBounds(782, 600, 218, 36);
		btnNewButton.setForeground(Style.dBlue);
		btnNewButton.setBackground(Style.lBlue);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(new Clerk().returnResource(bookTxt.getText(), usetTxt.getText()) > 0) {
				//	lblDone_2.setVisible(true);
				//}
				//
			}
		});
		btnReturn.setBounds(782, 647, 218, 36);
		btnReturn.setForeground(Style.dBlue);
		btnReturn.setBackground(Style.lBlue);
		frame.getContentPane().add(btnReturn);

		///////////////// main page setup
		//log out button
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginWindow newWin = new LoginWindow();
				newWin.frame.setVisible(true);
			}
		});
		btnLogOut.setBackground(new Color(255, 140, 0));
		btnLogOut.setBounds(1774, 952, 116, 33);
		frame.getContentPane().add(btnLogOut);
		//
		
		//title name
		JLabel lblTitle = new JLabel("Admin");
		lblTitle.setForeground(new Color(3, 51, 89));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblTitle.setBounds(25, 179, 223, 73);
		frame.getContentPane().add(lblTitle);
		//
		
		//selected
		//return checkout books button
		JButton btnAdd = new JButton("Return/Check-out");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAdd.setBounds(243, 204, 223, 38);
		btnAdd.setForeground(Style.lBlue);
		btnAdd.setBackground(Style.dBlue);
		frame.getContentPane().add(btnAdd);
		//
		
		//add user button
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AddUser().AddUserS();
			}
		});
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAddUser.setBounds(478, 204, 223, 38);
		btnAddUser.setForeground(Style.dBlue);
		btnAddUser.setBackground(Style.lBlue);
		frame.getContentPane().add(btnAddUser);
		//
		
		//search book location button
		JButton btnSearchBookLoc = new JButton("Search Book Location");
		btnSearchBookLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookSearch("email","Admin").BookS();
				frame.dispose();
			}
		});
		btnSearchBookLoc.setForeground(new Color(3, 51, 89));
		btnSearchBookLoc.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnSearchBookLoc.setBackground(new Color(230, 230, 240));
		btnSearchBookLoc.setBounds(713, 204, 223, 38);
		frame.getContentPane().add(btnSearchBookLoc);
		//
		
		//my books button
		JButton btnMyBooks = new JButton("My Books");
		btnMyBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new BookSearch( email, "Admin").BookS();
			}
		});
		btnMyBooks.setForeground(new Color(3, 51, 89));
		btnMyBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMyBooks.setBackground(new Color(230, 230, 240));
		btnMyBooks.setBounds(948, 204, 223, 38);
		frame.getContentPane().add(btnMyBooks);
		//
		
		//Item add button
		JButton btnItemAdd = new JButton("Item Add");
		btnItemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ItemAdd().ItemS();
			}
		});
		btnItemAdd.setForeground(new Color(3, 51, 89));
		btnItemAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnItemAdd.setBackground(new Color(230, 230, 240));
		btnItemAdd.setBounds(1183, 204, 223, 38);
		frame.getContentPane().add(btnItemAdd);
		//
		
		//User search button
		JButton btnFindUser = new JButton("User Search");
		btnFindUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new FindUser().FindS();
			}
		});
		btnFindUser.setForeground(new Color(3, 51, 89));
		btnFindUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnFindUser.setBackground(new Color(230, 230, 240));
		btnFindUser.setBounds(1418, 204, 223, 38);
		frame.getContentPane().add(btnFindUser);
		//
		
		//background and banner photo
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(0, 0, 1920, 166);

			myPicture = ImageIO.read(new File("norm.jpg"));
			JLabel picLabel2 = new JLabel(new ImageIcon(myPicture));
			picLabel2.setBorder(null);
			frame.getContentPane().add(picLabel2);
			picLabel2.setBounds(-12, 0, 1939, 1020);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
	}
}