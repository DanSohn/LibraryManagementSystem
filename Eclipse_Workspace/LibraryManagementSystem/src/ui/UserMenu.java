package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserMenu {

	public static final int NUM_CHOICES = 6;
	boolean exit;
	//private MenuInput menuInput = new MenuInput(NUM_CHOICES);
	private String userType;
	private String userEmail;
	// Since the only difference between Student and Faculty is the number of items
	// they can have checked out at once, I though maybe we can just use a variable to
	// differentiate between them later on in the program. Feel free to improve the implementation!
	// 2 possible values: "student" and "faculty"
	public UserMenu(String userType, String userEmail) {
		this.userType = userType;
		this.userEmail = userEmail;
	}

	private void printHeader() {

		System.out.println("*****************************************");
		System.out.println("**                User                 **");
		System.out.println("*****************************************");

	}

	private void printMenu() {

		System.out.println("1. Check Out Item");
		System.out.println("2. Check Item Location");
		System.out.println("3. Reserve Item");
		System.out.println("4. Renew Item");
		System.out.println("5. Browse Scholarly Papers, Journals and Articles");
		System.out.println("6. View Checked Out Items");
		System.out.println("7. Logout");

	}

	/*public void runMenu() {
		printHeader();
		while (!exit) {
			printMenu();
			int choice = menuInput.getInput();
			processInput(choice);
		}
	}*/

	private void processInput(int choice) {

		switch(choice) {
		case 1:
			System.out.println("User enters ID of the item they want to check out, we update database");
			break;
		case 2:
			System.out.println("User enters ID/Name of an item they want and we print it's location in the library");
			break;
		case 3:
			System.out.println("User enters ID/Name of the item they want to reserve, update database appropriately");
			break;
		case 4:
			System.out.println("User picks an item from their borrowed list and renews it, update due date");
			break;
		case 5:
			System.out.println("User can browse a list of links to articles, papers etc.");
			break;
		case 6:
			System.out.println("User can view a list of items he has out, including due date.");
			//getBooks();
			break;
		case 7:
			System.out.println("Logging Out\n");
			exit = true;
			break;
		default:
			System.out.println("An unknown error occured");
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
    /*
    public static void main(String[] args){
        System.out.println(getBooks("john.smith@staff.ca"));
    }
    */

}
