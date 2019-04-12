package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Style {
	static Color dBlue = new Color(3, 51, 89);
	static Color mBlue = new Color(15, 154, 187);
	static Color lBlue = new Color(230, 230, 240);
	static Color backBlue = new Color(122, 178, 201);
	static Color jBlue = new Color(210, 210, 210);
	static int buttonY = 185;	
	
	
	public static void setup(String name, JFrame frame){
		//frame set up
		frame.getContentPane().setBackground(backBlue);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0,screen.width,screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
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
		
		//title name
		JLabel lblTitle = new JLabel(name);
		lblTitle.setForeground(new Color(3, 51, 89));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblTitle.setBounds(25, buttonY-20 , 223, 73);
		frame.getContentPane().add(lblTitle);

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
	
	//button set up for each user type
	//last constructor is boolean to see which page is open
	public static void buttons(String name, String email, JFrame frame, String s) {
		switch (name) {
			case "Clerk":
				buttonReturnCheckout(frame, name, email, 250, s.equals("ReturnCheckout"));
				buttonAddUser(frame, name, email, 500, s.equals("AddUser"));
				buttonMyBook(frame, name, email, 750, s.equals("MyBook") );				
				buttonSearchBook(frame, name, email, 1000, s.equals("SearchBook"));
				break;
				
			case "Faculty":
				buttonMyBook(frame, name, email, 250, s.equals("MyBook") );
				buttonSearchBook(frame, name, email, 500,s.equals("SearchBook"));
				buttonRestrict(frame, name, email, 750, s.equals("Restrict"));
				break;
				
			case "Admin":
				buttonReturnCheckout(frame, name, email, 250, s.equals("ReturnCheckout") );
				buttonAddUser(frame, name, email, 500, s.equals("AddUser"));
				buttonItemAdd(frame, name, email, 750, s.equals("ItemAdd"));
				buttonFindUser(frame, name, email, 1000, s.equals("FindUser"));
				buttonMyBook(frame, name, email, 1250, s.equals("MyBook"));
				buttonSearchBook(frame, name, email, 1500,s.equals("SearchBook"));
				break;
				
			case "Student":
				buttonMyBook(frame, name, email, 250, s.equals("MyBook") );
				buttonSearchBook(frame, name, email, 500,s.equals("SearchBook"));
				break;
		}
	}
	
	/**
	 * @param frame, input frame
	 * @param name, user type name
	 * @param email, email entered on login
	 * @param x, x coordinate variable of button
	 * @param s, true if its the selected button
	 */
	public static void buttonMyBook(JFrame frame, String name, String email, int x, boolean s) {
		JButton btnMyBooks = new JButton("My Books");
		btnMyBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentWindow.StudentS(name, email);
				frame.dispose();
			}
		});
		btnMyBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMyBooks.setBounds(x, buttonY, 223, 38);
		frame.getContentPane().add(btnMyBooks);
		
		if(s) {
			btnMyBooks.setForeground(lBlue);
			btnMyBooks.setBackground(dBlue);
		}
		else {
			btnMyBooks.setForeground(dBlue);
			btnMyBooks.setBackground(lBlue);
		}
	}
	
	/**
	 * @param frame, input frame
	 * @param name, user type name
	 * @param email, email entered on login
	 * @param x, x coordinate variable of button
	 * @param s, true if its the selected button
	 */
	public static void buttonSearchBook(JFrame frame,String name, String email, int x, boolean s) {
		JButton btnSearchBookLoc = new JButton("Search Books");
		btnSearchBookLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSearch.BookS(name, email);
				frame.dispose();
			}
		});
		btnSearchBookLoc.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSearchBookLoc.setBounds(x, buttonY, 223, 38);
		
		if(s) {
			btnSearchBookLoc.setForeground(lBlue);
			btnSearchBookLoc.setBackground(dBlue);
		}
		else {
			btnSearchBookLoc.setForeground(dBlue);
			btnSearchBookLoc.setBackground(lBlue);
		}
		
		frame.getContentPane().add(btnSearchBookLoc);
	}
	
	/**
	 * @param frame, input frame
	 * @param name, user type name
	 * @param email, email entered on login
	 * @param x, x coordinate variable of button
	 * @param s, true if its the selected button
	 */
	public static void buttonAddUser(JFrame frame, String name, String email, int x, boolean s) {
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUser.AddUserS(name, email);
				frame.dispose();
			}
		});
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAddUser.setBounds(x, buttonY, 223, 38);
		
		if(s) {
			btnAddUser.setForeground(lBlue);
			btnAddUser.setBackground(dBlue);
		}
		else {
			btnAddUser.setForeground(dBlue);
			btnAddUser.setBackground(lBlue);
		}
		
		frame.getContentPane().add(btnAddUser);
	}
	
	/**
	 * @param frame, input frame
	 * @param name, user type name
	 * @param email, email entered on login
	 * @param x, x coordinate variable of button
	 * @param s, true if its the selected button
	 */
	public static void buttonReturnCheckout(JFrame frame, String name, String email, int x, boolean s) {
		JButton btnAdd = new JButton("Return/Check-out");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClerkWindow.ClerkS(name, email);
				frame.dispose();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAdd.setBounds(x, buttonY, 223, 38);
		

		if(s) {
			btnAdd.setForeground(lBlue);
			btnAdd.setBackground(dBlue);
		}
		else {
			btnAdd.setForeground(dBlue);
			btnAdd.setBackground(lBlue);
		}
		
		frame.getContentPane().add(btnAdd);
	}
	
	/**
	 * @param frame, input frame
	 * @param name, user type name
	 * @param email, email entered on login
	 * @param x, x coordinate variable of button
	 * @param s, true if its the selected button
	 */
	public static void buttonRestrict(JFrame frame, String name, String email, int x, boolean s) {
		JButton btnRestrictBooks = new JButton("Restrict Books");
		btnRestrictBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRestrictBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestrictBooks.RestrictS(name, email);
				frame.dispose();
			}
		});
		btnRestrictBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRestrictBooks.setBounds(x, buttonY, 223, 38);
		

		if(s) {
			btnRestrictBooks.setForeground(lBlue);
			btnRestrictBooks.setBackground(dBlue);
		}
		else {
			btnRestrictBooks.setForeground(dBlue);
			btnRestrictBooks.setBackground(lBlue);
		}
		
		frame.getContentPane().add(btnRestrictBooks);
	}
	
	/**
	 * @param frame, input frame
	 * @param name, user type name
	 * @param email, email entered on login
	 * @param x, x coordinate variable of button
	 * @param s, true if its the selected button
	 */
	public static void buttonItemAdd(JFrame frame, String name, String email, int x, boolean s) {
		JButton btnItemAdd = new JButton("Item Add");
		btnItemAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnItemAdd.setBounds(x, buttonY, 223, 38);
		btnItemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemAdd.ItemS(name, email);
				frame.dispose();
			}
		});
		
		
		if(s) {
			btnItemAdd.setForeground(lBlue);
			btnItemAdd.setBackground(dBlue);
		}
		else {
			btnItemAdd.setForeground(dBlue);
			btnItemAdd.setBackground(lBlue);
		}
		
		frame.getContentPane().add(btnItemAdd);
	}
	
	/**
	 * @param frame, input frame
	 * @param name, user type name
	 * @param email, email entered on login
	 * @param x, x coordinate variable of button
	 * @param s, true if its the selected button
	 */
	public static void buttonFindUser(JFrame frame, String name, String email, int x, boolean s) {
		JButton btnFindUser = new JButton("User Search");
		btnFindUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnFindUser.setBounds(x, buttonY, 223, 38);
		btnFindUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindUser.FindS(name, email);
				frame.dispose();
			}
		});

		if(s) {
			btnFindUser.setForeground(lBlue);
			btnFindUser.setBackground(dBlue);
		}
		else {
			btnFindUser.setForeground(dBlue);
			btnFindUser.setBackground(lBlue);
		}
		
		frame.getContentPane().add(btnFindUser);
	}
}