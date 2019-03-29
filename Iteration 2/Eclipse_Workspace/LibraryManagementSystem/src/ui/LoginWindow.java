package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import users.Login;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import enums.UserType;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginWindow {
	
	JFrame					frame;
	private JTextField		txtEmail;
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
		frame.setForeground(Style.dBlue);
		frame.setBackground(Style.dBlue);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0,screen.width,screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		frame.getContentPane().add(new JLabel(new ImageIcon("test.png")));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Style.dBlue);
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPassword.setBounds(690, 550, 197, 38);
		frame.getContentPane().add(lblPassword);
		
		txtEmail = new JTextField();

		txtEmail.setBorder(new LineBorder(Style.dBlue));
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setForeground(Style.mBlue);
		txtEmail.setBackground(Style.lBlue);
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setBounds(900, 500, 200, 38);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(Style.dBlue));
		passwordField.setForeground(Color.BLACK);
		passwordField.setBackground(Style.lBlue);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(900, 550, 200, 38);
		frame.getContentPane().add(passwordField);
		
		JLabel lblUserName = new JLabel("Email");
		lblUserName.setForeground(Style.dBlue);
		lblUserName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUserName.setBounds(719, 498, 168, 38);
		frame.getContentPane().add(lblUserName);
		
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setForeground(Style.dBlue);
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblLogIn.setBounds(889, 381, 200, 73);
		frame.getContentPane().add(lblLogIn);
		
		JLabel lblinvalidemailPassword = new JLabel("<html>Invalid email or password</html>");
		lblinvalidemailPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblinvalidemailPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblinvalidemailPassword.setForeground(new Color(255, 69, 0));
		lblinvalidemailPassword.setBounds(800, 642, 391, 38);
		lblinvalidemailPassword.setVisible(false);
		frame.getContentPane().add(lblinvalidemailPassword);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBorder(new LineBorder(Style.dBlue));
		comboBox.setBackground(Style.lBlue);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(UserType.values()));
		comboBox.setBounds(900, 450, 200, 38);
		frame.getContentPane().add(comboBox);
		
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserType type = (UserType) comboBox.getSelectedItem();
				
				if (Login.userLogin(txtEmail.getText(), passwordField.getText(), type)) {
					
					switch (type) {
						case ADMIN:
							frame.dispose();
							new AdminWindow().AdminS();
							break;
						case CLERK:
							frame.dispose();
							new ClerkWindow().ClerkS();
							break;
						case FACULTY:
							frame.dispose();
							new FacultyWindow(txtEmail.getText()).FacultyS();
							break;
						case STUDENT:
							frame.dispose();
							new StudentWindow(txtEmail.getText()).StudentS();
							break;
					}
					
				} else {
					lblinvalidemailPassword.setVisible(true);
				}
				
			}
		});
		
		btnSubmit.setBackground(Style.mBlue);
		btnSubmit.setBounds(900, 600, 200, 38);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setForeground(Style.dBlue);
		lblUserType.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUserType.setBounds(719, 450, 168, 38);
		frame.getContentPane().add(lblUserType);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("test.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(-12, 0, 1939, 1020);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

