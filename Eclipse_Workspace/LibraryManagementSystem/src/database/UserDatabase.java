package database;

import enums.ResourceField;
import enums.ResourceType;
import enums.UserField;
import enums.UserType;
import ui.StudentWindow;
import utils.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase {
	
	public static void main (String[] args) {
		System.out.print(searchBookInfo(ResourceType.BOOK.indexOfField(ResourceField.ID), "540087"));
	}
	/**
	 * Generates a new user database string.
	 *
	 * @param id        	The user's id.
	 * @param type      	The user's type.
	 * @param firstName 	The user's first name.
	 * @param lastName  	The user's last name.
	 * @param email     	The user's email.
	 * @param password  	The user's password.
	 * @return 				A string representing the user in the user database.
	 */
	private static String genNewUserDBStr(String id, UserType type, String firstName, String lastName, String email,
			String password) {
		
		return String.join("*", id, type.name(), firstName, lastName, email, password, "NULL", "NULL", "0", "false");	
	}
	
	/**
	 * Adds a new user to the user database.
	 *
	 * @param id        	The user's id.
	 * @param type      	The user's type.
	 * @param firstName 	The user's first name.
	 * @param lastName  	The user's last name.
	 * @param email     	The user's email.
	 * @param password  	The user's password.
	 * @return 				A boolean stating whether or not the operation completed
	 *         				successfully.
	 */
	public static boolean addNewUser(String id, UserType type, String firstName, String lastName, String email,
			String password) {
		
		if(!isInteger(id) || id.length() != 8 || containsInteger(firstName) || containsInteger(lastName) || !email.contains("@") || !email.contains(".")) {
			return false;
		}

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
		// Password is not stored for security
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
            String[]    bits    = line.split("\\*");
            String      ID      = bits[0];
            
            if (userID.equals(ID)) {
                if(fieldToGet == UserField.SIGNED_OUT_ARRAY){
                    String      delimiter = ",";
                    String      bookName = null;
                    String[]    tempArr;
                    String      bookID;
                    String      day;
                    String      month;
                    String      year;
                    String      returnString    = "";
                    String 		result = bits[UserType.STUDENT.indexOfField(fieldToGet)];
                    if(!result.equals("NULL")){
                        tempArr = result.split(delimiter);
                        for(int i = 0; i < tempArr.length; i++){
                            bookID  = tempArr[i].substring(0, 6);
                            day     = tempArr[i].substring(6, 8);
                            month   = tempArr[i].substring(8, 10);
                            year    = tempArr[i].substring(10);
                            
                            
                            bookName = Utilities.getBookName(bookID);
                            returnString += String.format("Book: %s Due date: %s/%s/%s   ID:%s\n", bookName, day, month, year, bookID);
                        }
                    }else{
                        returnString = "No books currently out";
                    }
                    return returnString;
                }else{
                    return bits[UserType.STUDENT.indexOfField(fieldToGet)];
                }
            }
        }
        return null;
    }
	
	/**
	 * Gets the information of the given resource ID.
	 * @param type The index of the resource field.
	 * @param resourceID The ID of the resource.
	 * @return The information 
	 */
	public static String searchBookInfo(int type, String resourceID){
        String returnString = "";
        ArrayList<String> fileLines = Utilities.readTextFile("ItemDatabase.txt");
        boolean itemFound = false;
        
        if (resourceID.length()< 2 ) {
        	return "Please use at least two characters.";
        }
        
        for (String line : fileLines) {
            String[]    bits    = line.split("\\*");
            if(bits[type].toUpperCase().contains(resourceID.toUpperCase())){
            	itemFound = true;
                returnString += "Item ID: " + bits[0] + "\n";
                returnString += "Item Type: " + bits[1] + "\n";
                returnString += "Title: " + bits[2] + "\n";
                returnString += "Author: " + bits[3] + "\n";
                returnString += "Location: " + bits[4] + "\n";
                returnString += "Current Status: " + bits[5] + "\n\n";
            }
        
                
        }
        if (itemFound) return returnString;
        return "Book not found";
    }
	
	/**
	 * Checks if a string is an integer or not
	 * 
	 * @param str string
	 * @return if its an integer
	 */
	public static boolean isInteger(String str) {
	    return isInteger(str,10);
	}

	/**
	 * Checks if string is an integer
	 *  
	 * @param str string
	 * @param max max digit is 10
	 * @return if its an integer
	 */
	public static boolean isInteger(String str, int max) {
	    if(str.isEmpty()) return false;
	    
	    for(int i = 0; i < str.length(); i++) {
	        if(i == 0 && str.charAt(i) == '-') {
	            if(str.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(str.charAt(i),max) < 0) {
	        	return false;
	        }
	    }
	    return true;
	}
	
	public static boolean containsInteger(String str) {
		boolean found = false;
		for(char ch : str.toCharArray()){
	        if(Character.isDigit(ch)){
	            found = true;
	            return found;
	        }
	    }
		
		return found;
	}
}
