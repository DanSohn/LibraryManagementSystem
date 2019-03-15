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

import database.DatabaseUtils;
import enums.UserType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.ResourceType;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.beans.PropertyChangeEvent;

public class AdminWindow {
	
	private JFrame			frame;
	private JTextField		TF_id;
	private JTextField		TF_title;
	private JTextField		TF_param1;
	private JTextField		TF_param2;
	private static String	email	= null;
	
	/**
	 * Launch the application.
	 */
	public void AdminS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow(email);
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
	public AdminWindow(String email) {
		this.email = email;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Style.mBlue);
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
		btnLogOut.setBounds(1030, 692, 97, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblLibraryStaff = new JLabel("Admin");
		lblLibraryStaff.setForeground(Style.dBlue);
		lblLibraryStaff.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblLibraryStaff.setBounds(684, 362, 223, 73);
		frame.getContentPane().add(lblLibraryStaff);
		
		JLabel lblId = new JLabel("type");
		lblId.setBounds(764, 452, 56, 16);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setForeground(Style.dBlue);
		TF_id.setBackground(Style.lBlue);
		TF_id.setColumns(10);
		TF_id.setBounds(851, 485, 116, 22);
		frame.getContentPane().add(TF_id);
		
		TF_title = new JTextField();
		TF_title.setForeground(Style.dBlue);
		TF_title.setBackground(Style.lBlue);
		TF_title.setColumns(10);
		TF_title.setBounds(851, 519, 116, 22);
		frame.getContentPane().add(TF_title);
		
		TF_param1 = new JTextField();
		TF_param1.setForeground(Style.dBlue);
		TF_param1.setBackground(Style.lBlue);
		TF_param1.setColumns(10);
		TF_param1.setBounds(851, 565, 116, 22);
		frame.getContentPane().add(TF_param1);
		
		TF_param2 = new JTextField();
		TF_param2.setForeground(Style.dBlue);
		TF_param2.setBackground(Style.lBlue);
		TF_param2.setColumns(10);
		TF_param2.setBounds(851, 600, 116, 22);
		frame.getContentPane().add(TF_param2);
		
		JLabel LB_id = new JLabel("id");
		LB_id.setBounds(760, 488, 84, 16);
		frame.getContentPane().add(LB_id);
		
		JLabel LB_title = new JLabel("title");
		LB_title.setBounds(755, 522, 84, 16);
		frame.getContentPane().add(LB_title);
		
		JLabel LB_param1 = new JLabel("author");
		LB_param1.setBounds(760, 568, 84, 16);
		frame.getContentPane().add(LB_param1);
		
		JLabel LB_param2 = new JLabel("location");
		LB_param2.setBounds(760, 603, 84, 16);
		frame.getContentPane().add(LB_param2);
		
		JComboBox CB_type = new JComboBox();
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
		CB_type.setBounds(851, 448, 107, 24);
		frame.getContentPane().add(CB_type);
		
		JButton btnAdd = new JButton("add");
		btnAdd.setForeground(Style.dBlue);
		btnAdd.setBackground(Style.lBlue);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DatabaseUtils.addNewResource(TF_id.getText(), TF_title.getText(),
						(ResourceType) CB_type.getSelectedItem(), TF_param1.getText(), TF_param2.getText());
			}
		});
		btnAdd.setBounds(997, 542, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBorder(null);
			frame.getContentPane().add(picLabel);
			picLabel.setBounds(0, 0, 1920, 250);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
