
/**
 * Clerk class for performing Clerk functionalities
 * 
 * @author Jacob Cuke
 * 
 */

package users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Utilities;

public class Clerk {

	/**
	 * Performs all the operations necessary for the returning of a resource
	 * 
	 * @param resourceID ID of the resource to be returned
	 * @param userID ID of the user returning it
	 * @return 1 if successful, -1 if an error occurred
	 */
	public int returnResource(String resourceID, String userID) {
		
		ArrayList<String> fileLines = Utilities.readTextFile("UserDatabase.txt");
		// File not found
		if (fileLines == null) {
			return -1;
		}
		
		// Make sure the user actually has the book checked out
		// If so, get due date
		boolean found = false;
		String dueDate = null;
		for (String line : fileLines) {
			String[] bits = line.split("\\*");
			// Find the user and make sure their list of borrowed items is not empty
			if (bits[0].equals(userID) && !bits[6].equals("NULL")) {
				String[] items = bits[6].split(",");
				for (String item : items) {
					// Find the item the user is trying to return
					if (item.substring(0,6).equals(resourceID)) {
						// Get the 8 digits after the resource ID that represent the due date
						dueDate = item.substring(6);
						found = true;
					}
				}
				break;
			}
		}
		
		// User does not currently have the item checked out
		if (!found) {
			System.out.println("Error: User does not currently have that item checked out");
			return -1;
		}
		
		// Calculate the number of $5 fines to be added
		int numFines = Utilities.compareDates(Utilities.getCurrentDate(), dueDate);
		
		// If the user is returning the book late
		if (numFines > 0) {
			updateFine(fileLines, userID, 5 * numFines);
		}
		
		// Remove the resource from the user's list of borrowed items
		removeResourceId(fileLines, userID, resourceID);
		
		// Update the item's status
		int status = updateItemStatus(fileLines, resourceID);
		// If item database file was not found
		if (status == -1) {
			return -1;
		}
		
		// Finally, write and update the User Database
		Utilities.writeTextFile("UserDatabase.txt", fileLines);
		
		return 1;
	}

	/**
	 * Adds the specified amount to a user's fine total
	 * 
	 * @param fileLines The contents of the file to be edited
	 * @param userID ID of the user whose fine is to updated
	 * @param fineToAdd Amount to add to the current fine value
	 */
	private void updateFine(ArrayList<String> fileLines, String userID, int fineToAdd) {
		for (String line : fileLines) {
			String[] bits = line.split("\\*");
			// Find the user
			if (bits[0].equals(userID)) {
				// Add the specified amount to the current fine
				int newFine = Integer.parseInt(bits[8]) + fineToAdd;
				// If the fine has now gone over $50, blacklist the user
				if (newFine >= 50) {
					bits[9] = "true";
				}
				// Update the file contents with the changed entries
				bits[8] = Integer.toString(newFine);
				String newLine = String.join("*", bits);
				int index = fileLines.indexOf(line);
				fileLines.set(index, newLine);
				break;
			}
		}
	}
	
	/**
	 * Returns the user's fine amount
	 * @param userID user's ID number
	 * @return fine amount, -1 if error occured
	 */
	public int getFine(String userID) {
		int fine = -1; // -1 returned if user not found
		
		ArrayList<String> fileLines = Utilities.readTextFile("UserDatabase.txt");
		// File not found
		if (fileLines == null) {
			return -1;
		}
		
		for (String line : fileLines) {
			String[] bits = line.split("\\*");
			// Find the user and return their fine amount
			if (bits[0].equals(userID)) {
				fine = Integer.parseInt(bits[8]);
				break;
			}
		}
		
		return fine;
	}
	
	/**
	 * Reduces the user's fine total by the specified amount
	 * 
	 * @param userID ID of the user who's fine is to be updated
	 * @param amount Amount paid in fine dues
	 * @return user's change, -1 if there was an error
	 */
	public int payFine(String userID, int amount) {
		int change = 0, newFine;
		boolean found = false;
		
		ArrayList<String> fileLines = Utilities.readTextFile("UserDatabase.txt");
		// File not found
		if (fileLines == null) {
			return -1;
		}
		
		for (String line : fileLines) {
			String[] bits = line.split("\\*");
			// Find the user
			if (bits[0].equals(userID)) {
				found = true;
				int fine = Integer.parseInt(bits[8]);
				if (amount > fine) {
					change = amount - fine;
					newFine = 0;
				}
				else {
					// Subtract the amount paid off from the user's fine total
					newFine = Integer.parseInt(bits[8]) - amount;
				}
				// If the fine has reached zero, unblacklist the user
				// Has no effect if they were never blacklisted in the first place
				if (newFine == 0) {
					bits[9] = "false";
				}
				// Update the file contents with the changed entries
				bits[8] = Integer.toString(newFine);
				String newLine = String.join("*", bits);
				int index = fileLines.indexOf(line);
				fileLines.set(index, newLine);
				break;
			}
		}
		
		// If user doesn't exist return -1
		if (!found) {
			return -1;
		}
		
		// Finally, write and update the User Database
		Utilities.writeTextFile("UserDatabase.txt", fileLines);
		return change;
	}

