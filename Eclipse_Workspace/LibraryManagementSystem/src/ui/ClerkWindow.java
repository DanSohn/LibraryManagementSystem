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
	private JTextField userIDTxt;
	private JTextField bookTxt;
	private JTextField txtPaid;
	
	/**
	 * Launch the application.
	 */
	public static void ClerkS() {
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

		userIDTxt = new JTextField();
		userIDTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		userIDTxt.setColumns(10);
		userIDTxt.setBounds(782, 430, 337, 47);
		frame.getContentPane().add(userIDTxt);
		
		bookTxt = new JTextField();
		bookTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bookTxt.setColumns(10);
		bookTxt.setBounds(526, 540, 218, 34);
		frame.getContentPane().add(bookTxt);
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserId.setBounds(782, 400, 94, 21);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblBookId = new JLabel("Book ID:");
		lblBookId.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBookId.setBounds(526, 508, 97, 34);
		frame.getContentPane().add(lblBookId);
		
		JLabel lblDone = new JLabel("Successful!");
		lblDone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDone.setForeground(Color.GREEN);
		lblDone.setBounds(769, 638, 123, 34);
		frame.getContentPane().add(lblDone);
		lblDone.setVisible(false);
		
		JLabel lblDone_2 = new JLabel("Successful!");
		lblDone_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDone_2.setForeground(Color.GREEN);
		lblDone_2.setBounds(769, 587, 107, 38);
		frame.getContentPane().add(lblDone_2);
		lblDone_2.setVisible(false);
		
		
		JButton btnNewButton = new JButton("Check out");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(CheckoutBook.Checkout("UserDatabase.txt", "ItemDatabase.txt", userIDTxt.getText(), bookTxt.getText()) > 0) {
						lblDone.setVisible(true);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setBounds(526, 587, 218, 36);
		btnNewButton.setForeground(Style.dBlue);
		btnNewButton.setBackground(Style.lBlue);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(new Clerk().returnResource(bookTxt.getText(), userIDTxt.getText()) > 0) {
					lblDone_2.setVisible(true);
				}
				
			}
		});
		btnReturn.setBounds(526, 636, 218, 36);
		btnReturn.setForeground(Style.dBlue);
		btnReturn.setBackground(Style.lBlue);
		frame.getContentPane().add(btnReturn);

		JLabel lblChange = new JLabel("change");
		lblChange.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblChange.setBounds(1361, 540, 148, 34);
		frame.getContentPane().add(lblChange);
		lblChange.setVisible(false);

		txtPaid = new JTextField();
		txtPaid.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtPaid.setColumns(10);
		txtPaid.setBounds(1153, 540, 196, 34);
		frame.getContentPane().add(txtPaid);
		
		JLabel label_1 = new JLabel("$");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_1.setBounds(1131, 540, 56, 32);
		frame.getContentPane().add(label_1);
		
		JLabel lblAmountPaid = new JLabel("Amount Paid:");
		lblAmountPaid.setHorizontalAlignment(SwingConstants.LEFT);
		lblAmountPaid.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAmountPaid.setBounds(1131, 508, 155, 34);
		frame.getContentPane().add(lblAmountPaid);
		
		JLabel lblOwed = new JLabel("owed");
		lblOwed.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblOwed.setBounds(1361, 437, 56, 32);
		frame.getContentPane().add(lblOwed);
		lblOwed.setVisible(false);
		
		JButton CheckFine = new JButton("Check Fine");
		CheckFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fine = new Clerk().getFine(userIDTxt.getText());
				if (fine < 0) {
					lblOwed.setText("Error");
					lblOwed.setVisible(true);
				}
				else
				{
					lblOwed.setText( "$" + Integer.toString(fine) );
					lblOwed.setVisible(true);
				}
				
			}
		});
		CheckFine.setForeground(new Color(3, 51, 89));
		CheckFine.setFont(new Font("Tahoma", Font.PLAIN, 22));
		CheckFine.setBackground(new Color(230, 230, 240));
		CheckFine.setBounds(1131, 435, 218, 36);
		frame.getContentPane().add(CheckFine);
		
		
		JButton btnPayFine = new JButton("Pay Fine");
		btnPayFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int change = new Clerk().payFine(userIDTxt.getText(), Integer.parseInt(txtPaid.getText()));
				if (change < 0) {
					lblChange.setText("Error");
					lblChange.setVisible(true);
				}
				else
				{
					lblChange.setText( "Change: $" + Integer.toString(change) );
					lblChange.setVisible(true);
				}
				
			}
		});
		btnPayFine.setForeground(new Color(3, 51, 89));
		btnPayFine.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnPayFine.setBackground(new Color(230, 230, 240));
		btnPayFine.setBounds(1131, 587, 218, 36);
		frame.getContentPane().add(btnPayFine);
		
		
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
		JLabel lblTitle = new JLabel("Clerk");
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
		btnAddUser.setForeground(Style.dBlue);
		btnAddUser.setBackground(Style.lBlue);
		frame.getContentPane().add(btnAddUser);
		//
		
		//search book location button
		JButton btnSearchBookLoc = new JButton("Search Book Location");
		btnSearchBookLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookSearch("addthis","Clerk").BookS();		////////////////////////////////// no email for clerk yet?!!!!!!
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
