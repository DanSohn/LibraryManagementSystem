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

import enums.ResourceType;
import enums.UserField;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class FindUser {
	
	JFrame			frame;
	private static String	email	= null;
	private JTextField textField;
	
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
		frame.setBounds(0, 0,screen.width,screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
			
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setBounds(226, 446, 175, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCheck.setForeground(Style.dBlue);
		btnCheck.setBackground(Style.lBlue);
		btnCheck.setBounds(413, 446, 117, 32);
		frame.getContentPane().add(btnCheck);
		
		JLabel lblNoSuchUser = new JLabel("No such user found");
		lblNoSuchUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNoSuchUser.setForeground(Color.RED);
		lblNoSuchUser.setBounds(542, 451, 314, 28);
		frame.getContentPane().add(lblNoSuchUser);
		lblNoSuchUser.setVisible(false);
		
		JLabel lblUserFound = new JLabel("User found");
		lblUserFound.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserFound.setBounds(542, 453, 167, 23);
		frame.getContentPane().add(lblUserFound);
		lblUserFound.setVisible(false);
		
		JTextPane textPane_0 = new JTextPane();
		textPane_0.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane_0.setBounds(818, 859, 172, 37);
		frame.getContentPane().add(textPane_0);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane_1.setBounds(818, 446, 172, 37);
		frame.getContentPane().add(textPane_1);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane_3.setBounds(1048, 859, 172, 37);
		frame.getContentPane().add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane_4.setBounds(1007, 446, 172, 37);
		frame.getContentPane().add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane_5.setBounds(818, 592, 792, 189);
		frame.getContentPane().add(textPane_5);
		
		JButton btnBack = new JButton("Back"); //to admin
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AdminWindow().AdminS();
			}
		});
		
		btnBack.setBackground(new Color(255, 140, 0));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.setBounds(1735, 284, 97, 32);
		frame.getContentPane().add(btnBack);
		
		JLabel label = new JLabel("Admin");
		label.setForeground(new Color(3, 51, 89));
		label.setFont(new Font("Tahoma", Font.BOLD, 44));
		label.setBounds(10, 258, 223, 73);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("User Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel.setBounds(12, 336, 461, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserId.setBounds(105, 446, 109, 37);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstName.setBounds(818, 408, 109, 37);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastName.setBounds(1007, 408, 109, 37);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblBooksCheckedOut = new JLabel("Books checked out:");
		lblBooksCheckedOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblBooksCheckedOut.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBooksCheckedOut.setBounds(818, 559, 217, 37);
		frame.getContentPane().add(lblBooksCheckedOut);
		
		JLabel lblBlackListStatus = new JLabel("Black list status:");
		lblBlackListStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblBlackListStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBlackListStatus.setBounds(818, 822, 172, 37);
		frame.getContentPane().add(lblBlackListStatus);
		
		JLabel lblFeesOwed = new JLabel("Fees owed:");
		lblFeesOwed.setHorizontalAlignment(SwingConstants.LEFT);
		lblFeesOwed.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFeesOwed.setBounds(1048, 822, 109, 37);
		frame.getContentPane().add(lblFeesOwed);
		
		JLabel label_1 = new JLabel("$");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_1.setBounds(1019, 859, 38, 37);
		frame.getContentPane().add(label_1);
		
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UserDatabase.checkRegdUser(textField.getText())){
					lblUserFound.setVisible(true);
					textPane_0.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.FIRST_NAME));
					textPane_1.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.LAST_NAME));
					textPane_5.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.SIGNED_OUT_ARRAY));
					textPane_4.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.IS_BLACKLISTED));
					textPane_3.setText(UserDatabase.getParameterOfUser(textField.getText(), UserField.FINE_AMOUNT));
				}
				else{
					lblNoSuchUser.setVisible(true);
				};
			}
		});
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(0, 0, 1920, 250);	
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}