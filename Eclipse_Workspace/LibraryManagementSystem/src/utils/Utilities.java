package utils;

import java.text.SimpleDateFormat;

public class Utilities {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");
	
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
	
}
