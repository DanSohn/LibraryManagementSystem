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

public class AddUser extends Style{
	
	private JFrame			frame;
	private JTextField		TF_id;
	private JTextField		TF_firstName;
	private JTextField		TF_lastName;
	private JTextField		TF_email;
	private JTextField		TF_password;
	
	/**
	 * Launch the application.
	 * @param name 
	 */
	public static void AddUserS(String name, String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser window = new AddUser(name, email);
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
	public AddUser(String name, String email) {
		frame = new JFrame();
		initialize();
		buttons(name, email, frame, "AddUser");
		setup(name, frame);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JLabel lblId = new JLabel("ID #");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblId.setBounds(813, 300, 56, 26);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_id.setColumns(10);
		TF_id.setBounds(881, 300, 186, 33);
		frame.getContentPane().add(TF_id);
		
		TF_firstName = new JTextField();
		TF_firstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_firstName.setColumns(10);
		TF_firstName.setBounds(881, 392, 186, 33);
		frame.getContentPane().add(TF_firstName);
		
		TF_lastName = new JTextField();
		TF_lastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_lastName.setColumns(10);
		TF_lastName.setBounds(881, 438, 186, 33);
		frame.getContentPane().add(TF_lastName);
		
		TF_email = new JTextField();
		TF_email.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_email.setColumns(10);
		TF_email.setBounds(881, 484, 186, 33);
		frame.getContentPane().add(TF_email);
		
		TF_password = new JTextField();
		TF_password.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_password.setColumns(10);
		TF_password.setBounds(881, 530, 186, 33);
		frame.getContentPane().add(TF_password);
		
		JLabel lblUserType = new JLabel("User type");
		lblUserType.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserType.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserType.setBounds(726, 348, 143, 29);
		frame.getContentPane().add(lblUserType);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFirstName.setBounds(736, 395, 133, 26);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLastName.setBounds(765, 441, 104, 26);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEmail.setBounds(765, 487, 104, 26);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(765, 532, 104, 29);
		frame.getContentPane().add(lblPassword);
		
		JComboBox CB_type = new JComboBox();
		CB_type.setFont(new Font("Tahoma", Font.PLAIN, 22));
		CB_type.setModel(new DefaultComboBoxModel(UserType.getNonStaffTypes()));
		CB_type.setBounds(881, 346, 186, 33);
		frame.getContentPane().add(CB_type);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDatabase.addNewUser(TF_id.getText(), (UserType) CB_type.getSelectedItem(), TF_firstName.getText(),
						TF_lastName.getText(), TF_email.getText(), TF_password.getText());
			}
		});
		btnAdd.setBounds(1108, 417, 116, 33);
		btnAdd.setForeground(dBlue);
		btnAdd.setBackground(lBlue);
		frame.getContentPane().add(btnAdd);
	}
}
