package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseTesting {
	
	public static final int NUM_FIELDS = 8;

	public static void main(String[] args) {

		File databaseFile = new File("Database.txt");
		if (!databaseFile.exists()) {
			System.out.println("Error: Database file not found");
		}
		
		else {
			System.out.println("*****Display Test*****");
			displayDatabase(databaseFile);
			
			System.out.println("\n*****Check Entry Test*****");
			System.out.println(isBlacklisted("30030179", databaseFile));
			System.out.println(isBlacklisted("99999999", databaseFile));
			
			System.out.println("\n*****Edit Entry Test*****");
			addBlacklist("77777777", databaseFile);
			
			System.out.println("\n*****Add Entry Test*****");
			addEntry(databaseFile);
		}
		
	}

	private static void displayDatabase(File database) {
		try (BufferedReader in = new BufferedReader(new FileReader(database))) {
			String line;
			int i;
			String[] fields = new String[NUM_FIELDS];
			
			// Print table headers
			System.out.printf("%-15s%-15s%-15s%-15s%-30s%-15s%-15s%s\n", 
					"ID", "TYPE", "FIRST NAME", "LAST NAME", "EMAIL", "PASSWORD", 
					"FINE AMOUNT", "BLACKLIST");
			
			// Read text file line by line and print each field
			while ((line = in.readLine()) != null) {
				// Since each entry is separated by * in the text file, we can easily
				// split the whole line into individual fields in an array
				fields = line.split("\\*");
				for (i = 0; i < NUM_FIELDS; i++) {
					// The email field took up a bit more space, so I added some extra
					// spacing just to make it look nice
					if (i == 4) {
						System.out.printf("%-30s", fields[i]);
					}
					// Puts a dollar sign in front of the "fine amount" field
					else if (i == 6) {
						System.out.printf("$%-15s", fields[i]);
					}
					else {
						System.out.printf("%-15s", fields[i]);
					}
				}
				// Ensure each individual is printed on a new line
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Checks whether or not a user is blacklisted
	private static boolean isBlacklisted(String userID, File database) {
		boolean result = false;
		
		try (BufferedReader in = new BufferedReader(new FileReader(database))) {
			String line;
			String[] fields = new String[NUM_FIELDS];
			
			while ((line = in.readLine()) != null) {
				fields = line.split("\\*");
				// Looks for the ID of the user we need to check
				if (fields[0].equals(userID)) {
					result = Boolean.parseBoolean(fields[7]);
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
		return result;
	}
	
	// Things got a bit complicated here, hopefully it's easy enough to understand
	// Essentially what we do is read the whole file, edit the line we need to,
	// then write the whole file back
	private static void addBlacklist(String userID, File database) {
		StringBuffer stringBuffer = new StringBuffer();
		String tempString;
		
		try (BufferedReader in = new BufferedReader(new FileReader(database))) {
			String line;
			String[] fields = new String[NUM_FIELDS];
			while ((line = in.readLine()) != null) {
				fields = line.split("\\*");
				// This is where we find the ID of the line we need to edit
				if (fields[0].equals(userID)) {
					// We will only edit it if it's currently false. If it's already true, we don't
					// need to do anything
					if (fields[7].equals("false")) {
						// tempString now contains the whole line minus the "false" part at the end
						tempString = line.substring(0, line.length()-5);
						// Now we set line equal to that cut off string plus "true"
						line = tempString + "true";
						// NOTE: This case was a little easier since the blacklist entry is at the end
						// When we need to edit entries in the middle it'll involve a few more lines
						// However the same principle applies
					}
				}
				// StringBuffer allows us to build strings dynamically
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			
			// Write the whole file back, with the updated line
			BufferedWriter out = new BufferedWriter(new FileWriter(database));
			out.write(stringBuffer.toString());
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void addEntry(File database) {
		// Would be nice to have a GUI for this later, but for now it's all entered in the temrinal
		String id, type, firstName, lastName, email, password;
		StringBuffer stringBuffer = new StringBuffer();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("*****Adding new user to the database*****");
		System.out.printf("Please enter user ID: ");
		id = scan.nextLine();
		System.out.printf("Please enter user type: ");
		type = scan.nextLine();
		System.out.printf("Please enter first name: ");
		firstName = scan.nextLine();
		System.out.printf("Please enter last name: ");
		lastName = scan.nextLine();
		System.out.printf("Please enter user email: ");
		email = scan.nextLine();
		System.out.printf("Please enter user password: ");
		password = scan.nextLine();
		
		scan.close();
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, true))) {
			stringBuffer.append(id);
			stringBuffer.append('*');
			stringBuffer.append(type);
			stringBuffer.append('*');
			stringBuffer.append(firstName);
			stringBuffer.append('*');
			stringBuffer.append(lastName);
			stringBuffer.append('*');
			stringBuffer.append(email);
			stringBuffer.append('*');
			stringBuffer.append(password);
			stringBuffer.append('*');
			stringBuffer.append("0");
			stringBuffer.append('*');
			stringBuffer.append("false");
			stringBuffer.append('\n');
			
			out.write(stringBuffer.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
