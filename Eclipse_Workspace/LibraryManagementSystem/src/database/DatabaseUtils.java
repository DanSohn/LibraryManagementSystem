package database;

import java.util.ArrayList;

import enums.ResourceField;
import enums.ResourceStatus;
import enums.ResourceType;
import enums.UserType;
import utils.Utilities;

public class DatabaseUtils {
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/*
	 * Generating new stings
	 */
	
	/**
	 * Generates a new staff database string.
	 * 
	 * @param email    The staff member's email.
	 * @param password The staff member's password.
	 * @param type     The staff member's type.
	 * @return A string representing the staff member in the staff database.
	 */
	public static String genNewStaffDBStr(String email, String password, UserType type) {
		
		return String.join("*", email, password, type.name());
		
	}
	
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
	public static String genNewUserDBStr(String id, UserType type, String firstName, String lastName, String email,
			String password) {
		
		return String.join("*", id, type.name(), firstName, lastName, email, password, "NULL", "NULL", "0", "false");
		
	}
	
	/**
	 * Generates a new resource database string.
	 * 
	 * @param id          The resource's id.
	 * @param title       The resource's title.
	 * @param type        The resource's type.
	 * @param extraFields The extra fields for the specific resource.
	 * @return A string representing the resource in the item database.
	 */
	public static String genNewResourceDBStr(String id, ResourceType type, String title, String... extraFields) {
		
		return String.join("*", id, type.name(), title) + "*" + String.join("*", extraFields)
				+ (type.IS_PHYSICAL ? "*" + String.join("*", ResourceStatus.AVAILABLE.name(), "NULL", "NULL") : "");
		
	}
	
	/*
	 * Modifying existing strings
	 */
	
	/**
	 * Adds a new user to the user database.
	 * 
	 * @param id        The user's id.
	 * @param type      The user's type.
	 * @param firstName The user's first name.
	 * @param lastName  The user's last name.
	 * @param email     The user's email.
	 * @param password  The user's password.
	 */
	public static void addNewUser(String id, UserType type, String firstName, String lastName, String email,
			String password) {
		
		ArrayList<String> fileLines = Utilities.readTextFile("UserDatabase.txt");
		
		for (String line : fileLines) {
			String[]	bits			= line.split("\\*");
			String		existingID		= bits[0];
			String		existingEmail	= bits[4];
			
			if (id.equals(existingID) || email.equals(existingEmail)) {
				return;
			}
		}
		
		fileLines.add(genNewUserDBStr(id, type, firstName, lastName, email, password));
		Utilities.writeTextFile("UserDatabase.txt", fileLines);
		
	}
	
	/**
	 * Adds a new resource to the item database.
	 * 
	 * @param id          The resource's id.
	 * @param title       The resource's title.
	 * @param type        The resource's type.
	 * @param extraFields The extra fields for the specific resource.
	 */
	public static void addNewResource(String id, String title, ResourceType type, String... extraFields) {
		
		ArrayList<String> fileLines = Utilities.readTextFile("ItemDatabase.txt");
		
		for (String line : fileLines) {
			String[]		bits		= line.split("\\*");
			ResourceType	entryType	= ResourceType.valueOf(bits[1]);
			String			existingID	= bits[entryType.indexOfField(ResourceField.ID)];
			
			if (id.equals(existingID)) {
				return;
			}
		}
		
		fileLines.add(genNewResourceDBStr(id, type, title, extraFields));
		Utilities.writeTextFile("ItemDatabase.txt", fileLines);
		
	}
	
}
