package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import utils.CR;
import utils.Utilities;

import javax.swing.JList;

public class FacultyWindow {

	private JFrame frame;
	private JTextField txtReserve;
	private static String email = null;
	private static String studentID = null;
	private static int numBooks = 0;

	/**
	 * Launch the application.
	 */
	public static void FacultyS(String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyWindow window = new FacultyWindow(email);
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
	public FacultyWindow(String email) {
		this.email = email;
		this.studentID = setID(email);
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
		
		//reserve books
		
		JLabel lblNope = new JLabel("Failed");
		lblNope.setBounds(590, 715, 87, 16);
		frame.getContentPane().add(lblNope);
		lblNope.setVisible(false);
		
		JLabel lblYup = new JLabel("Completed");
		lblYup.setBounds(590, 715, 97, 16);
		frame.getContentPane().add(lblYup);
		lblYup.setVisible(false);
		
		JLabel lblReserveBooks = new JLabel("Reserve Book:");
		lblReserveBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReserveBooks.setBounds(424, 646, 181, 25);
		frame.getContentPane().add(lblReserveBooks);
		
		txtReserve = new JTextField();
		txtReserve.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtReserve.setBounds(424, 702, 154, 35);
		frame.getContentPane().add(txtReserve);
		txtReserve.setColumns(10);
		
		JLabel lblBookId = new JLabel("Book ID #:");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBookId.setBounds(424, 670, 154, 35);
		frame.getContentPane().add(lblBookId);
		
		JButton btnReserve = new JButton("Reserve");
		btnReserve.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(CR.Reserve("UserDatabase.txt", "ItemDatabase.txt", email, txtReserve.getText()) < 1 ) {
						lblNope.setVisible(true);
					};
					lblYup.setVisible(true);
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnReserve.setBounds(424, 750, 154, 41);
		btnReserve.setForeground(Style.dBlue);
		btnReserve.setBackground(Style.lBlue);
		frame.getContentPane().add(btnReserve);
		////////reserve
		
		DefaultListModel	model	= new DefaultListModel();
		JList				list	= new JList(model);
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.setBounds(424, 357, 1054, 153);
		frame.getContentPane().add(list);
		
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
		btnRenew.setBounds(424, 523, 105, 35);
		btnRenew.setForeground(Style.dBlue);
		btnRenew.setBackground(Style.lBlue);
		frame.getContentPane().add(btnRenew);
		
		String		string	= listBooks();
		String[]	parts	= string.split("_");
		for (int i = 0; i < parts.length; i++) {
			model.addElement(parts[i]);
		}
		
		JLabel lblCurrentBooks = new JLabel("Current books:");
		lblCurrentBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCurrentBooks.setBounds(424, 303, 220, 41);
		frame.getContentPane().add(lblCurrentBooks);
		
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
		JLabel lblTitle = new JLabel("Faculty");
		lblTitle.setForeground(new Color(3, 51, 89));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 44));
		lblTitle.setBounds(25, 179, 223, 73);
		frame.getContentPane().add(lblTitle);
		//
		
