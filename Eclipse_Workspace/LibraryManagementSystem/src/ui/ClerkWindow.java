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
import java.io.IOException;

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
	private JTextField usetTxt;
	private JTextField bookTxt;
	
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
		btnLogOut.setBounds(997, 608, 97, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblLibraryStaff = new JLabel("Clerk");
		lblLibraryStaff.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblLibraryStaff.setBounds(694, 290, 223, 73);
		frame.getContentPane().add(lblLibraryStaff);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(720, 389, 56, 16);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setColumns(10);
		TF_id.setBounds(788, 376, 116, 22);
		frame.getContentPane().add(TF_id);
		
		TF_firstName = new JTextField();
		TF_firstName.setColumns(10);
		TF_firstName.setBounds(788, 455, 116, 22);
		frame.getContentPane().add(TF_firstName);
		
		TF_lastName = new JTextField();
		TF_lastName.setColumns(10);
		TF_lastName.setBounds(788, 487, 116, 22);
		frame.getContentPane().add(TF_lastName);
		
		TF_email = new JTextField();
		TF_email.setColumns(10);
		TF_email.setBounds(788, 519, 116, 22);
		frame.getContentPane().add(TF_email);
		
		TF_password = new JTextField();
		TF_password.setColumns(10);
		TF_password.setBounds(788, 554, 116, 22);
		frame.getContentPane().add(TF_password);
		
		JLabel lblUserType = new JLabel("user type");
		lblUserType.setBounds(708, 422, 84, 16);
		frame.getContentPane().add(lblUserType);
		
		JLabel lblFirstName = new JLabel("first name");
		lblFirstName.setBounds(694, 458, 84, 16);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("last name");
		lblLastName.setBounds(694, 490, 84, 16);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(692, 522, 84, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(694, 557, 84, 16);
		frame.getContentPane().add(lblPassword);
		
		JComboBox CB_type = new JComboBox();
		CB_type.setModel(new DefaultComboBoxModel(UserType.getNonStaffTypes()));
		CB_type.setBounds(798, 418, 107, 24);
		frame.getContentPane().add(CB_type);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserDatabase.addNewUser(TF_id.getText(), (UserType) CB_type.getSelectedItem(), TF_firstName.getText(),
						TF_lastName.getText(), TF_email.getText(), TF_password.getText());
			}
		});
		btnAdd.setBounds(940, 435, 97, 25);
		frame.getContentPane().add(btnAdd);
		


		
		usetTxt = new JTextField();
		usetTxt.setColumns(10);
		usetTxt.setBounds(85, 401, 116, 22);
		frame.getContentPane().add(usetTxt);
		
		bookTxt = new JTextField();
		bookTxt.setColumns(10);
		bookTxt.setBounds(85, 436, 116, 22);
		frame.getContentPane().add(bookTxt);
		
		JLabel lblUserId = new JLabel("user id");
		lblUserId.setBounds(26, 404, 56, 16);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblBookId = new JLabel("book id");
		lblBookId.setBounds(26, 439, 56, 16);
		frame.getContentPane().add(lblBookId);
		
		JLabel lblDone = new JLabel("Done!");
		lblDone.setForeground(Color.GREEN);
		lblDone.setBounds(362, 404, 56, 16);
		frame.getContentPane().add(lblDone);
		lblDone.setVisible(false);
		
		JLabel lblDone_2 = new JLabel("Done!");
		lblDone_2.setForeground(Color.GREEN);
		lblDone_2.setBounds(362, 439, 56, 16);
		frame.getContentPane().add(lblDone_2);
		lblDone_2.setVisible(false);
		
		
		JButton btnNewButton = new JButton("Check out");
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
		
		btnNewButton.setBounds(234, 400, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(new Clerk().returnResource(bookTxt.getText(), usetTxt.getText()) > 0) {
					lblDone_2.setVisible(true);
				}
				
			}
		});
		btnReturn.setBounds(234, 435, 97, 25);
		frame.getContentPane().add(btnReturn);
		

	}
}
