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
import enums.ResourceType;
import java.beans.PropertyChangeListener;
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
		
		JLabel lblLibraryStaff = new JLabel("Admin");
		lblLibraryStaff.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblLibraryStaff.setBounds(26, 0, 223, 73);
		frame.getContentPane().add(lblLibraryStaff);
		
		JLabel lblId = new JLabel("type");
		lblId.setBounds(26, 85, 56, 16);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setColumns(10);
		TF_id.setBounds(91, 111, 116, 22);
		frame.getContentPane().add(TF_id);
		
		TF_title = new JTextField();
		TF_title.setColumns(10);
		TF_title.setBounds(91, 140, 116, 22);
		frame.getContentPane().add(TF_title);
		
		TF_param1 = new JTextField();
		TF_param1.setColumns(10);
		TF_param1.setBounds(91, 170, 116, 22);
		frame.getContentPane().add(TF_param1);
		
		TF_param2 = new JTextField();
		TF_param2.setColumns(10);
		TF_param2.setBounds(91, 200, 116, 22);
		frame.getContentPane().add(TF_param2);
		
		JLabel LB_id = new JLabel("id");
		LB_id.setBounds(26, 113, 84, 16);
		frame.getContentPane().add(LB_id);
		
		JLabel LB_title = new JLabel("title");
		LB_title.setBounds(26, 142, 84, 16);
		frame.getContentPane().add(LB_title);
		
		JLabel LB_param1 = new JLabel("author");
		LB_param1.setBounds(26, 172, 84, 16);
		frame.getContentPane().add(LB_param1);
		
		JLabel LB_param2 = new JLabel("location");
		LB_param2.setBounds(26, 202, 84, 16);
		frame.getContentPane().add(LB_param2);
		
		JComboBox CB_type = new JComboBox();
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
		CB_type.setBounds(101, 77, 107, 24);
		frame.getContentPane().add(CB_type);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DatabaseUtils.addNewResource(TF_id.getText(), TF_title.getText(),
						(ResourceType) CB_type.getSelectedItem(), TF_param1.getText(), TF_param2.getText());
			}
		});
		btnAdd.setBounds(219, 138, 97, 25);
		frame.getContentPane().add(btnAdd);
	}
}
