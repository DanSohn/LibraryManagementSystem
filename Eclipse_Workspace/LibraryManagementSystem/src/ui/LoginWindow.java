package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import users.Login;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class LoginWindow {
	
	JFrame					frame;
	private JTextField		textField;
	private JPasswordField	passwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.WHITE); // decode("#9B9B9B")
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(24, 137, 63, 16);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(94, 99, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(94, 134, 116, 22);
		frame.getContentPane().add(passwordField);
		
		JLabel lblUserName = new JLabel("Email");
		lblUserName.setBounds(48, 102, 31, 16);
		frame.getContentPane().add(lblUserName);
		
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		
		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblLogIn.setBounds(26, 0, 179, 73);
		frame.getContentPane().add(lblLogIn);
		
		JLabel lblinvalidemailPassword = new JLabel("<html>**Invalid email<br>or password**</html>");
		lblinvalidemailPassword.setForeground(Color.RED);
		lblinvalidemailPassword.setBounds(222, 99, 152, 38);
		lblinvalidemailPassword.setVisible(false);
		frame.getContentPane().add(lblinvalidemailPassword);
		
		String[]			bookTitles	= new String[] {
				"Student",
				"Faculty",
				"Library staff",
				"Clerk"
		};
		JComboBox<String>	comboBox	= new JComboBox(bookTitles);
		comboBox.setBounds(94, 64, 116, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((comboBox.getSelectedItem() == "Faculty")
						&& (Login.userLogin(textField.getText(), passwordField.getText(), "FACULTY"))) {
					frame.dispose();
					FacultyWindow newWin = new FacultyWindow(textField.getText());
					newWin.FacultyS();
				}
				if ((comboBox.getSelectedItem() == "Library staff")
						&& (Login.userLogin(textField.getText(), passwordField.getText(), "ADMIN"))) {
					frame.dispose();
					AdminWindow newWin = new AdminWindow(textField.getText());
					newWin.AdminS();
				}
				if ((comboBox.getSelectedItem() == "Student")
						&& (Login.userLogin(textField.getText(), passwordField.getText(), "STUDENT"))) {
					frame.dispose();
					StudentWindow newWin = new StudentWindow(textField.getText());
					newWin.StudentS();
				}
				if ((comboBox.getSelectedItem() == "Clerk")
						&& (Login.userLogin(textField.getText(), passwordField.getText(), "CLERK"))) {
					frame.dispose();
					ClerkWindow newWin = new ClerkWindow(textField.getText());
					newWin.ClerkS();
				} else
					lblinvalidemailPassword.setVisible(true);
			}
		});
		
		btnSubmit.setBackground(new Color(204, 255, 204));
		btnSubmit.setBounds(94, 169, 97, 25);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setBounds(24, 67, 63, 16);
		frame.getContentPane().add(lblUserType);
		
	}
}
