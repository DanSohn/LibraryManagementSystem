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

class FindUser extends Style{
	
	JFrame					frame;
	private JTextField		textField;
	
	/**
	 * Launch the application.
	 * @param email 
	 * @param name 
	 */
	static void FindS(String name, String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindUser window = new FindUser(name, email);
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
	private FindUser(String name, String email) {
		frame = new JFrame();
		initialize();
		buttons(name, email, frame, "FindUser");
		setup(name, frame);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		////labels
		//
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserId.setBounds(740, 300, 109, 37);
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
		
		JTextPane textField = new JTextPane();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setBounds(862, 302, 194, 35);
		frame.getContentPane().add(textField);
		
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
		TP_checkedOut.setBounds(555, 530, 1102, 199);
		frame.getContentPane().add(TP_checkedOut);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCheck.setForeground(dBlue);
		btnCheck.setBackground(lBlue);
		btnCheck.setBounds(1060, 305, 117, 32);
		frame.getContentPane().add(btnCheck);
		
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserDatabase.checkRegdUser(textField.getText())) {
					MessageBox.MessageS("User found");
					TP_blacklisted.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.IS_BLACKLISTED));
					TP_firstName.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.FIRST_NAME));
					TP_checkedOut.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.SIGNED_OUT_ARRAY));
					TP_lastName.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.LAST_NAME));
					TP_fees.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.FINE_AMOUNT));
				} else {
					MessageBox.MessageS("Error");
				}
				;
			}
		});
	}
}