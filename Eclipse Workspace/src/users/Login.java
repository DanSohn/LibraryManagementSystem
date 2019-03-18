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
	 * @param userType Permission level of user: admin, clerk, faculty, student
	 * @return -1 if login failed, 0 if successful
	 */
	public static boolean userLogin(String email, String password, UserType userType) {
		
		String database;
		int numFields;
		int emailIndex;
		int passIndex;
		int typeIndex;
		
		if (userType.IS_STAFF) {
			database = "StaffDatabase.txt";
			numFields = 3;
			emailIndex = 0;
			passIndex = 1;
			typeIndex = 2;
		} else {
			database = "UserDatabase.txt";
			numFields = 10;
			emailIndex = 4;
			passIndex = 5;
			typeIndex = 1;
		}
		
		try (BufferedReader in = new BufferedReader(new FileReader(database))) {
			String line;
			String[] fields = new String[numFields];
			
			while ((line = in.readLine()) != null) {
				fields = line.split("\\*");
				// Looks for the email of the user we need to check
				if (fields[emailIndex].equals(email)) {
					// Checks password is correct
					if (fields[passIndex].equals(password)) {
						// Checks permission level the user is trying to access is correct
						if (fields[typeIndex].equals(userType.name())) {
							return true;
						}
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
		
		return false;
	}
}