	/**
	 * Removes a resource from a user's list of borrowed items
	 * 
	 * @param fileLines The contents of the file to be edited
	 * @param userID ID of the user whose list of borrwed items is to be updated
	 * @param resourceID ID of the item to remove
	 */
	private void removeResourceId(ArrayList<String> fileLines, String userID, String resourceID) {
		for (String line : fileLines) {
			String[] bits = line.split("\\*");
			// Find the user
			if (bits[0].equals(userID)) {
				String[] items = bits[6].split(",");
				// If the user only had 1 item checked out, set their list to empty
				if (items.length == 1) bits[6] = "NULL";
				else {
					for (String item : items) {
						// Find the item the user is returning and remove it
						if (item.substring(0,6).equals(resourceID)) {
							List<String> list = new ArrayList<String>(Arrays.asList(items));
							list.remove(item);
							items = list.toArray(new String[0]);
							break;
						}
					}
					bits[6] = String.join(",", items);
				}
				// Update the file contents with the changed entries
				String newLine = String.join("*", bits);
				int index = fileLines.indexOf(line);
				fileLines.set(index, newLine);
				break;
			}
		}
	}
	
	/**
	 * Updates an item's status after it has been returned
	 * Additionally, issues a pick-up-by date for the next user in the reserve queue
	 * 
	 * @param userLines The contents of the user file to be edited
	 * @param resourceID ID of the item whose status is to be updated
	 */
	private int updateItemStatus(ArrayList<String> userLines, String resourceID) {
		ArrayList<String> itemLines = Utilities.readTextFile("ItemDatabase.txt");
		// File not found
		if (itemLines == null ) {
			return -1;
		}
		
		for (String line : itemLines) {
			String[] bits = line.split("\\*");
			// Find the item
			if (bits[0].equals(resourceID)) {
				// Update the current holder field to NULL
				bits[6] = "NULL";
				// If the item has been restricted, do not make it available
				if (!bits[5].equals("RESTRICTED")) {
					// If there is no one in the reserve queue, set item to AVAILABLE
					if (bits[7].equals("NULL")) {
						bits[5] = "AVAILABLE";
					} else {
						// Issue a pick-up-by date for the first user waiting in the queue
						String[] users = bits[7].split(",");
						addPickupDate(userLines, users[0], resourceID);
					}
				}
				
				// Update the file contents with the changed entries
				String newLine = String.join("*", bits);
				int index = itemLines.indexOf(line);
				itemLines.set(index, newLine);
				break;
			}
		}
		// Write and update Item Database
		Utilities.writeTextFile("ItemDatabase.txt", itemLines);
		
		return 1;
	}

	/**
	 * Issues a pick-up-by date for an item in a user's list of reserved items
	 * 
	 * @param fileLines The contents of the file to be edited
	 * @param userID ID of the user whose list is to be updated
	 * @param resourceID ID of the resource for which the pick-up-by date is being issued
	 */
	private void addPickupDate(ArrayList<String> fileLines, String userID, String resourceID) {
		for (String line : fileLines) {
			String[] bits = line.split("\\*");
			// Find the user
			if (bits[0].equals(userID)) {
				String[] items = bits[7].split(",");
				// Find the resource to be issued the pick-up-by date
				for (int i = 0; i < items.length; i++) {
					if (items[i].equals(resourceID)) {
						// Concatenate the date 2 weeks from today
						items[i] = items[i] + Utilities.get2WeeksDate();
						break;
					}
				}
				// Update the file contents with the changed entries
				bits[7] = String.join(",", items);
				String newLine = String.join("*", bits);
				int index = fileLines.indexOf(line);
				fileLines.set(index, newLine);
				break;
			}
		}
		
	}
	
	/**
	 * Main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		System.out.println(clerk.getFine("31249002"));
	}

}
