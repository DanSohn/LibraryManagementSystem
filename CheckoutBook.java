package temp;

import java.io.IOException;
import java.time.*;
// 
// in items database:
//                    check if item exists (if not; cancel)
//                      check if item is available:
//                        if not; cancel
//                    check if reservered (if not, skip)
//                      check if person renting is reserver (if not, cancel)
//                        if so remove their name from reserved list
//
//                    change item status (un/available/etc).
//                    changes current holder's user id
//                    if student had this item reserved
//                      remove them from reserved list
//
// in users data base:
//                    get date
//                    add item to list of borrowed items
//                    remove item from list of reserved items (if reserved)
//
// return success boolean ( -1 = did not rent; 1 = rented);


// THIS STILL NEEDS TO CHECK FOR RESERVATION FOR SELF
public class CheckoutBook {
	public static int Checkout(String user_db_filename, String item_db_filename, String user_id,
			String book_id) throws IOException {

		String[] items = Io.fileToStringArray(item_db_filename);
		
		// check if book exists
		int l = Io.findLine(items, 0, book_id);
		if (l < 0) {
			System.out.println("book not found");
			return -1;
		}
		
		// check if unavailable
		if (items[l].indexOf("unavailable") > -1) {
			System.out.println("book unavailable");
			return -1;
		// check if book is reference
		} else if (items[l].indexOf("reference") > -1) {
			System.out.println("book unavailable");
			return -1;
		}
		
		// I STILL HAVE TO CHECK THE RESERVE QUEUE RIGHT HERE
		// if this student had this book reserved, remove from
		// reserved list
		
		String temp = items[l];
		String[] fields = temp.split("\\*");
		fields[6] = user_id; // change current holder
		fields[5] = "unavailable"; // change item status
		
		temp = "*";
		for (int j = 0; j < fields.length; j++) {
			temp = temp + fields[j];
			temp = temp + "*";
		}
		
		items[l] = temp;
		
		//save changes to item database
		Io.save(item_db_filename, items);
		
		// get users db
		String[] users = Io.fileToStringArray(user_db_filename);
		// in users database add to list of book currently taken out
		
		// find user in database
		l = Io.findLine(users, 0, user_id);
		if (l < 0) {
			System.out.println("could not find user");
			return -1;
		}
		temp = users[l]; // user's database entry
		fields = temp.split("\\*");
		
		// get date of checkout
		// 09/28/2015
	    String today = LocalDate.now().toString().substring(8) + LocalDate.now().toString().substring(5,7) +
	    		LocalDate.now().toString().substring(0,4);
	    
	    fields[6] = fields[6] + "," + book_id + today;
	    
	    return 1; // success
	}
	
}
