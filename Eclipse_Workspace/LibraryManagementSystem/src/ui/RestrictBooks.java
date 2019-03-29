package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import utils.Utilities;

import javax.swing.JTextField;

public class RestrictBooks {

	private JFrame frame;
	private static String email = null;
	private static String studentID = null;
	private static int numBooks = 0;
	private JTextField idIn;

	/**
	 * Launch the application.
	 */
	public static void RestrictS(String email) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestrictBooks window = new RestrictBooks(email);
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
	public RestrictBooks(String email) {
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
		
		JLabel lblId = new JLabel("ID#");
		lblId.setBounds(306, 386, 56, 16);
		frame.getContentPane().add(lblId);
		
		JLabel lblTxt = new JLabel("text here");
		lblTxt.setBounds(739, 468, 56, 16);
		frame.getContentPane().add(lblTxt);
		
		idIn = new JTextField();
		idIn.setBounds(374, 383, 116, 22);
		frame.getContentPane().add(idIn);
		idIn.setColumns(10);
		
		JButton btnRestrict = new JButton("restrict");
		btnRestrict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTxt.setText(restrictBook(idIn.getText()));
				//System.out.print(restrictBook(idIn.getText()));
			}
		});
		btnRestrict.setBounds(509, 382, 97, 25);
		frame.getContentPane().add(btnRestrict);
		
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
				new BookSearch(email,"Faculty").BookS();
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
		btnMyBooks.setForeground(new Color(3, 51, 89));
		btnMyBooks.setBackground(new Color(230, 230, 240));
		btnMyBooks.setBounds(248, 204, 223, 38);
		frame.getContentPane().add(btnMyBooks);
		//
		
		//restrict books
		JButton btnRestrictBooks = new JButton("Restrict Books");
		btnRestrictBooks.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnRestrictBooks.setBounds(768, 205, 223, 38);
		btnRestrictBooks.setForeground(Style.lBlue);
		btnRestrictBooks.setBackground(Style.dBlue);
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
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String restrictBook(String bookID){
		String bookName = null;
        String authorName = null;
		ArrayList<String>	itemLines	= Utilities.readTextFile("ItemDatabase.txt");
		String sentMails = null;
		String returnString = null;
		System.out.println("Inside restrictBook with ID " + bookID);
		for (String line : itemLines) {
			String[] bits = line.split("\\*");
			// Find the book
			//System.out.println("searching line with bookid: " + bits[0]);
			if (bits[0].equals(bookID)) {
				//System.out.println("Book found!");
                bookName = bits[2];
                authorName = bits[3];
				// If book is AVAILABLE
				if (bits[5].equals("AVAILABLE")) {
					System.out.println("book is avail, now restricting");
					bits[5] = "RESTRICTED";
					returnString = bookName + " (" + bookID + ") is now restricted.";
				} else if(bits[5].equals("RESTRICTED")){
                    System.out.println("Already restricted");
                    returnString = bookName + " (" + bookID + ") is already restricted.";
                    //should return the string already restricted
                }else{
					System.out.println("Book is currently out");
                    //if unavailable and current holder exists
					//change due date to 1 week from today in UserDatabase
                    if(!bits[6].equals("NULL")){
                        String userID = bits[6];
                        String newDate = Utilities.getWeekDate();
                        //ddMMyyyy
                        changeDate(userID, newDate, bookID);
                        //will update the userdatabase by changing the book the user has, by updating the return date
                    }
                    //go to UserDatabase
                    //and change everyone in reserve queue, their data such that
                    //you erase the item in the list of reserved items
                    if(!bits[7].equals("NULL")){
                        //reserve queue is not null
                        String[] currentReserveQueue = bits[7].split(",");
                        for(int i = 0 ; i<currentReserveQueue.length; i++){
                            deleteReserveBook(currentReserveQueue[i], bookID);
                        }
                        //send email to everyone in reserve queue (ItemDatabase)
                        sentMails = sendEmail(bits[7], bookName, authorName, bookID);
                        //clear reserve queue (ItemDatabase)
                        bits[7] = "NULL";
                    }
                    returnString = bookName + " (" + bookID + ") is now restricted.";
                    bits[5] = "RESTRICTED";
				}
				String	newLine	= String.join("*", bits);
				System.out.println("line we replaced is now: \n" + newLine);
				int		index	= itemLines.indexOf(line);
				itemLines.set(index, newLine);
				break;
			}
			

		}
		Utilities.writeTextFile("ItemDatabase.txt", itemLines);
		return returnString;
	}
    
    /**
     *
     * Given the userID and bookID, will search for users and then delete
     * the reserve book from their list of reserved books
     *
     * @param userID  ID of the user
     * @param bookID  ID of the book
     */
    private void deleteReserveBook(String userID, String bookID){
        ArrayList<String> itemLines = Utilities.readTextFile("UserDatabase.txt");
        String bookCode = null;
        for(String line: itemLines){
            String[] bits = line.split("\\*");
            if(bits[0].equals(userID)){
                String[] reserveQueue = bits[7].split(",");
                bits[7] = ""; //reset the queue to nothing
                int removed = -1;
                for(int i = 0; i < reserveQueue.length; i++){
                	if(reserveQueue[i].length() > 6){
                		bookCode = reserveQueue[i].substring(0, 6);
                	}else{
                		bookCode = reserveQueue[i];
                	}
                	//System.out.println(reserveQueue[i].substring(0, 6));
                    if(bookCode.equals(bookID)){
                        //book has been found in the reserve queue
                    	if(reserveQueue.length == 1){
                    		reserveQueue[i] = "NULL";
                    	}else{
                    		reserveQueue[i] = "";
                    	}
                        removed = i;
                    }
                    bits[7] += reserveQueue[i];
                    if(i != reserveQueue.length-1 && i != removed){
                        bits[7] += ",";
                    }
                }
                String newLine = String.join("*", bits);
                int index = itemLines.indexOf(line);
                itemLines.set(index, newLine);
                break;
            }
        }
        Utilities.writeTextFile("UserDatabase.txt", itemLines); 
    }
    
    /**
     *
     * Changes the current due date of the book in the user database
     * given that the current due date is over a week from now
     *
     * @param userID  ID of the user
     * @param newDate the date one week from now in ddMMyyyy
     * @param bookID  ID of the book
     */
    private void changeDate(String userID, String newDate, String bookID){
        String currentDate = null;
        String bookList = null;
        ArrayList<String> itemLines = Utilities.readTextFile("UserDatabase.txt");
        //searching each line in the userdatabase
        for(String line: itemLines){
            String[] bits = line.split("\\*");
            //if specific line contains our user
            if(bits[0].equals(userID)){
                String[] books = bits[6].split(",");
                bits[6] = "";
                for(int i = 0; i < books.length; i++){
                    //we found our book
                    //if(bookID.equals(books[i].substring(0,6))){
                    if(books[i].startsWith(bookID)){
                    	System.out.println("book found");
                        //must compare dates here to make sure that current due date
                        //is over a week, else do nothing
                        currentDate = books[i].substring(6);
                        System.out.println("Current date: " + currentDate);
                        if(Utilities.compareDates(newDate, currentDate) == 0){
                            //our newDaate due date is before or equal to the current date
                            //
                            //edge case in which the two dates are equal, since method does BEFORE OR EQUAL
                        	//System.out.println(");
                            if(currentDate.equals(newDate)){
                            	System.out.println("The two dates are equal, not doing anything");
                                break;
                            }else{
                                books[i] = bookID + newDate;
                                System.out.println("new book line: " + books[i]);
                            }
                        }
                    }
                    bits[6] += books[i];
                    if(i != books.length-1){
                        bits[6] += ",";
                    }
                }
                String newLine = String.join("*", bits);
                int index = itemLines.indexOf(line);
                itemLines.set(index, newLine);
                break;
                
            }
        }
        Utilities.writeTextFile("UserDatabase.txt", itemLines); 
    }
   
    
    /**
     *
     * Given the list of people in reserve queue, grab emails
     * from all of them and send email to them all, or in this case
     * print out "Sending email to FIRSTNAME LASTNAME at EMAIL that
     * BOOK ( BOOKID ) by AUTHOR is now restricted
     * 
     * @param list  the list of people in the reserve queue in ItemDatabase
     * @param bookName  Name of the book
     * @param authorName  Name of the author
     * @param bookID  ID of the book
     *
     * @return the full string that you can just BAM put in textbox
     */
    private String sendEmail(String list, String bookName, String authorName, String bookID){
        String firstName = null;
        String lastName = null;
        String email = null;
        String returnString = null;
        //create arraylist from userdatabase and use it to call methods to find names, email
        ArrayList<String>	itemLines	= Utilities.readTextFile("UserDatabase.txt");
        //split the list into different parts
        String bits[] = list.split(",");
        System.out.println("Sending email to following users that " + bookName + " ( " + bookID + " ) by " + authorName + " is now restricted");
        returnString = "Sending email to following users that " + bookName + " ( " + bookID + " ) by " + authorName + " is now restricted\n";
        //inefficient algorithm to search the database every time for each userID
        for(int i = 0; i < bits.length; i++){
            //search userdatabase for userIDs
            for (String line : itemLines) {
                String[] userID = line.split("\\*");
                if(userID[0].equals(bits[i])){
                    //we found user
                    firstName = userID[2];
                    lastName = userID[3];
                    email = userID[4];
                    System.out.println(firstName + " " + lastName + " at " + email);
                    returnString += firstName + " " + lastName + " at " + email + "\n";
                    break;
                    //broke out for searching for ONE user, now will search for next user
                }
            }
        }
        
        return returnString;
    }
}