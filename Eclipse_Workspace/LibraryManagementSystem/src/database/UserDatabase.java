package database;

import enums.ResourceField;
import enums.ResourceType;
import enums.UserField;
import enums.UserType;
import utils.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase {
	
	/*
	 *
	 * METHODS
	 *
	 */
	
	/**
	 * Generates a new user database string.
	 *
	 * @param id        The user's id.
	 * @param type      The user's type.
	 * @param firstName The user's first name.
	 * @param lastName  The user's last name.
	 * @param email     The user's email.
	 * @param password  The user's password.
	 * @return A string representing the user in the user database.
	 */
	private static String genNewUserDBStr(String id, UserType type, String firstName, String lastName, String email,
			String password) {
		
		return String.join("*", id, type.name(), firstName, lastName, email, password, "NULL", "NULL", "0", "false");
		
	}
	
	/**
	 * Adds a new user to the user database.
	 *
	 * @param id        The user's id.
	 * @param type      The user's type.
	 * @param firstName The user's first name.
	 * @param lastName  The user's last name.
	 * @param email     The user's email.
	 * @param password  The user's password.
	 * @return A boolean stating whether or not the operation completed
	 *         successfully.
	 */
	public static boolean addNewUser(String id, UserType type, String firstName, String lastName, String email,
			String password) {
		
		ArrayList<String> fileLines = Utilities.readTextFile("UserDatabase.txt");
		
		for (String line : fileLines) {
			String[]	bits			= line.split("\\*");
			String		existingID		= bits[type.indexOfField(UserField.ID)];
			String		existingEmail	= bits[type.indexOfField(UserField.EMAIL)];
			
			if (id.equals(existingID) || email.equals(existingEmail)) {
				return false;
			}
		}
		
		fileLines.add(genNewUserDBStr(id, type, firstName, lastName, email, password));
		Utilities.writeTextFile("UserDatabase.txt", fileLines);
		
		return true;
		
	}
	
	/**
	 * 
	 * @param searchTerm
	 * @return
	 */
	public static boolean checkRegdUser(String searchTerm) {
		
		System.out.println(searchTerm);
		
		Scanner scn = new Scanner(System.in);
		
		// Specifies where the file you want to read from can be found
		String filepath = "UserDatabase.txt";
		
		// Initializes found status
		boolean found = false;
		
		// Initializes data fields for reading and storing database entries
		String	id			= "";
		String	usertype	= "";
		String	fname		= "";
		String	lname		= "";
		String	email		= "";
		// Password is not stores for security
		String	borrowed	= "";
		String	reserved	= "";
		String	fine		= "";
		String	blacklisted	= "";
		
		try {
			
			// Finds the file and specifies the delimiter
			scn = new Scanner(new File(filepath));
			scn.useDelimiter("[*\n]");
			
			// Executes loop while there is more data in the database and the searchterm
			// hasnt been found
			while (scn.hasNext() && !found) {
				
				// Stores each data field in their respective variables
				id			= scn.next();
				usertype	= scn.next();
				fname		= scn.next();
				lname		= scn.next();
				email		= scn.next();
				
				// Does not store password anywhere
				scn.next();
				
				// Continues storing data
				borrowed	= scn.next();
				reserved	= scn.next();
				fine		= scn.next();
				blacklisted	= scn.next();
				
				// Check if the searchterm has been found and is an ID
				if (id.equals(searchTerm) || email.equals(searchTerm)) {
					scn.close();
					return true;
				}
				
			}
			
		}
		
		catch (Exception e) {
			
		}
		
		scn.close();
		return false;
		
	}
	
	/**
	 * Gets the specified parameter of the the specified user from the user
	 * database.
	 * 
	 * @param userID     The ID of the item to look at.
	 * @param fieldToGet The field to get from the resource.
	 * @return The value of the field.
	 * @throws Exception If the resource is not in the database.
	 */
	public static String getParameterOfUser(String userID, UserField fieldToGet) {
		
		ArrayList<String> fileLines = Utilities.readTextFile("UserDatabase.txt");
		
		for (String line : fileLines) {
			String[]	bits	= line.split("\\*");
			String		ID		= bits[0];
			
			if (userID.equals(ID)) {
				return bits[UserType.STUDENT.indexOfField(fieldToGet)];
			}
		}
		
		return null;
		
	}
	
}
