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

public class AddUser {
	
	private JFrame			frame;
	private JTextField		TF_id;
	private JTextField		TF_firstName;
	private JTextField		TF_lastName;
	private JTextField		TF_email;
	private JTextField		TF_password;
	
	/**
	 * Launch the application.
	 */
	public void AddUserS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser window = new AddUser();
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
	public AddUser() {
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
		
		JButton btnLogOut = new JButton("Back");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ClerkWindow().ClerkS();
			}
		});
		btnLogOut.setBackground(new Color(255, 140, 0));
		btnLogOut.setBounds(1743, 281, 116, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblId = new JLabel("ID #");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(63, 422, 56, 16);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_id.setColumns(10);
		TF_id.setBounds(131, 412, 186, 33);
		frame.getContentPane().add(TF_id);
		
		TF_firstName = new JTextField();
		TF_firstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_firstName.setColumns(10);
		TF_firstName.setBounds(131, 505, 186, 33);
		frame.getContentPane().add(TF_firstName);
		
		TF_lastName = new JTextField();
		TF_lastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_lastName.setColumns(10);
		TF_lastName.setBounds(131, 554, 186, 33);
		frame.getContentPane().add(TF_lastName);
		
		TF_email = new JTextField();
		TF_email.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_email.setColumns(10);
		TF_email.setBounds(131, 602, 186, 33);
		frame.getContentPane().add(TF_email);
		
		TF_password = new JTextField();
		TF_password.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_password.setColumns(10);
		TF_password.setBounds(131, 648, 186, 33);
		frame.getContentPane().add(TF_password);
		
		JLabel lblUserType = new JLabel("User type");
		lblUserType.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserType.setBounds(40, 463, 84, 29);
		frame.getContentPane().add(lblUserType);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstName.setBounds(15, 510, 104, 26);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastName.setBounds(15, 559, 104, 26);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(15, 612, 104, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(15, 652, 104, 29);
		frame.getContentPane().add(lblPassword);
		
		JComboBox CB_type = new JComboBox();
		CB_type.setFont(new Font("Tahoma", Font.PLAIN, 22));
		CB_type.setModel(new DefaultComboBoxModel(UserType.getNonStaffTypes()));
		CB_type.setBounds(131, 459, 186, 33);
		frame.getContentPane().add(CB_type);
		
		JButton btnAdd = new JButton("add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDatabase.addNewUser(TF_id.getText(), (UserType) CB_type.getSelectedItem(), TF_firstName.getText(),
						TF_lastName.getText(), TF_email.getText(), TF_password.getText());
			}
		});
		btnAdd.setBounds(365, 541, 97, 25);
		btnAdd.setForeground(Style.dBlue);
		btnAdd.setBackground(Style.lBlue);
		frame.getContentPane().add(btnAdd);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(0, 0, 1920, 250);
			
			JLabel lblClerk = new JLabel("Clerk");
			lblClerk.setForeground(new Color(3, 51, 89));
			lblClerk.setFont(new Font("Tahoma", Font.BOLD, 44));
			lblClerk.setBounds(25, 263, 223, 73);
			frame.getContentPane().add(lblClerk);
			
			JLabel lblAddUser = new JLabel("Add User");
			lblAddUser.setFont(new Font("Tahoma", Font.PLAIN, 34));
			lblAddUser.setBounds(26, 328, 461, 60);
			frame.getContentPane().add(lblAddUser);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