		//search book location button
		JButton btnSearchBookLoc = new JButton("Search Book Location");
		btnSearchBookLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSearch.BookS();
				frame.dispose();
			}
		});
		btnSearchBookLoc.setForeground(new Color(3, 51, 89));
		btnSearchBookLoc.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnSearchBookLoc.setBackground(new Color(230, 230, 240));
		btnSearchBookLoc.setBounds(506, 205, 223, 38);
		frame.getContentPane().add(btnSearchBookLoc);
		//
		
		//selected
		//my books button
		JButton btnMyBooks = new JButton("My Books");
		btnMyBooks.setForeground(new Color(3, 51, 89));
		btnMyBooks.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnMyBooks.setForeground(Style.lBlue);
		btnMyBooks.setBackground(Style.dBlue);
		btnMyBooks.setBounds(248, 204, 223, 38);
		frame.getContentPane().add(btnMyBooks);
		//
		
		//restrict books
		JButton btnRestrictBooks = new JButton("Restrict Books");
		btnRestrictBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestrictBooks.RestrictS("Faculty");
				frame.dispose();
			}
		});
		btnRestrictBooks.setForeground(new Color(3, 51, 89));
		btnRestrictBooks.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnRestrictBooks.setBackground(new Color(230, 230, 240));
		btnRestrictBooks.setBounds(768, 205, 223, 38);
		frame.getContentPane().add(btnRestrictBooks);
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
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Back-end
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static String setID(String userEmail){
		String result = null;
		try (BufferedReader in = new BufferedReader(new FileReader("UserDatabase.txt"))) {
			String line;
			String[] fields = new String[10]; //userdatabase contains 10 fields

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
	// Checks what books the user has out
	//takes in the book ID and the due date it currently has
	public static void renewBook(String resourceString){
		String resourceID = resourceString.substring(resourceString.length() - 6);
		ArrayList<String> itemLines = Utilities.readTextFile("ItemDatabase.txt");
		String myDate = null;
		boolean renew = false;
		for (String line : itemLines) {
			String[] bits = line.split("\\*");
			// Find the item
			if (bits[0].equals(resourceID)) {
				// If there is no one in the reserve queue, we will renew the item
				if (bits[7].equals("NULL")) {
					renew = true;
					break;
				}else{
					System.out.println("Can't renew book");
					return;
				}
			}
		}
		//at this point the book provided (which should be only selected by the user so user
		// definitely has the book) has no reserve queue and as such can be renewed
		// read Write and update user Database
		ArrayList<String> myLines = Utilities.readTextFile("UserDatabase.txt");
		DateFormat sourceFormat = new SimpleDateFormat("ddMMyyyy");;
		Date date = null;
		Date currentDate;
		if(renew){
			for(String line : myLines){
				String[] bits = line.split("\\*");
				//found the user 
				if(bits[0].equals(studentID)){
					
					myDate = getDate(resourceID);
					try{
						date = sourceFormat.parse(myDate);
					}catch(Exception e){
						System.out.println(e);
					}
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					c.add(Calendar.DATE, 14);
				    currentDate = c.getTime();
					System.out.println(sourceFormat.format(currentDate));
					//finished incrementing the date by 2 weeks
					String[] temp = bits[6].split(",");
					bits[6] = "";
					for(int i = 0; i < temp.length; i++){
						if(temp[i].startsWith(resourceID)){
							temp[i] = resourceID + sourceFormat.format(currentDate);
						}
						//replace bits[6] with our new value
						bits[6] += temp[i];
						if(i != temp.length - 1){
                            bits[6] += ",";
                        }
					}
					String newLine = String.join("*", bits);
					int index = myLines.indexOf(line);
					myLines.set(index, newLine);
					break;
				}
				
			}
			Utilities.writeTextFile("UserDatabase.txt", myLines);
		}
	}
	
	public static String getBooks(){
		String result = "NULL";
		try (BufferedReader in = new BufferedReader(new FileReader("UserDatabase.txt"))) {
			String line;
			String[] fields = new String[10]; //userdatabase contains 10 fields

			while ((line = in.readLine()) != null) {
				fields = line.split("\\*");
				// Looks for the ID of the user we need to check
				if (fields[4].equals(email)) {
					result = fields[6];
				} else {
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	//will call getBooks() which returns all the books we have, and then list them in
	// the window we have
	public static String listBooks() {
		String result = getBooks();
		String bookName = null;
		String delimiter = ",";
		String[] tempArr;
		String bookID;
		String day;
		String month;
		String year;
        String returnString = "";
		if(!result.equals("NULL")){
			//user actually has books out, and the field is not null
			tempArr = result.split(delimiter);
			numBooks = tempArr.length;
			for(int i = 0; i < tempArr.length; i++){
				bookID 	= tempArr[i].substring(0,6);
				day 	= tempArr[i].substring(6,8);
				month 	= tempArr[i].substring(8,10);
				year 	= tempArr[i].substring(10);
				//from the 14 digit value, first 6 = item ID, then ddmmyyyy

				bookName = getBookName(bookID);


				returnString += String.format("Book: %s Due date: %s/%s/%s   ID:%s_", bookName, day, month, year, bookID);
			}
		}else{
			returnString = "No books currently out";
		}
		return returnString;
	}

	/*
	 * given the book the user has, including the dates, it will take out the date from
	 * the item we want
	 * 
	 * @param borrowedItem - all the items the user has out
	 */
	private static String getDate(String borrowedItem){
		String result = getBooks();
		String day;
		String month;
		String year;
		String bookField;
        String date = null;
        if(!result.equals("NULL")){
			String[] fields = result.split(",");
			// Looks for the ID of the user we need to check
			for(int i = 0; i<fields.length;i++){
				if (fields[i].startsWith(borrowedItem)) {
					bookField = fields[i];
					day 	= bookField.substring(6,8);
					month 	= bookField.substring(8,10);
					year 	= bookField.substring(10);
					//from the 14 digit value, first 6 = item ID, then ddmmyyyy
					date = day  + month + year;
					//bookName = getBookName(bookID);
				}
			}
        }
        return date;
	}

	/*
	 * Given the book ID, it will go through the item database and retrieve the book name
	 * If for some reason there is no book name attached (which won't happen), will return null
	 * 
	 * @param bookID - ID of the book
	 */
	private static String getBookName(String bookID){
		//man this is fugly
		//init a new bufferedreader to read from itemdatabase, and
		//using the bookID found above, search the itemdatabase for
		//matching book title to the id
		String bookName = null;
		try (BufferedReader itemIn = new BufferedReader(new FileReader("ItemDatabase.txt"))){
			String itemLine;
			String[] itemFields = new String[8]; //itemdatabase contains 8 fields

			while((itemLine = itemIn.readLine()) != null) {
				itemFields = itemLine.split("\\*");
				//look for the book ID we need to check
				if(itemFields[0].equals(bookID)){
					bookName = itemFields[2];
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bookName;
	}		
}
		