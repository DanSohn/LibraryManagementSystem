package database;

import enums.UserType;
import utils.Utilities;

import java.util.ArrayList;

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
     */
    public static boolean addNewUser(String id, UserType type, String firstName, String lastName, String email,
                                  String password) {

        ArrayList<String> fileLines = Utilities.readTextFile("UserDatabase.txt");

        for (String line : fileLines) {
            String[]	bits			= line.split("\\*");
            String		existingID		= bits[0];
            String		existingEmail	= bits[4];

            if (id.equals(existingID) || email.equals(existingEmail)) {
                return false;
            }
        }

        fileLines.add(genNewUserDBStr(id, type, firstName, lastName, email, password));
        Utilities.writeTextFile("UserDatabase.txt", fileLines);

        return true;

    }

}
