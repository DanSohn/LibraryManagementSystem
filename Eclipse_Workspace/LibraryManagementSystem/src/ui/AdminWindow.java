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

import users.Clerk;
import utils.CheckoutBook;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;

public class AdminWindow extends Style{
	
	private JFrame			frame;
	private JTextField usetTxt;
	private JTextField bookTxt;
	
	/**
	 * Launch the application.
	 * @param name 
	 */
	public static void AdminS(String name, String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow(name, email);
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
	public AdminWindow(String name, String email) {
		frame = new JFrame();
		initilize();
		buttons(name, email, frame, "ReturnCheckout");
		setup(name, frame);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initilize() {
		////Labels
		//user id labels
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserId.setBounds(785, 298, 94, 21);
		frame.getContentPane().add(lblUserId);
		
		//book id label
		JLabel lblBookId = new JLabel("Book ID:");
		lblBookId.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBookId.setBounds(782, 392, 97, 34);
		frame.getContentPane().add(lblBookId);
		
		////text fields
		//user id text field
		usetTxt = new JTextField();
		usetTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		usetTxt.setColumns(10);
		usetTxt.setBounds(782, 332, 337, 47);
		frame.getContentPane().add(usetTxt);
		
		//book id text field
		bookTxt = new JTextField();
		bookTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bookTxt.setColumns(10);
		bookTxt.setBounds(782, 429, 337, 47);
		frame.getContentPane().add(bookTxt);
		
		//checkout button
		JButton btnNewButton = new JButton("Check out");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(CheckoutBook.Checkout("UserDatabase.txt", "ItemDatabase.txt", usetTxt.getText(), bookTxt.getText()) > 0) {
						MessageBox.MessageS("Book Check Out Successful!");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(782, 489, 218, 36);
		btnNewButton.setForeground(dBlue);
		btnNewButton.setBackground(lBlue);
		frame.getContentPane().add(btnNewButton);
		
		//return button
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(new Clerk().returnResource(bookTxt.getText(), usetTxt.getText()) > 0) {
					MessageBox.MessageS("Book Return Successful!");
				}
			}
		});
		btnReturn.setBounds(782, 538, 218, 36);
		btnReturn.setForeground(dBlue);
		btnReturn.setBackground(lBlue);
		frame.getContentPane().add(btnReturn);
	}
}