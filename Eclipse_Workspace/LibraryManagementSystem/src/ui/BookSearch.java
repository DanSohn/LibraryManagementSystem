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

import database.ResourceDatabase;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.ResourceField;
import enums.ResourceType;
import java.io.File;
import java.io.IOException;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class BookSearch {
	
	JFrame			frame;
	private static String	email	= null;
	private static String	from	= null;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public void BookS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearch window = new BookSearch(email, from);
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
	public BookSearch(String email, String from) {
		this.email = email;
		this.from = from;
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
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setBounds(641, 466, 228, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSearch.setForeground(Style.dBlue);
		btnSearch.setBackground(Style.lBlue);

		btnSearch.setBounds(881, 466, 122, 33);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblNoSuchUser = new JLabel("No such book found");
		lblNoSuchUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNoSuchUser.setForeground(Color.RED);
		lblNoSuchUser.setBounds(1015, 471, 257, 24);
		frame.getContentPane().add(lblNoSuchUser);
		lblNoSuchUser.setVisible(false);
		
		JLabel lblUserFound = new JLabel("Book found");
		lblUserFound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserFound.setBounds(1015, 469, 122, 28);
		frame.getContentPane().add(lblUserFound);
		lblUserFound.setVisible(false);
		
		JTextPane textPane_0 = new JTextPane();
		textPane_0.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane_0.setBounds(641, 641, 619, 48);
		frame.getContentPane().add(textPane_0);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel(ResourceType.values()));
		comboBox.setBounds(641, 555, 194, 38);
		frame.getContentPane().add(comboBox);
		
		JLabel lblId_1 = new JLabel("ID #");
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblId_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblId_1.setBounds(641, 442, 148, 23);
		frame.getContentPane().add(lblId_1);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane_0.setText(ResourceDatabase.getParameterOfResource(textField.getText(), (ResourceType) comboBox.getSelectedItem(), ResourceField.LOCATION));
			}
		});
		
		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblType.setBounds(641, 515, 56, 38);
		frame.getContentPane().add(lblType);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLocation.setBounds(641, 606, 97, 38);
		frame.getContentPane().add(lblLocation);
		
		///////////////// main page setup
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
		//
		
		//title name
		JLabel lblTitle = new JLabel(from);
		lblTitle.setForeground(new Color(3, 51, 89));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblTitle.setBounds(25, 179, 223, 73);
		frame.getContentPane().add(lblTitle);
		//
		
		//search book location button
		JButton btnSearchBookLoc = new JButton("Search Book Location");
		btnSearchBookLoc.setForeground(new Color(3, 51, 89));
		btnSearchBookLoc.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnSearchBookLoc.setForeground(Style.lBlue);
		btnSearchBookLoc.setBackground(Style.dBlue);
		btnSearchBookLoc.setBounds(506, 205, 223, 38);
		frame.getContentPane().add(btnSearchBookLoc);
		//
		
		//selected
		//my books button
		JButton btnMyBooks = new JButton("My Books");
		btnMyBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StudentWindow.StudentS(email);
			}
		});
		btnMyBooks.setForeground(new Color(3, 51, 89));
		btnMyBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMyBooks.setBackground(new Color(230, 230, 240));
		btnMyBooks.setBounds(248, 204, 223, 38);
		frame.getContentPane().add(btnMyBooks);
		//
		
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
}