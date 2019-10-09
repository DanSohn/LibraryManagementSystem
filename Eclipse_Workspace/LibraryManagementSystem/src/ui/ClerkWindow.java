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

import users.Clerk;
import utils.CheckoutBook;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

class ClerkWindow extends Style{
	
	private JFrame			frame;
	private JTextField userIDTxt;
	private JTextField bookTxt;
	private JTextField txtPaid;
	
	/**
	 * Launch the application.
	 * @param name - name of person
	 * @param email - email of person
	 */
	static void ClerkS(String name, String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClerkWindow window = new ClerkWindow(name, email);
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
	private ClerkWindow(String name, String email) {
		frame = new JFrame();
		initilize();
		buttons(name, email, frame, "ReturnCheckout");
		setup(name, frame);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initilize(){
		//text fields
		userIDTxt = new JTextField();
		userIDTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		userIDTxt.setColumns(10);
		userIDTxt.setBounds(782, 339, 337, 47);
		frame.getContentPane().add(userIDTxt);
		
		bookTxt = new JTextField();
		bookTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bookTxt.setColumns(10);
		bookTxt.setBounds(526, 444, 218, 34);
		frame.getContentPane().add(bookTxt);
		
		txtPaid = new JTextField();
		txtPaid.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtPaid.setColumns(10);
		txtPaid.setBounds(1153, 444, 196, 34);
		frame.getContentPane().add(txtPaid);
		
		//field labels
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserId.setBounds(782, 305, 94, 21);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblBookId = new JLabel("Book ID:");
		lblBookId.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBookId.setBounds(526, 406, 97, 34);
		frame.getContentPane().add(lblBookId);
		
		//labels
		JLabel label_1 = new JLabel("$");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_1.setBounds(1129, 444, 56, 32);
		frame.getContentPane().add(label_1);
		
		JLabel lblAmountPaid = new JLabel("Amount Paid:");
		lblAmountPaid.setHorizontalAlignment(SwingConstants.LEFT);
		lblAmountPaid.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAmountPaid.setBounds(1131, 406, 155, 34);
		frame.getContentPane().add(lblAmountPaid);
		
		JLabel lblOwed = new JLabel("owed");
		lblOwed.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblOwed.setBounds(1357, 346, 56, 32);
		frame.getContentPane().add(lblOwed);
		lblOwed.setVisible(false);
		
		//Buttons
		//checkout button
		JButton btnNewButton = new JButton("Check out");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(CheckoutBook.Checkout("UserDatabase.txt", "ItemDatabase.txt", userIDTxt.getText(), bookTxt.getText()) > 0) {
						MessageBox.MessageS("Item Check Out Successful");
					}
					else{
						MessageBox.MessageS("Item Check Out Unsuccessful");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setBounds(526, 491, 218, 36);
		btnNewButton.setForeground(dBlue);
		btnNewButton.setBackground(lBlue);
		frame.getContentPane().add(btnNewButton);
		
		//return button
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(new Clerk().returnResource(bookTxt.getText(), userIDTxt.getText()) > 0) {
					MessageBox.MessageS("Item Return Successful");
				}
				else{
					MessageBox.MessageS("Item Return Unsuccessful");
				}
				
			}
		});
		btnReturn.setBounds(526, 540, 218, 36);
		btnReturn.setForeground(dBlue);
		btnReturn.setBackground(lBlue);
		frame.getContentPane().add(btnReturn);
		
		//check fine button
		JButton CheckFine = new JButton("Check Fine");
		CheckFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fine = new Clerk().getFine(userIDTxt.getText());
				if (fine < 0) {
					MessageBox.MessageS("User Not Found");
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
		CheckFine.setBounds(1131, 344, 218, 36);
		frame.getContentPane().add(CheckFine);
		
		//pay fine button
		JButton btnPayFine = new JButton("Pay Fine");
		btnPayFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int change = new Clerk().payFine(userIDTxt.getText(), Integer.parseInt(txtPaid.getText()));
				if (change < 0) {
					MessageBox.MessageS("Error");
				}
				else
				{
					MessageBox.MessageS("Change: $" + Integer.toString(change) );
					
					int fine = new Clerk().getFine(userIDTxt.getText());
					lblOwed.setText( "$" + Integer.toString(fine) );
				}
				
			}
		});
		btnPayFine.setForeground(new Color(3, 51, 89));
		btnPayFine.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnPayFine.setBackground(new Color(230, 230, 240));
		btnPayFine.setBounds(1131, 491, 218, 36);
		frame.getContentPane().add(btnPayFine);
	}
}
