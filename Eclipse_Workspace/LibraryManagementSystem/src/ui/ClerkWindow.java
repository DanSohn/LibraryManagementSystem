package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import database.DatabaseUtils;
import enums.UserType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ClerkWindow {
	
	private JFrame			frame;
	private JTextField		TF_id;
	private JTextField		TF_firstName;
	private JTextField		TF_lastName;
	private JTextField		TF_email;
	private JTextField		TF_password;
	private static String	email	= null;
	
	/**
	 * Launch the application.
	 */
	public static void ClerkS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClerkWindow window = new ClerkWindow(email);
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
	public ClerkWindow(String email) {
		this.email = email;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 465, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginWindow newWin = new LoginWindow();
				newWin.frame.setVisible(true);
			}
		});
		btnLogOut.setBackground(new Color(255, 102, 102));
		btnLogOut.setBounds(338, 229, 97, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblLibraryStaff = new JLabel("Clerk");
		lblLibraryStaff.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblLibraryStaff.setBounds(26, 0, 223, 73);
		frame.getContentPane().add(lblLibraryStaff);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(71, 81, 56, 16);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setColumns(10);
		TF_id.setBounds(91, 80, 116, 22);
		frame.getContentPane().add(TF_id);
		
		TF_firstName = new JTextField();
		TF_firstName.setColumns(10);
		TF_firstName.setBounds(91, 140, 116, 22);
		frame.getContentPane().add(TF_firstName);
		
		TF_lastName = new JTextField();
		TF_lastName.setColumns(10);
		TF_lastName.setBounds(91, 170, 116, 22);
		frame.getContentPane().add(TF_lastName);
		
		TF_email = new JTextField();
		TF_email.setColumns(10);
		TF_email.setBounds(91, 200, 116, 22);
		frame.getContentPane().add(TF_email);
		
		TF_password = new JTextField();
		TF_password.setColumns(10);
		TF_password.setBounds(91, 230, 116, 22);
		frame.getContentPane().add(TF_password);
		
		JLabel lblUserType = new JLabel("user type");
		lblUserType.setBounds(28, 113, 84, 16);
		frame.getContentPane().add(lblUserType);
		
		JLabel lblFirstName = new JLabel("first name");
		lblFirstName.setBounds(26, 140, 84, 16);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("last name");
		lblLastName.setBounds(26, 173, 84, 16);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(50, 205, 84, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(26, 233, 84, 16);
		frame.getContentPane().add(lblPassword);
		
		JComboBox CB_type = new JComboBox();
		CB_type.setModel(new DefaultComboBoxModel(UserType.values()));
		CB_type.setBounds(101, 109, 107, 24);
		frame.getContentPane().add(CB_type);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DatabaseUtils.addNewUser(Integer.parseInt(TF_id.getText()), (UserType) CB_type.getSelectedItem(),
						TF_firstName.getText(), TF_lastName.getText(), TF_email.getText(), TF_password.getText());
			}
		});
		btnAdd.setBounds(219, 138, 97, 25);
		frame.getContentPane().add(btnAdd);
	}
}
