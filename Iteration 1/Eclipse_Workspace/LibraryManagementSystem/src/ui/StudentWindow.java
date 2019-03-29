package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	public void StudentS() {
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
		
		String string = getBooks(email);
		String[] parts = string.split("_");
		for (int i = 0; i < parts.length; i++) {
			model.addElement(parts[i]);
		}
	}
	
	// Checks what books the user has out
		public static String getBooks(String userEmail) {
			String result = null;
			String bookName = null;
			String delimiter = ",";
			String[] tempArr;
			String bookID;
			String day;
			String month;
			String year;
	        String returnString = "";
			try (BufferedReader in = new BufferedReader(new FileReader("UserDatabase.txt"))) {
				String line;
				String[] fields = new String[10]; //userdatabase contains 10 fields

				while ((line = in.readLine()) != null) {
					fields = line.split("\\*");
					// Looks for the ID of the user we need to check
					if (fields[4].equals(userEmail)) {
						result = fields[6];
						if(!result.equals("NULL")){
							//user actually has books out, and the field is not null
							tempArr = result.split(delimiter);
							for(int i = 0; i < tempArr.length; i++){
								bookID 	= tempArr[i].substring(0,6);
								day 	= tempArr[i].substring(6,8);
								month 	= tempArr[i].substring(8,10);
								year 	= tempArr[i].substring(10);
								//from the 14 digit value, first 6 = item ID, then ddmmyyyy

								bookName = getBookName(bookID);


								returnString += String.format("Book: %s Due date: %s/%s/%s_", bookName, day, month, year);
							}
						}else{
							returnString = "No books currently out";
						}
						break;
					} else {
						continue;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return returnString;
		}


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
