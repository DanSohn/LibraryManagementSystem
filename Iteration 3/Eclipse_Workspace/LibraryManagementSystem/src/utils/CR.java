package utils;

import java.io.IOException;
//  CHECKOUT FUNCTION
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
//
// RESERVE FUNCTION
public class CR {


    // takes:
    //     user_file: Filename of the database of users
    //     item_file: Filename of the database of items/books
    //     user_id:   ID of user that wants to  reserve book
    //     item_id:   ID of item/book user wants to reserve
    // gives:
    //     integer: 1 = book was reserved; -1 = book could not be reserved
    public static int Reserve(String user_file, String item_file, String user_id, String item_id)
        throws IOException {
        // read item database and find item
        String[] items = Io.fileToStringArray(item_file);
        int item_position = Io.findLine(items, 0, item_id);

        // read user database and find user
        String[] users = Io.fileToStringArray(user_file);
        int user_position = Io.findLine(users, 0, user_id);

        // make sure both item and user exist
        if (item_position < 0) { return -1; } // item not found
        if (user_position < 0) { return -1; } // user not found
        if (users[user_position].indexOf(item_id) > -1) {
        	return -1;
        }

        // split line into fields
        String[] item_fields = items[item_position].split("\\*");
        String[] user_fields = users[user_position].split("\\*");

        // check if book has not allowed to be reserved (is a refernce)
        if (item_fields[5].equals("REFERENCE")) { return -1; } // book is reference
        // check if book even needs to be reserved
        if (item_fields[5].equals("AVAILABLE")) { return -1; } // book is available

        // book is unavailable and can be reserved, unless we have it
        if (item_fields[6].equals(user_id)) { return -1; } // we already have the book

        // Add user to item reserve queue
        if (item_fields[7].equals("NULL")) {
            item_fields[7] = user_fields[0];
        } else {
            item_fields[7] = item_fields[7] + "," + user_fields[0];
        }

        // add item to user reserve queue
        if (user_fields[7].equals("NULL")) {
            user_fields[7] = item_id + Utilities.get2WeeksDate();
        } else {
            user_fields[7] = user_fields[7] + "," + item_id;
        }

        String temp = user_fields[0];
        String tempo = item_fields[0];

        for (int i = 1; i < 10; i++) {
            temp = temp + "*" + user_fields[i];
        }

        users[user_position] = temp;

        for (int j = 1; j < 8; j++) {
            tempo = tempo + "*" + item_fields[j];
        }

        items[item_position] = tempo;

        Io.save(item_file, items);
        Io.save(user_file, users);



        return 1;
    }
    
    public static int Checkout(String user_db_filename, String item_db_filename, String user_id,
                    String book_id) throws IOException {

        // Get required info from items database
        // read items db
        String[] items = Io.fileToStringArray(item_db_filename);
        // find book in database
        int l = Io.findLine(items, 0, book_id);
        if (l < 0) {
                System.out.println("book not found");
                return -1;
        }
        System.out.println(items[l]);
        // get fields
        String[] item_fields = items[l].split("\\*");

        // Get required info from users database
        // read users db
        String[] users = Io.fileToStringArray(user_db_filename);
        // find user in database
        int n = Io.findLine(users, 0, user_id);
        if (n < 0) {
                System.out.println("could not find user");
                return -1;
        }
        System.out.println(users[n]);
        // get users fields
        String[] user_fields = users[n].split("\\*");
        
        
        // check if unavailable
        if (item_fields[5] == "UNAVAILABLE") {
                System.out.println("book unavailable");
                return -1;
        // check if book is reference
        } else if (item_fields[5] == "REFERENCE") {
                System.out.println("book unavailable");
                return -1;
        }
        
        

        // check if book has reserve queue
        System.out.println(item_fields[7]);
        if (item_fields[7].indexOf("NULL") < 0) {
            // if so, check if user is at front of queue
            if (item_fields[7].indexOf(user_id) < 5 && item_fields[7].indexOf(user_id) > -1) {
                // user is at the front of the reserve queue, and can take the book out
                
                // remove user from reserve queue
                if (item_fields[7].length() > 8) {
                    // more than one person in reserve queue, make sure to remove extra comma
                    item_fields[7] = item_fields[7].substring(9, item_fields[7].length());
                } else {
                    // reserve queue now empty
                    item_fields[7] = "NULL";
                }

                // remove book id from list of reserved books
                if (user_fields[7].length() > 14) {
                    // more than one book reserved by user
                } else { // only one book was reserved by user, remove from queue
                    user_fields[7] = "NULL";
                }
            } else {
                System.out.println("book is reserved by someone else");
                return -1;
            }
        }


        // change current holder
        item_fields[6] = user_id;
        // change item status
        item_fields[5] = "UNAVAILABLE";
        
        // undo string splitting
        String temp = "";
        for (int j = 0; j < item_fields.length; j++) {
                temp = temp + "*" + item_fields[j];
        }
        
        // UPDATE ENTRY IN TEMP DATABASE AND WRITE TO FILE
        items[l] = temp.substring(1); // substring to remove initial '*'
        System.out.println(items[l]);
        Io.save(item_db_filename, items);
        
        // ADD BOOK TO LIST OF USERS BOOKS CURRENTLY TAKEN OUT
        // check if user has books taken out
        if (user_fields[6].indexOf("NULL") > -1) {
            // make item only book taken out
            user_fields[6] = book_id + Utilities.get2WeeksDate();
        } else {
            // add book to list of books taken out
            user_fields[6] = user_fields[6] + "," + book_id + Utilities.get2WeeksDate();
        }

        // remove book from users reserved books (if it was reserved)
        if (user_fields[7].indexOf(book_id) > -1) { // user had book reserved
            String[] user_reserved = user_fields[7].split(",");
            for (int i = 0; i < user_reserved.length; i++) {
                if (user_reserved[i].indexOf(book_id) > -1) {
                    user_reserved[i] = "";
                }
            }

            // add reservation date to next book IF QUEUE ISNT EMPTY
            if (user_reserved.length > 0) {
                if (user_reserved[0].length() < 11 && user_reserved[0].length() > 2) {
                    user_reserved[0] = user_reserved[0] + Utilities.get2WeeksDate();
                } else {
                    user_reserved[0] = "NULL";
                }
            }

            temp = "";
            for (int x = 0; x < user_reserved.length; x++) {
                temp = "," + user_reserved[x];
            }
            // update reserved field
            user_fields[7] = temp.substring(1);
        }




       
        // undo string splitting
        temp = "";
        for (int j = 0; j < user_fields.length; j++) {
                temp = temp + "*" + user_fields[j];
        }
        // update temp database
        users[n] = temp.substring(1);
        System.out.println(users[n]);

        // write temporary database to file
        Io.save(user_db_filename, users);
        
        return 1; // success
    }
}
