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
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import enums.UserField;

import java.io.File;
import java.io.IOException;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class FindUser {
	
	JFrame					frame;
	private static String	email	= null;
	private JTextField		textField;
	
	/**
	 * Launch the application.
	 */
	public void FindS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindUser window = new FindUser();
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
	public FindUser() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Style.backBlue);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, screen.width, screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		JTextPane textField = new JTextPane();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setBounds(862, 302, 194, 35);
		frame.getContentPane().add(textField);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCheck.setForeground(Style.dBlue);
		btnCheck.setBackground(Style.lBlue);
		btnCheck.setBounds(1060, 305, 117, 32);
		frame.getContentPane().add(btnCheck);
		
		JLabel lblNoSuchUser = new JLabel("No such user found");
		lblNoSuchUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNoSuchUser.setForeground(Color.RED);
		lblNoSuchUser.setBounds(1189, 307, 314, 28);
		frame.getContentPane().add(lblNoSuchUser);
		lblNoSuchUser.setVisible(false);
		
		JLabel lblUserFound = new JLabel("User found");
		lblUserFound.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserFound.setBounds(1191, 309, 167, 23);
		frame.getContentPane().add(lblUserFound);
		lblUserFound.setVisible(false);
		
		JTextPane TP_blacklisted = new JTextPane();
		TP_blacklisted.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TP_blacklisted.setBounds(555, 788, 238, 37);
		frame.getContentPane().add(TP_blacklisted);
		
		JTextPane TP_firstName = new JTextPane();
		TP_firstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TP_firstName.setBounds(555, 435, 369, 37);
		frame.getContentPane().add(TP_firstName);
		
		JTextPane TP_fees = new JTextPane();
		TP_fees.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TP_fees.setBounds(876, 788, 172, 37);
		frame.getContentPane().add(TP_fees);
		
		JTextPane TP_lastName = new JTextPane();
		TP_lastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TP_lastName.setBounds(978, 435, 369, 37);
		frame.getContentPane().add(TP_lastName);
		
		JTextPane TP_checkedOut = new JTextPane();
		TP_checkedOut.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TP_checkedOut.setBounds(555, 530, 792, 199);
		frame.getContentPane().add(TP_checkedOut);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserId.setBounds(741, 302, 109, 37);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFirstName.setBounds(555, 400, 223, 37);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLastName.setBounds(978, 400, 182, 37);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblBooksCheckedOut = new JLabel("Books checked out:");
		lblBooksCheckedOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblBooksCheckedOut.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBooksCheckedOut.setBounds(555, 496, 238, 37);
		frame.getContentPane().add(lblBooksCheckedOut);
		
		JLabel lblBlackListStatus = new JLabel("Black list status:");
		lblBlackListStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblBlackListStatus.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBlackListStatus.setBounds(555, 756, 194, 37);
		frame.getContentPane().add(lblBlackListStatus);
		
		JLabel lblFeesOwed = new JLabel("Fees owed:");
		lblFeesOwed.setHorizontalAlignment(SwingConstants.LEFT);
		lblFeesOwed.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFeesOwed.setBounds(876, 756, 172, 37);
		frame.getContentPane().add(lblFeesOwed);
		
		JLabel label_1 = new JLabel("$");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_1.setBounds(845, 788, 38, 37);
		frame.getContentPane().add(label_1);
		
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserDatabase.checkRegdUser(textField.getText())) {
					lblUserFound.setVisible(true);
					TP_blacklisted.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.IS_BLACKLISTED));
					TP_firstName.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.FIRST_NAME));
					TP_checkedOut.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.SIGNED_OUT_ARRAY));
					TP_lastName.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.LAST_NAME));
					TP_fees.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.FINE_AMOUNT));
				} else {
					lblNoSuchUser.setVisible(true);
				}
				;
			}
		});
		
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
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new BookSignOut().SignOutS();
			}
		});
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
		btnAddUser.setBackground(new Color(230, 230, 240));
		btnAddUser.setForeground(new Color(3, 51, 89));
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
		btnItemAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnItemAdd.setBackground(new Color(230, 230, 240));
		btnItemAdd.setForeground(new Color(3, 51, 89));
		btnItemAdd.setBounds(1183, 204, 223, 38);
		frame.getContentPane().add(btnItemAdd);
		//
		
		//selected
		//Item add button
		JButton btnFindUser = new JButton("User Search");
		btnFindUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnFindUser.setBackground(new Color(230, 230, 240));
		btnFindUser.setForeground(Style.dBlue);
		btnFindUser.setBackground(Style.lBlue);
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
			picLabel2.setBounds(-13, 0, 1939, 1020);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
	}
}