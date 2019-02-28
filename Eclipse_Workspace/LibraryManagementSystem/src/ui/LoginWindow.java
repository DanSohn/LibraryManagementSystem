package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import enums.UserType;
import javax.swing.JButton;

public class LoginWindow {
	
	private JFrame frame;
	private JPasswordField Input_Password;
	private JTextField Input_Username;
	
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
		frame.setBounds(100, 100, 450, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Label_Login = new JLabel("Login");
		Label_Login.setFont(new Font("Dialog", Font.BOLD, 20));
		Label_Login.setBounds(30, 50, 120, 24);
		frame.getContentPane().add(Label_Login);
		
		JLabel Label_Username = new JLabel("Username:");
		Label_Username.setFont(new Font("Dialog", Font.BOLD, 20));
		Label_Username.setBounds(30, 120, 152, 35);
		frame.getContentPane().add(Label_Username);
		
		JLabel Label_Password = new JLabel("Password:");
		Label_Password.setFont(new Font("Dialog", Font.BOLD, 20));
		Label_Password.setBounds(30, 180, 152, 35);
		frame.getContentPane().add(Label_Password);
		
		JLabel Label_UserType = new JLabel("Login As:");
		Label_UserType.setFont(new Font("Dialog", Font.BOLD, 20));
		Label_UserType.setBounds(30, 240, 152, 35);
		frame.getContentPane().add(Label_UserType);
		
		Input_Password = new JPasswordField();
		Input_Password.setFont(new Font("Dialog", Font.PLAIN, 16));
		Input_Password.setBounds(180, 182, 200, 35);
		frame.getContentPane().add(Input_Password);
		
		Input_Username = new JTextField();
		Input_Username.setFont(new Font("Dialog", Font.PLAIN, 16));
		Input_Username.setBounds(180, 122, 200, 35);
		frame.getContentPane().add(Input_Username);
		Input_Username.setColumns(10);
		
		JComboBox Input_UserType = new JComboBox();
		Input_UserType.setFont(new Font("Dialog", Font.BOLD, 16));
		Input_UserType.setModel(new DefaultComboBoxModel(UserType.values()));
		Input_UserType.setBounds(180, 242, 200, 35);
		frame.getContentPane().add(Input_UserType);
		
		JButton Button_Login = new JButton("Login");
		Button_Login.setFont(new Font("Dialog", Font.BOLD, 16));
		Button_Login.setBounds(162, 340, 114, 25);
		frame.getContentPane().add(Button_Login);
	}
}
