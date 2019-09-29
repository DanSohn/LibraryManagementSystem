package users;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import enums.UserType;

public class Login {

	public static final int NUM_USER_FIELDS = 10;
	public static final int NUM_STAFF_FIELDS = 2;
	
	/**
	 * 
	 * @param email Email entered by the user
	 * @param password Password entered by the user
	 * @return null if login failed, user type if successful
	 */
	public static UserType userLogin(String email, String password) {
		
		String database = "UserDatabase.txt";
		int numFields = 10;
		int emailIndex = 4;
		int passIndex = 5;
		int typeIndex = 1;
		
		try (BufferedReader in = new BufferedReader(new FileReader(database))) {
			String line;
			String[] fields = new String[numFields];
			
			while ((line = in.readLine()) != null) {
				fields = line.split("\\*");
				// Looks for the email of the user we need to check
				if (fields[emailIndex].equals(email)) {
					// Checks password is correct
					if (fields[passIndex].equals(password)) {
						return UserType.valueOf(fields[typeIndex]);
					}
					// break if anything didn't match up
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
		
		// Return null if login was unsuccessful
		return null;
	}
}



// UserType.valueOf(string)