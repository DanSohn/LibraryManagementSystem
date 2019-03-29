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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.ResourceType;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;

public class ItemAdd {
	
	private JFrame			frame;
	private JTextField		TF_id;
	private JTextField		TF_title;
	private JTextField		TF_param1;
	private JTextField		TF_param2;
	private static String	email	= null;
	
	/**
	 * Launch the application.
	 */
	public void ItemS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemAdd window = new ItemAdd();
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
	public ItemAdd() {
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
		
		JLabel lblId = new JLabel("Type");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblId.setBounds(658, 443, 84, 23);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_id.setForeground(Style.dBlue);
		TF_id.setBackground(Style.lBlue);
		TF_id.setColumns(10);
		TF_id.setBounds(754, 486, 268, 38);
		frame.getContentPane().add(TF_id);
		
		TF_title = new JTextField();
		TF_title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_title.setForeground(Style.dBlue);
		TF_title.setBackground(Style.lBlue);
		TF_title.setColumns(10);
		TF_title.setBounds(754, 537, 268, 38);
		frame.getContentPane().add(TF_title);
		
		TF_param1 = new JTextField();
		TF_param1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_param1.setForeground(Style.dBlue);
		TF_param1.setBackground(Style.lBlue);
		TF_param1.setColumns(10);
		TF_param1.setBounds(754, 584, 268, 38);
		frame.getContentPane().add(TF_param1);
		
		TF_param2 = new JTextField();
		TF_param2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_param2.setForeground(Style.dBlue);
		TF_param2.setBackground(Style.lBlue);
		TF_param2.setColumns(10);
		TF_param2.setBounds(754, 635, 268, 38);
		frame.getContentPane().add(TF_param2);
		
		JLabel LB_id = new JLabel("ID");
		LB_id.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LB_id.setBounds(658, 496, 84, 19);
		frame.getContentPane().add(LB_id);
		
		JLabel LB_title = new JLabel("Title");
		LB_title.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LB_title.setBounds(658, 547, 84, 19);
		frame.getContentPane().add(LB_title);
		
		JLabel LB_param1 = new JLabel("Author");
		LB_param1.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_param1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LB_param1.setBounds(658, 594, 84, 19);
		frame.getContentPane().add(LB_param1);
		
		JLabel LB_param2 = new JLabel("Location");
		LB_param2.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_param2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		LB_param2.setBounds(658, 637, 84, 33);
		frame.getContentPane().add(LB_param2);
		
		JComboBox CB_type = new JComboBox();
		CB_type.setFont(new Font("Tahoma", Font.PLAIN, 22));
		CB_type.setForeground(Style.dBlue);
		CB_type.setBackground(Style.lBlue);
		CB_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch ((ResourceType) CB_type.getSelectedItem()) {
					case BOOK:
						LB_param1.setText("author");
						LB_param2.setText("location");
						break;
					case DVD:
						LB_param1.setText("producer");
						LB_param2.setText("location");
						break;
					case ONLINE:
						LB_param1.setText("author");
						LB_param2.setText("url");
						break;
				}
			}
		});
		CB_type.setModel(new DefaultComboBoxModel(ResourceType.values()));
		CB_type.setBounds(754, 435, 268, 38);
		frame.getContentPane().add(CB_type);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAdd.setForeground(Style.dBlue);
		btnAdd.setBackground(Style.lBlue);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResourceDatabase.addNewResource(TF_id.getText(), TF_title.getText(),
						(ResourceType) CB_type.getSelectedItem(), TF_param1.getText(), TF_param2.getText());
			}
		});
		btnAdd.setBounds(1074, 540, 97, 30);
		frame.getContentPane().add(btnAdd);

		
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
		JLabel lblTitle = new JLabel("Admin");
		lblTitle.setForeground(new Color(3, 51, 89));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblTitle.setBounds(25, 179, 223, 73);
		frame.getContentPane().add(lblTitle);
		//
		
		//return checkout books button
		JButton btnRC = new JButton("Return/Check-out");
		btnRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new BookSignOut().SignOutS();
			}
		});
		btnRC.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRC.setBounds(243, 204, 223, 38);
		btnRC.setForeground(new Color(3, 51, 89));
		btnRC.setBounds(1183, 204, 223, 38);
		frame.getContentPane().add(btnRC);
		//
		
		//add user button
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AddUser().AddUserS();
			}
		});
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAddUser.setBounds(478, 204, 223, 38);
		btnAddUser.setForeground(Style.dBlue);
		btnAddUser.setBackground(Style.lBlue);
		frame.getContentPane().add(btnAddUser);
		//
		
		//search book location button
		JButton btnSearchBookLoc = new JButton("Search Book Location");
		btnSearchBookLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookSearch("email","Admin").BookS();
				frame.dispose();
			}
		});
		btnSearchBookLoc.setForeground(new Color(3, 51, 89));
		btnSearchBookLoc.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnSearchBookLoc.setBackground(new Color(230, 230, 240));
		btnSearchBookLoc.setBounds(713, 204, 223, 38);
		frame.getContentPane().add(btnSearchBookLoc);
		//
		
		//my books button
		JButton btnMyBooks = new JButton("My Books");
		btnMyBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMyBooks.setForeground(new Color(3, 51, 89));
		btnMyBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMyBooks.setBackground(new Color(230, 230, 240));
		btnMyBooks.setBounds(948, 204, 223, 38);
		frame.getContentPane().add(btnMyBooks);
		//
		
		//selected
		//Item add button
		JButton btnItemAdd = new JButton("Item Add");
		btnItemAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnItemAdd.setBackground(new Color(230, 230, 240));
		btnItemAdd.setForeground(Style.lBlue);
		btnItemAdd.setBackground(Style.dBlue);
		frame.getContentPane().add(btnItemAdd);
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