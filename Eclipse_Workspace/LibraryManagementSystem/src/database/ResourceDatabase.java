package database;

import enums.ResourceField;
import enums.ResourceStatus;
import enums.ResourceType;
import utils.Utilities;

import java.util.ArrayList;

public class ResourceDatabase {

    /*
     *
     * METHODS
     *
     */

    /**
     * Generates a new resource database string.
     *
     * @param id          The resource's id.
     * @param title       The resource's title.
     * @param type        The resource's type.
     * @param extraFields The extra fields for the specific resource.
     * @return A string representing the resource in the item database.
     */
    private static String genNewResourceDBStr(String id, ResourceType type, String title, String... extraFields) {

        return String.join("*", id, type.name(), title) + "*" + String.join("*", extraFields)
                + (type.IS_PHYSICAL ? "*" + String.join("*", ResourceStatus.AVAILABLE.name(), "NULL", "NULL") : "");

    }

    /**
     * Adds a new resource to the item database.
     *
     * @param id          The resource's id.
     * @param title       The resource's title.
     * @param type        The resource's type.
     * @param extraFields The extra fields for the specific resource.
     */
    public static boolean addNewResource(String id, String title, ResourceType type, String... extraFields) {

        ArrayList<String> fileLines = Utilities.readTextFile("ItemDatabase.txt");

        for (String line : fileLines) {
            String[]		bits		= line.split("\\*");
            ResourceType	entryType	= ResourceType.valueOf(bits[1]);
            String			existingID	= bits[entryType.indexOfField(ResourceField.ID)];

            if (id.equals(existingID)) {
                return false;
            }
        }

        fileLines.add(genNewResourceDBStr(id, type, title, extraFields));
        Utilities.writeTextFile("ItemDatabase.txt", fileLines);

        return true;

    }

}
