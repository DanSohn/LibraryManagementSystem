package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTextField;

public class StudentWindow {

	private JFrame frame;
	private JTextField textField;
	private static String email = null;

	/**
	 * Launch the application.
	 */
	public static void StudentS() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentWindow window = new StudentWindow(email);
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
	public StudentWindow(String email) {
		this.email = email;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 568, 300);
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
		btnLogOut.setBounds(323, 215, 97, 25);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblLibraryStaff = new JLabel("Student");
		lblLibraryStaff.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblLibraryStaff.setBounds(26, 0, 223, 73);
		frame.getContentPane().add(lblLibraryStaff);
		
	    DefaultListModel model = new DefaultListModel();
	    JList list = new JList(model);
		list.setBounds(26, 99, 497, 102);
		frame.getContentPane().add(list);
		
		textField = new JTextField();
		textField.setBounds(26, 66, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnRent = new JButton("Rent");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addElement(textField.getText());
			}
		});
		btnRent.setBounds(152, 65, 97, 25);
		frame.getContentPane().add(btnRent);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.remove(0);
			}
		});
		btnReturn.setBounds(261, 65, 97, 25);
		frame.getContentPane().add(btnReturn);
		
		String string = UserMenu.getBooks(email);
		String[] parts = string.split("_");
		for (int i = 0; i < parts.length; i++) {
			model.addElement(parts[i]);
		}
	}

	public void Student() {
		// TODO Auto-generated method stub
		
	}
}
