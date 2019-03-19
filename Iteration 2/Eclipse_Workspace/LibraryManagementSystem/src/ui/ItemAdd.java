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
import javax.swing.border.LineBorder;
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
		
		JButton btnLogOut = new JButton("Back");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AdminWindow().AdminS();
			}
		});
		btnLogOut.setBackground(new Color(255, 140, 0));
		btnLogOut.setBounds(1762, 299, 97, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblLibraryStaff = new JLabel("Admin");
		lblLibraryStaff.setForeground(Style.dBlue);
		lblLibraryStaff.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblLibraryStaff.setBounds(10, 263, 223, 73);
		frame.getContentPane().add(lblLibraryStaff);
		
		JLabel lblId = new JLabel("Type");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(10, 420, 84, 23);
		frame.getContentPane().add(lblId);
		
		TF_id = new JTextField();
		TF_id.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_id.setForeground(Style.dBlue);
		TF_id.setBackground(Style.lBlue);
		TF_id.setColumns(10);
		TF_id.setBounds(106, 459, 142, 28);
		frame.getContentPane().add(TF_id);
		
		TF_title = new JTextField();
		TF_title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_title.setForeground(Style.dBlue);
		TF_title.setBackground(Style.lBlue);
		TF_title.setColumns(10);
		TF_title.setBounds(106, 500, 142, 28);
		frame.getContentPane().add(TF_title);
		
		TF_param1 = new JTextField();
		TF_param1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_param1.setForeground(Style.dBlue);
		TF_param1.setBackground(Style.lBlue);
		TF_param1.setColumns(10);
		TF_param1.setBounds(106, 541, 142, 28);
		frame.getContentPane().add(TF_param1);
		
		TF_param2 = new JTextField();
		TF_param2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TF_param2.setForeground(Style.dBlue);
		TF_param2.setBackground(Style.lBlue);
		TF_param2.setColumns(10);
		TF_param2.setBounds(106, 582, 142, 28);
		frame.getContentPane().add(TF_param2);
		
		JLabel LB_id = new JLabel("ID");
		LB_id.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_id.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LB_id.setBounds(10, 466, 84, 19);
		frame.getContentPane().add(LB_id);
		
		JLabel LB_title = new JLabel("Title");
		LB_title.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LB_title.setBounds(10, 507, 84, 19);
		frame.getContentPane().add(LB_title);
		
		JLabel LB_param1 = new JLabel("Author");
		LB_param1.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_param1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LB_param1.setBounds(10, 548, 84, 19);
		frame.getContentPane().add(LB_param1);
		
		JLabel LB_param2 = new JLabel("Location");
		LB_param2.setHorizontalAlignment(SwingConstants.TRAILING);
		LB_param2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LB_param2.setBounds(20, 589, 84, 19);
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
		CB_type.setBounds(106, 414, 142, 30);
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
		btnAdd.setBounds(277, 502, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblHome = new JLabel("Add Item");
		lblHome.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblHome.setBounds(10, 321, 461, 60);
		frame.getContentPane().add(lblHome);

		
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