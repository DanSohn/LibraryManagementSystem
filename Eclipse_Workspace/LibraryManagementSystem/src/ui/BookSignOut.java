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

public class BookSignOut {
	
	private JFrame			frame;
	private JTextField usetTxt;
	private JTextField bookTxt;
	
	/**
	 * Launch the application.
	 */
	public void SignOutS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSignOut window = new BookSignOut();
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
	public BookSignOut() {
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
		usetTxt.setBounds(132, 391, 177, 34);
		frame.getContentPane().add(usetTxt);
		
		bookTxt = new JTextField();
		bookTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bookTxt.setColumns(10);
		bookTxt.setBounds(132, 452, 177, 34);
		frame.getContentPane().add(bookTxt);
		
		JLabel lblUserId = new JLabel("User id");
		lblUserId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserId.setBounds(26, 399, 94, 21);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblBookId = new JLabel("Book id");
		lblBookId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBookId.setBounds(26, 453, 97, 34);
		frame.getContentPane().add(lblBookId);
		
		JLabel lblDone = new JLabel("Successful!");
		lblDone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDone.setForeground(Color.GREEN);
		lblDone.setBounds(321, 525, 123, 34);
		frame.getContentPane().add(lblDone);
		lblDone.setVisible(false);
		
		JLabel lblDone_2 = new JLabel("Successful!");
		lblDone_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDone_2.setForeground(Color.GREEN);
		lblDone_2.setBounds(321, 572, 107, 38);
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
		
		btnNewButton.setBounds(132, 574, 177, 34);
		btnNewButton.setForeground(Style.dBlue);
		btnNewButton.setBackground(Style.lBlue);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(new Clerk().returnResource(bookTxt.getText(), usetTxt.getText()) > 0) {
					lblDone_2.setVisible(true);
				}
				
			}
		});
		btnReturn.setBounds(132, 523, 177, 36);
		btnReturn.setForeground(Style.dBlue);
		btnReturn.setBackground(Style.lBlue);
		frame.getContentPane().add(btnReturn);
		
		JLabel lblClerk = new JLabel("Clerk");
		lblClerk.setForeground(new Color(3, 51, 89));
		lblClerk.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblClerk.setBounds(25, 263, 223, 73);
		frame.getContentPane().add(lblClerk);
		
		JLabel lblBookSignOut = new JLabel("Manage Books");
		lblBookSignOut.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblBookSignOut.setBounds(26, 328, 461, 60);
		frame.getContentPane().add(lblBookSignOut);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ClerkWindow().ClerkS();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBackground(new Color(255, 140, 0));
		button.setBounds(1723, 283, 116, 25);
		frame.getContentPane().add(button);
		
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
