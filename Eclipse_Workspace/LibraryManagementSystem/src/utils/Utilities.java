package utils;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Utilities {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	private static final SimpleDateFormat	DATE_FORMAT	= new SimpleDateFormat("ddMMyyyy");
	public static final String				PARENT_DIR	= new File(new File("").getAbsolutePath()).getParent();
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Timer method used as an alternative to Thread.sleep() Gives a more accurately
	 * timed delay across systems.
	 * 
	 * @param stopTime Stop after this long (Milliseconds)
	 */
	private static void waitMilliseconds(long stopTime) {
		
		// The time at the beginning of the method
		long startTime = System.currentTimeMillis();
		
		// Keep checking to see if the difference in time has passed the required length
		while (true) {
			
			long currentTime = System.currentTimeMillis();
			
			// If the time has passed, break out of the loop
			if (currentTime - startTime >= stopTime)
				break;
		}
		
	}
	
	/**
	 * Clear the console screen of text.
	 */
	public static void clearConsole() {
		
		// Try to clear the console via unique OS-Specific methods
		try {
			
			// Get the operating system name
			final String os = System.getProperty("os.name");
			
			// If using Windows: clear using cmd command
			if (os.contains("Windows")) {
				
				// Run the cmd command and wait for it to finish
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				
			} else { // If other: use terminal clearing command (bash)
				
				// Print this interesting series of character and flush the console
				System.out.println("\033[H\033[2J");
				System.out.flush();
				
			}
			
		} catch (Exception e) { // If clearing doesn't work: tell the user and stop the program
			// If console doesn't clear print this error
			System.out.println("Console failed to clear.");
			waitMilliseconds(3000);
			System.exit(1);
		}
		
	}
	
	/**
	 * Multiply a string by a specified amount.<br>
	 * 
	 * e.g.: "f"*3 = "fff"
	 * 
	 * @param string   The string to multiply.
	 * @param multiple The number of times to multiply the string.
	 * @return newString The multiplied string.
	 */
	public static String multiplyString(String string, int multiple) {
		
		// Create a string builder
		StringBuilder stringBuilder = new StringBuilder();
		
		// Append the inputed string to the string builder the desired number of times
		for (int i = 0; i < multiple; i++) {
			stringBuilder.append(string);
		}
		
		// Process and return the newly multiplied string
		String newString = stringBuilder.toString();
		return (newString);
		
	}
	
	/**
	 * Given a path to a file, this method ensures that the directories leading to
	 * the file exist, and creates them if they do not exist.
	 * 
	 * @param fileName The path to the file you intend to write to.
	 */
	private static void ensureDirsForFileExist(String fileName) {
		
		// Containers
		String		filePath	= "";
		String[]	pathBits	= fileName.split("/");
		
		// Build the path up to and including the last directory
		for (int i = 0; i < pathBits.length - 1; i++) {
			filePath = filePath + pathBits[i] + "/";
		}
		
		// Make the directories if they don't exist
		new File(filePath).mkdirs();
		
	}
	
	/**
	 * Reads lines from a text file into a String array.
	 * 
	 * @param fileName The file to read from.
	 * @return A String array of all the lines in the file.
	 */
	public static ArrayList<String> readTextFile(String fileName) {
		
		// Locate the file to read
		File fileToRead = new File(fileName);
		
		// Have an array list of lines waiting to be filled with the file's contents
		ArrayList<String> lines = new ArrayList<String>();
		
		// Try to open and read the file line-by-line with a scanner
		try (Scanner _scanner = new Scanner(fileToRead);) {
			
			// Read all non-null lines from the file into the array list
			while (_scanner.hasNext()) {
				lines.add(_scanner.nextLine());
			}
			
			// Return all of the lines in an array
			return lines;
			
		} catch (FileNotFoundException e) { // If the file isn't found
			
			System.out.println("File [" + fileName + "] not found.");
			e.printStackTrace();
			
		}
		
		// If nothing was read, return null
		return null;
		
	}
	
	/**
	 * Writes lines from a String array to a specified file.
	 * 
	 * @param fileName The file to write to.
	 * @param lines    The lines to write to the file.
	 */
	@SuppressWarnings("resource")
	public static void writeTextFile(String fileName, ArrayList<String> lines) {
		
		ensureDirsForFileExist(fileName);
		
		// Try to write the file
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			
			// Write each line
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
			
		} catch (IOException ioe) {
			
			System.out.println("Cannot write to file [" + fileName + "], IO Exception.");
			ioe.printStackTrace();
			
			// For debugging
			new java.util.Scanner(System.in).nextLine();
			
		}
		
	}
	
	/**
	 * Returns the current date
	 * @return String in the form ddMMyyyy
	 */
	public static String getCurrentDate() {
		String date = DATE_FORMAT.format(new Date());
		return date;
	}
	
	/**
	 * Returns the date 2 weeks from today
	 * @return String in the form ddMMyyyy
	 */
	public static String get2WeeksDate() {
		Date date;
		try {
			// Converts today's date to milliseconds since Jan. 1st 1970
			date = DATE_FORMAT.parse(getCurrentDate());
			// Add 14 days (in milliseconds) to that value
			long newDate = date.getTime() + TimeUnit.DAYS.toMillis(14);
			// Convert back from milliseconds to a date
			date.setTime(newDate);
			
			return DATE_FORMAT.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Compares 2 dates to determine length of time passed
	 * 
	 * @param date1_s date to compare
	 * @param date2_s date to be compared to
	 * @return 0 if date1 is before or the same day as date2
	 * > 0 if date1 is later than date2. The magnitude of the return value
	 * represents how long date1 is after date2. A return value of 1 means that
	 * date1 is within 1 month past date2
	 */
	public static int compareDates(String date1_s, String date2_s) {
		try {
			Date date1 = DATE_FORMAT.parse(date1_s);
			Date date2 = DATE_FORMAT.parse(date2_s);
			
			// Returns 0 if equal, < 0 if date1 is earlier and > 0 if date 1 is later
			if (date1.compareTo(date2) <= 0) {
				return 0;
			}
			
			// Convert the dates into milliseconds and subtract them
			long difference = date1.getTime() - date2.getTime();
			// Divide by the number of milliseconds in a day to determine how many days have passed
			long days = difference/TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
			
			// 1 month is treated as being 30 days
			// NB: return value of 1 means that date1 is within 1 month past date2
			// In other words only 1 fine of $5 needs to be applied
			return (int) (days/30) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Returns the date 2 weeks from today
	 * @return String in the form ddMMyyyy
	 */
	public static String getWeekDate() {
		Date date;
		try {
			// Converts today's date to milliseconds since Jan. 1st 1970
			date = DATE_FORMAT.parse(getCurrentDate());
			// Add 14 days (in milliseconds) to that value
			long newDate = date.getTime() + TimeUnit.DAYS.toMillis(7);
			// Convert back from milliseconds to a date
			date.setTime(newDate);

			return DATE_FORMAT.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns whether or not the input string given is valid for the program.
	 * @param input The input to check
	 * @return If the input is valid
	 */
	public static boolean isValidInput(String input) {
		
		return !(input.equals("") || input.contains("*"));
		
	}


	/*
	 * Given the book ID, it will go through the item database and retrieve the book
	 * name If for some reason there is no book name attached (which won't happen),
	 * will return null
	 *
	 * @param bookID - ID of the book
	 */
	public static String getBookName(String bookID) {
		// init a new bufferedreader to read from itemdatabase, and
		// using the bookID found above, search the itemdatabase for
		// matching book title to the id
		String bookName = null;
		try (BufferedReader itemIn = new BufferedReader(new FileReader("ItemDatabase.txt"))) {
			String itemLine;
			String[] itemFields = new String[8];    // itemdatabase contains 8 fields

			while ((itemLine = itemIn.readLine()) != null) {
				itemFields = itemLine.split("\\*");
				// look for the book ID we need to check
				if (itemFields[0].equals(bookID)) {
					bookName = itemFields[2];
					return bookName;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			return bookName;
	}

	private static String getBooks(String user_email) {
		String result = "NULL";
		try (BufferedReader in = new BufferedReader(new FileReader("UserDatabase.txt"))) {
			String		line;
			String[]	fields	= new String[10];	// userdatabase contains 10 fields

			while ((line = in.readLine()) != null) {
				fields = line.split("\\*");
				// Looks for the ID of the user we need to check
				if (fields[4].equals(user_email)) {
					result = fields[6];
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
	public static String listBooks(String user_email) {
		String result = getBooks(user_email);
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

	public static String getDate(String user_email, String borrowedItem) {
		String	result	= Utilities.getBooks(user_email);
		String	day;
		String	month;
		String	year;
		String	bookField;
		String	date	= null;
		if (!result.equals("NULL")) {
			String[] fields = result.split(",");
			// Looks for the ID of the user we need to check
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].startsWith(borrowedItem)) {
					bookField	= fields[i];
					// from the 14 digit value, first 6 = item ID, then ddmmyyyy
					date = bookField.substring(6);
					// bookName = getBookName(bookID);
				}
			}
		}
		return date;
	}

	/**
	 * Move a portion of renewBook into this method that will calculate the two weeks and return the new date
	 * @param iniDate - the initial return date
	 * @return returnDate - two weeks later
	 */
	public static Date twoWeekDate(String iniDate){
		DateFormat sourceFormat	= new SimpleDateFormat("ddMMyyyy");
		Date date	= null;
		Date currentDate;

		// attempts to convert the date string into a date object of the form ddMMyyyy
		try {
			date = sourceFormat.parse(iniDate);
		} catch (Exception e) {
			System.out.println(e);
		}
		//uses a calander object to set the date and then add two weeks, ending up with the currentdate = iniDate + 2 weeks
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 14);
		currentDate = c.getTime();

		return currentDate;
	}
}
