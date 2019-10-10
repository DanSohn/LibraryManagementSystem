package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

import utils.CR;
import utils.Utilities;

import javax.swing.JList;
import javax.swing.JTextField;

public class StudentWindow extends Style {
	
	private JFrame			frame;
	private static String	email		= null;
	private static String	studentID	= null;
	private static int		numBooks	= 0;
	private JTextField txtReserve;
	
	/**
	 * Launch the application.
	 * @param name - name of student
	 * @param email - email of student
	 */
	 static void StudentS(String name, String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentWindow window = new StudentWindow(name, email);
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
	public StudentWindow(String user_name, String user_email) {
		email		= user_email;
		studentID	= getID(email);
		frame = new JFrame();
		initialize();
		buttons(user_name, email, frame, "MyBook");
		setup(user_name, frame);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		////labels
		// current books label
		JLabel lblCurrentBooks = new JLabel("Current books:");
		lblCurrentBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCurrentBooks.setBounds(420, 301, 220, 41);
		frame.getContentPane().add(lblCurrentBooks);
		
		//reserve book label
		JLabel lblReserveBooks = new JLabel("Reserve Book:");
		lblReserveBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReserveBooks.setBounds(420, 589, 181, 25);
		frame.getContentPane().add(lblReserveBooks);
		
		//book id label
		JLabel lblBookId = new JLabel("Book ID #:");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBookId.setBounds(424, 621, 154, 35);
		frame.getContentPane().add(lblBookId);
		
		//reserve label
		txtReserve = new JTextField();
		txtReserve.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtReserve.setBounds(542, 621, 154, 35);
		frame.getContentPane().add(txtReserve);
		txtReserve.setColumns(10);
		
		//reserve button
		JButton btnReserve = new JButton("Reserve");
		btnReserve.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Utilities.isValidInput(txtReserve.getText())){
					try {
						if(CR.Reserve("UserDatabase.txt", "ItemDatabase.txt", email, txtReserve.getText()) < 1 ) {
							MessageBox.MessageS("Cant do that");
						}
						else {
							MessageBox.MessageS("book reserved successfuly");
						}
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else {
					MessageBox.MessageS("Fields cannot be blank or contain \"*\"");
				}
				
			}
		});
		btnReserve.setBounds(708, 618, 154, 41);
		btnReserve.setForeground(dBlue);
		btnReserve.setBackground(lBlue);
		frame.getContentPane().add(btnReserve);
		
		//Default list setup
		DefaultListModel	model	= new DefaultListModel();
		JList				list	= new JList(model);
		list.setFont(new Font("Tahoma", Font.PLAIN, 22));
		list.setBounds(424, 340, 1054, 153);
		frame.getContentPane().add(list);
		
		//button renew, adds 2 weeks to selected book, re writes list
		JButton btnRenew = new JButton("Renew");
		btnRenew.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = (String) list.getSelectedValue();
				renewBook(selected);
				model.removeAllElements();
				String		string	= listBooks();
				String[]	parts	= string.split("_");
				for (int i = 0; i < parts.length; i++) {
					model.addElement(parts[i]);
				}
			}
		});
		btnRenew.setBounds(424, 506, 154, 41);
		btnRenew.setForeground(dBlue);
		btnRenew.setBackground(lBlue);
		frame.getContentPane().add(btnRenew);
		
		//writes current books
		String		string	= listBooks();
		String[]	parts	= string.split("_");
		for (int i = 0; i < parts.length; i++) {
			model.addElement(parts[i]);
		}
		

	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Back-end
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static String getID(String userEmail) {
		String result = null;
		try (BufferedReader in = new BufferedReader(new FileReader("UserDatabase.txt"))) {
			String   line;
			String[] fields	= new String[10];	// UserDatabase contains 10 fields

			while ((line = in.readLine()) != null) {
				fields = line.split("\\*");
				// Looks for the ID of the user we need to check
				if (fields[4].equals(userEmail)) {
					result = fields[0];
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getEmail(){
		return email;
	}
	// Checks what books the user has out
	// takes in the book ID and the due date it currently has
	 public static void renewBook(String resourceString) {
		String				resourceID	= resourceString.substring(resourceString.length() - 6);
		ArrayList<String>	itemLines	= Utilities.readTextFile("ItemDatabase.txt");
		boolean				renew		= false;
		for (String line : itemLines) {
			String[] bits = line.split("\\*");
			// Find the item
			if (bits[0].equals(resourceID)) {
				// If there is no one in the reserve queue, we will renew the item
				if (bits[7].equals("NULL")) {
					renew = true;
					break;
				} else {
					System.out.println("Can't renew book");
					return;
				}
			}
		}
		// at this point the book provided (which should be only selected by the user so
		// user
		// definitely has the book) has no reserve queue and as such can be renewed
		// read Write and update user Database
		ArrayList<String> myLines			= Utilities.readTextFile("UserDatabase.txt");
		DateFormat sourceFormat	= new SimpleDateFormat("ddMMyyyy");
		Date currentDate;
		if (renew) {
			for (String line : myLines) {
				String[] bits = line.split("\\*");
				// found the user
				if (bits[0].equals(studentID)) {
					//grabs the data portion of the string
					String myDate = getDate(resourceID);
					// call twoWeeksDate on myDate and return the date two weeks later, in a Date object
					currentDate = Utilities.twoWeekDate(myDate);

					System.out.println(sourceFormat.format(currentDate));
					// finished incrementing the date by 2 weeks
					String[] temp = bits[6].split(",");
					bits[6] = "";
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].startsWith(resourceID)) {
							temp[i] = resourceID + sourceFormat.format(currentDate);
						}
						// replace bits[6] with our new value
						bits[6] += temp[i];
						if (i != temp.length - 1) {
							bits[6] += ",";
						}
					}
					String	newLine	= String.join("*", bits);
					int		index	= myLines.indexOf(line);
					myLines.set(index, newLine);
					break;
				}
				
			}
			Utilities.writeTextFile("UserDatabase.txt", myLines);
		}
	}



	/**
	 * I create a copy of listbooks that i transfered to utilities in order to call it through the window
	 * while testing
	 * @return a string format that is either book, due date, ID OR no books currently out
	 */
	public static String listBooks(){
		return Utilities.listBooks(email);
	}
	
	/*
	 * given the book the user has, including the dates, it will take out the date
	 * from the item we want
	 * 
	 * @param borrowedItem - all the items the user has out
	 */
	public static String getDate(String borrowedItem){
		return Utilities.getDate(email, borrowedItem);
	}


}
