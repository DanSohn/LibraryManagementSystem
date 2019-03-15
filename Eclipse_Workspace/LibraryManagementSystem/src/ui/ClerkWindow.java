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
	public void ClerkS() {
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
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0,screen.width,screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginWindow newWin = new LoginWindow();
				newWin.frame.setVisible(true);
			}
		});
		btnLogOut.setBackground(new Color(255, 102, 102));
		btnLogOut.setBounds(344, 228, 97, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblLibraryStaff = new JLabel("Clerk");
		lblLibraryStaff.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblLibraryStaff.setBounds(26, 0, 223, 73);
		frame.getContentPane().add(lblLibraryStaff);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(26, 87, 56, 16);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setColumns(10);
		TF_id.setBounds(120, 85, 116, 22);
		frame.getContentPane().add(TF_id);
		
		TF_firstName = new JTextField();
		TF_firstName.setColumns(10);
		TF_firstName.setBounds(120, 136, 116, 22);
		frame.getContentPane().add(TF_firstName);
		
		TF_lastName = new JTextField();
		TF_lastName.setColumns(10);
		TF_lastName.setBounds(120, 166, 116, 22);
		frame.getContentPane().add(TF_lastName);
		
		TF_email = new JTextField();
		TF_email.setColumns(10);
		TF_email.setBounds(120, 196, 116, 22);
		frame.getContentPane().add(TF_email);
		
		TF_password = new JTextField();
		TF_password.setColumns(10);
		TF_password.setBounds(120, 230, 116, 22);
		frame.getContentPane().add(TF_password);
		
		JLabel lblUserType = new JLabel("user type");
		lblUserType.setBounds(26, 112, 84, 16);
		frame.getContentPane().add(lblUserType);
		
		JLabel lblFirstName = new JLabel("first name");
		lblFirstName.setBounds(26, 138, 84, 16);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("last name");
		lblLastName.setBounds(26, 168, 84, 16);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(26, 198, 84, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(26, 232, 84, 16);
		frame.getContentPane().add(lblPassword);
		
		JComboBox CB_type = new JComboBox();
		CB_type.setModel(new DefaultComboBoxModel(UserType.getNonStaffTypes()));
		CB_type.setBounds(120, 109, 107, 24);
		frame.getContentPane().add(CB_type);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDatabase.addNewUser(TF_id.getText(), (UserType) CB_type.getSelectedItem(), TF_firstName.getText(),
						TF_lastName.getText(), TF_email.getText(), TF_password.getText());
			}
		});
		btnAdd.setBounds(248, 134, 97, 25);
		frame.getContentPane().add(btnAdd);
	}
}
