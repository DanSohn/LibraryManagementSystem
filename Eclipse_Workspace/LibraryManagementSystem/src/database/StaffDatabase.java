package database;

import enums.UserType;
import utils.Utilities;

import java.util.ArrayList;

public class StaffDatabase {

    /*
     *
     * METHODS
     *
     */

    /**
     * Generates a new staff database string.
     *
     * @param email    The staff member's email.
     * @param password The staff member's password.
     * @param type     The staff member's type.
     * @return A string representing the staff member in the staff database.
     */
    private static String genNewStaffDBStr(String email, String password, UserType type) {

        return String.join("*", email, password, type.name());

    }

    /**
     * Adds a new user to the user database.
     *
     * @param type      The user's type.
     * @param email     The user's email.
     * @param password  The user's password.
     */
    public static boolean addNewStaff(String email, String password, UserType type) {

        ArrayList<String> fileLines = Utilities.readTextFile("StaffDatabase.txt");

        for (String line : fileLines) {
            String[]	bits			= line.split("\\*");
            String		existingEmail	= bits[0];

            if (email.equals(existingEmail)) {
                return false;
            }
        }

        fileLines.add(genNewStaffDBStr(email, password, type));
        Utilities.writeTextFile("UserDatabase.txt", fileLines);

        return true;

    }

}
