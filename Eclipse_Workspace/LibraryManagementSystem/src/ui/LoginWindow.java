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
import javax.swing.KeyStroke;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import enums.UserType;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginWindow{
	
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
		lblPassword.setBounds(691, 518, 197, 38);
		frame.getContentPane().add(lblPassword);
		
		txtEmail = new JTextField();

		txtEmail.setBorder(new LineBorder(Style.dBlue));
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setForeground(Style.mBlue);
		txtEmail.setBackground(Style.lBlue);
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setBounds(900, 467, 200, 38);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(Style.dBlue));
		passwordField.setForeground(Color.BLACK);
		passwordField.setBackground(Style.lBlue);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(900, 518, 200, 38);
		frame.getContentPane().add(passwordField);
		
		JLabel lblUserName = new JLabel("Email");
		lblUserName.setForeground(Style.dBlue);
		lblUserName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUserName.setBounds(719, 465, 168, 38);
		frame.getContentPane().add(lblUserName);
		
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setForeground(Style.dBlue);
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblLogIn.setBounds(887, 393, 200, 73);
		frame.getContentPane().add(lblLogIn);
		
		JLabel ForgotPass = new JLabel("Forgot Password?");
		ForgotPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtEmail.getText().length() > 2) {
					MessageBox.MessageS("Recovery Email Sent");
				}
				else {
					MessageBox.MessageS("Who Are You Tho? Enter Email In Field.");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ForgotPass.setForeground(Style.lBlue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ForgotPass.setForeground(Style.mBlue);
			}
		});
		ForgotPass.setForeground(Style.mBlue);
		ForgotPass.setHorizontalAlignment(SwingConstants.CENTER);
		ForgotPass.setFont(new Font("Tahoma", Font.BOLD, 22));
		ForgotPass.setBounds(1535, 950, 528, 38);
		frame.getContentPane().add(ForgotPass);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Style.mBlue));
		panel.setBounds(1700, 980, 197, 2);
		frame.getContentPane().add(panel);
		
		//Invalid password texts                                   //////////////////////////////// not working
		//front
		JLabel lblinvalidemailPassword2 = new JLabel("Invalid email or password");
		lblinvalidemailPassword2.setHorizontalAlignment(SwingConstants.CENTER);
		lblinvalidemailPassword2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblinvalidemailPassword2.setForeground(new Color(255, 255, 255));
		lblinvalidemailPassword2.setBounds(800, 629, 391, 38);
		lblinvalidemailPassword2.setVisible(false);
		frame.getContentPane().add(lblinvalidemailPassword2);
		//back top left
		JLabel lblinvalidemailPassword = new JLabel("Invalid email or password");
		lblinvalidemailPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblinvalidemailPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblinvalidemailPassword.setForeground(new Color(255, 0, 0));
		lblinvalidemailPassword.setBounds(799, 628, 391, 38);
		lblinvalidemailPassword.setVisible(false);
		frame.getContentPane().add(lblinvalidemailPassword);
		//back bottom right
		JLabel lblinvalidemailPassword3 = new JLabel("Invalid email or password");
		lblinvalidemailPassword3.setHorizontalAlignment(SwingConstants.CENTER);
		lblinvalidemailPassword3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblinvalidemailPassword3.setForeground(new Color(255, 0, 0));
		lblinvalidemailPassword3.setBounds(802, 631, 391, 38);
		lblinvalidemailPassword3.setVisible(false);
		frame.getContentPane().add(lblinvalidemailPassword3);
		//////
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pass();
				lblinvalidemailPassword.setVisible(true);
				lblinvalidemailPassword2.setVisible(true);
				lblinvalidemailPassword3.setVisible(true);
			}
		});
		btnSubmit.setBackground(Style.mBlue);
		btnSubmit.setBounds(900, 569, 200, 38);
		frame.getContentPane().add(btnSubmit);
		frame.getRootPane().setDefaultButton(btnSubmit); //sets enter key to submit
		
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
	public void pass(){
		UserType type = Login.userLogin(txtEmail.getText(), passwordField.getText());
		if (type != null) {
			
			switch (type) {
				case ADMIN:
					frame.dispose();
					AdminWindow.AdminS("Admin", txtEmail.getText());
					break;
				case CLERK:
					frame.dispose();
					ClerkWindow.ClerkS("Clerk", txtEmail.getText());
					break;
				case FACULTY:
					frame.dispose();
					FacultyWindow.FacultyS("Faculty", txtEmail.getText());
					break;
				case STUDENT:
					frame.dispose();
					StudentWindow.StudentS("Student", txtEmail.getText());
					break;
			}
		}
	}
}

