package database;

import java.util.ArrayList;
import java.util.Arrays;

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
	 * @param email
	 * @param password
	 * @param type
	 * @return
	 */
	public static String genNewStaffDBStr(String email, String password, UserType type) {
		
		return String.join("*", email, password, type.name(), "NULL", "NULL", "0", "false");
		
	}
	
	/**
	 * Generates a new user database string.
	 * 
	 * @param id
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @return
	 */
	public static String genNewUserDBStr(int id, UserType type, String firstName, String lastName, String email,
			String password) {
		
		return String.join("*", Integer.toString(id), type.name(), firstName, lastName, email, password);
		
	}
	
	/**
	 * Generates a new resource database string.
	 * 
	 * @param id
	 * @param title
	 * @param type
	 * @param status
	 * @param holderID
	 * @param extraFields
	 * @return
	 */
	public static String genNewResourceDBStr(int id, String title, ResourceType type, ResourceStatus status,
			int holderID, String... extraFields) {
		
		return String.join("*", Integer.toString(id), title, type.name()) + "*" + String.join("*", extraFields) + "*"
				+ String.join("*", status.name(), Integer.toString(holderID), "NULL");
		
	}
	
	/*
	 * Modifying existing strings
	 */
	
	/**
	 * Adds a new user to the user database.
	 * 
	 * @param id
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public static void addNewUser(int id, UserType type, String firstName, String lastName, String email,
			String password) {
		
		ArrayList<String> fileLines = Utilities.readTextFile("UserDatabase.txt");
		fileLines.add(genNewUserDBStr(id, type, firstName, lastName, email, password));
		Utilities.writeTextFile("UserDatabase.txt", fileLines);
		
	}
	
	/**
	 * Adds a new resource to the item database.
	 * 
	 * @param id
	 * @param title
	 * @param type
	 * @param status
	 * @param holderID
	 * @param extraFields
	 */
	public static void addNewResource(int id, String title, ResourceType type, ResourceStatus status, int holderID,
			String... extraFields) {
		
		ArrayList<String> fileLines = Utilities.readTextFile("ItemDatabase.txt");
		fileLines.add(genNewResourceDBStr(id, title, type, status, holderID, extraFields));
		Utilities.writeTextFile("ItemDatabase.txt", fileLines);
		
	}
	
}
