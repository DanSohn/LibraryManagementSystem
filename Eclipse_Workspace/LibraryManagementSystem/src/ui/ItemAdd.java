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
import database.UserDatabase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import enums.ResourceType;
import enums.UserType;
import utils.Utilities;

import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;

public class ItemAdd extends Style {
	
	private JFrame			frame;
	private JTextField		TF_id;
	private JTextField		TF_title;
	private JTextField		TF_param1;
	private JTextField		TF_param2;
	private static String	email	= null;
	private static String	name	= null;
	
	/**
	 * Launch the application.
	 */
	public static void ItemS(String name, String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemAdd window = new ItemAdd(name, email);
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
	public ItemAdd(String name, String email) {
		this.name = name;
		frame = new JFrame();
		initialize();
		buttons(name, email, frame, "ItemAdd");
		setup(name, frame);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//text fields
		//id text field
		TF_id = new JTextField();
		TF_id.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_id.setColumns(10);
		TF_id.setBounds(754, 353, 268, 38);
		frame.getContentPane().add(TF_id);
		
		//title text field
		TF_title = new JTextField();
		TF_title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_title.setColumns(10);
		TF_title.setBounds(754, 404, 268, 38);
		frame.getContentPane().add(TF_title);
		
		//author or producer text field
		TF_param1 = new JTextField();
		TF_param1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_param1.setColumns(10);
		TF_param1.setBounds(754, 455, 268, 38);
		frame.getContentPane().add(TF_param1);
		
		//location or url text field
		TF_param2 = new JTextField();
		TF_param2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_param2.setColumns(10);
		TF_param2.setBounds(754, 506, 268, 38);
		frame.getContentPane().add(TF_param2);
		
		//type label
		JLabel lblId = new JLabel("Type");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblId.setBounds(658, 310, 84, 23);
		frame.getContentPane().add(lblId);
		
		//id label
		JLabel LB_id = new JLabel("ID");
		LB_id.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LB_id.setBounds(658, 363, 84, 19);
		frame.getContentPane().add(LB_id);
		
		//title label
		JLabel LB_title = new JLabel("Title");
		LB_title.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LB_title.setBounds(658, 414, 84, 19);
		frame.getContentPane().add(LB_title);
		
		//author label, changes to producer for movies
		JLabel LB_param1 = new JLabel("Author");
		LB_param1.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_param1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LB_param1.setBounds(658, 465, 84, 19);
		frame.getContentPane().add(LB_param1);
		
		//location label, changes to url for e-books
		JLabel LB_param2 = new JLabel("Location");
		LB_param2.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_param2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		LB_param2.setBounds(658, 508, 84, 33);
		frame.getContentPane().add(LB_param2);
		
		//type dropdown menu, changes the input text fields as necessary 
		JComboBox CB_type = new JComboBox();
		CB_type.setFont(new Font("Tahoma", Font.PLAIN, 22));
		CB_type.setForeground(dBlue);
		CB_type.setBackground(lBlue);
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
		CB_type.setBounds(754, 302, 268, 38);
		frame.getContentPane().add(CB_type);
		
		//add button
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAdd.setForeground(dBlue);
		btnAdd.setBackground(lBlue);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Utilities.isValidInput(TF_id.getText()) && Utilities.isValidInput(TF_title.getText()) && Utilities.isValidInput(TF_param1.getText()) 
						&& Utilities.isValidInput(TF_param2.getText())) {
					ResourceDatabase.addNewResource(TF_id.getText(), TF_title.getText(),
							(ResourceType) CB_type.getSelectedItem(), TF_param1.getText(), TF_param2.getText());
				}
				else {
					MessageBox.MessageS("Fields cannot be blank or contain \"*\"");
				}				

			}
		});
		btnAdd.setBounds(1050, 407, 97, 30);
		frame.getContentPane().add(btnAdd);
	}
}