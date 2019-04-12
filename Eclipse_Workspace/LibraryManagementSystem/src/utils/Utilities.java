package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Utilities {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public static final SimpleDateFormat	DATE_FORMAT	= new SimpleDateFormat("ddMMyyyy");
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
	public static void waitMilliseconds(long stopTime) {
		
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
	public static void ensureDirsForFileExist(String fileName) {
		
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
	
}
