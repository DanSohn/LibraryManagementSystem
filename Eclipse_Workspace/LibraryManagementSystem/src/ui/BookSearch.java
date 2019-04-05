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

public class BookSearch extends Style{
	
	JFrame frame;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 * @param name 
	 */
	public static void BookS(String name, String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSearch window = new BookSearch(name, email);
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
	public BookSearch(String name, String email) {
		frame = new JFrame();
		initialize();
		buttons(name, email, frame, "SearchBook");
		setup(name, frame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setBounds(641, 337, 228, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSearch.setForeground(dBlue);
		btnSearch.setBackground(lBlue);

		btnSearch.setBounds(885, 337, 122, 33);
		frame.getContentPane().add(btnSearch);
		
		JTextPane textPane_0 = new JTextPane();
		textPane_0.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textPane_0.setBounds(641, 510, 619, 48);
		frame.getContentPane().add(textPane_0);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel(ResourceType.values()));
		comboBox.setBounds(641, 420, 194, 38);
		frame.getContentPane().add(comboBox);
		
		JLabel lblId_1 = new JLabel("ID #");
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblId_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblId_1.setBounds(641, 305, 148, 33);
		frame.getContentPane().add(lblId_1);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane_0.setText(ResourceDatabase.getParameterOfResource(textField.getText(), (ResourceType) comboBox.getSelectedItem(), ResourceField.LOCATION));
			}
		});
		
		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblType.setBounds(641, 383, 56, 38);
		frame.getContentPane().add(lblType);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLocation.setBounds(641, 472, 97, 38);
		frame.getContentPane().add(lblLocation);
	}
}