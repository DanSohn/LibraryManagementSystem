package database;

import enums.ResourceField;
import enums.ResourceStatus;
import enums.ResourceType;
import utils.Utilities;

import java.util.ArrayList;

public class ResourceDatabase {

    /**
     * Generates a new resource database string.
     *
     * @param id          	The resource's id.
     * @param title       	The resource's title.
     * @param type        	The resource's type.
     * @param extraFields 	The extra fields for the specific resource.
     * @return 				A string representing the resource in the item database.
     */
    private static String genNewResourceDBStr(String id, ResourceType type, String title, String... extraFields) {

        return String.join("*", id, type.name(), title) + "*" + String.join("*", extraFields)
                + (type.IS_PHYSICAL ? "*" + String.join("*", ResourceStatus.AVAILABLE.name(), "NULL", "NULL") : "");
    }
 
    /**
     * Adds a new resource to the item database.
     *
     * @param id          	The resource's id.
     * @param title       	The resource's title.
     * @param type        	The resource's type.
     * @param extraFields 	The extra fields for the specific resource.
     * @return 				A boolean stating whether or not the operation completed successfully.
     */
    public static boolean addNewResource(String id, String title, ResourceType type, String... extraFields) {
    	
    	if(!isInteger(id) || id.length() != 6) return false;
    	//	Opens the item database for reading and writing
        ArrayList<String> fileLines = Utilities.readTextFile("ItemDatabase.txt");

        //	Iterates the document and checks if the ID already exists
        //	and returns false if it does
        for (String line : fileLines) {
            String[]		bits		= line.split("\\*");
            String			existingID	= bits[type.indexOfField(ResourceField.ID)];

            if (id.equals(existingID)) {
                return false;
            }
        }

        //	If the ID was not found, adds it to the database and returns true
        fileLines.add(genNewResourceDBStr(id, type, title, extraFields));
        Utilities.writeTextFile("ItemDatabase.txt", fileLines);

        return true;
    }
    
    /**
     * Gets a 2D array of all the stored resources, splitting each parameter into a column.
     * @return 2D array of stored resources.
     */
    public static String[][] getStoredResources() {

        ArrayList<String>   fileLines = Utilities.readTextFile("ItemDatabase.txt");
        String[][]          resources = new String[fileLines.size()][ResourceType.MAX_NUM_FIELDS];

        //	Iterates through database
        for (int i = 0; i < fileLines.size(); i++) {
            String[] bits = fileLines.get(i).split("\\*");
            for (int j = 0; j < bits.length; j++) {
                resources[i][j] = bits[j];
            }
        }

        return resources;
    }
    
    /**
     * Gets the specified parameter of the the specified resource from the resource database.
     * @param resourceID    	The ID of the item to look at.
     * @param resourceType  	The type of the resource to look at.
     * @param fieldToGet    	The field to get from the resource.
     * @return              	The value of the field.
     * @throws Exception    	If the resource is not in the database.
     */
    public static String getParameterOfResource(String resourceID, ResourceType resourceType, ResourceField fieldToGet) { 

        ArrayList<String>   fileLines = Utilities.readTextFile("ItemDatabase.txt");

        //	Iterates through database
        for (String line : fileLines) {
            String[]        bits    = line.split("\\*");
            String          ID      = bits[resourceType.indexOfField(ResourceField.ID)];
            ResourceType    type    = ResourceType.valueOf(bits[resourceType.indexOfField(ResourceField.TYPE)]);

            if (resourceID.equals(ID) && resourceType == type) {
                return bits[resourceType.indexOfField(fieldToGet)];
            }
        }

        return null;
    }
    
    /**
     * Gets the ID of a resource, specified via the title.
     * @param resourceTitle The title of the resource to search
     * @param resourceType The type of the resource to search
     * @return The ID of the resource
     */
    public static String getIDFromTitle(String resourceTitle, ResourceType resourceType) {
    	
    	ArrayList<String>   fileLines = Utilities.readTextFile("ItemDatabase.txt");

        //	Iterates through database
        for (String line : fileLines) {
            String[]        bits    = line.split("\\*");
            String          title   = bits[resourceType.indexOfField(ResourceField.TITLE)].toUpperCase();
            ResourceType    type    = ResourceType.valueOf(bits[resourceType.indexOfField(ResourceField.TYPE)]);

            if (resourceTitle.equals(title) && resourceType == type) {
                return bits[resourceType.indexOfField(ResourceField.ID)];
            }
        }

        return null;
    	
    }
    
    /**
	 * Checks if a string is an integer or not
	 * 
	 * @param str string
	 * @return if its an integer
	 */
	private static boolean isInteger(String str) {
	    return isInteger(str,10);
	}

	/**
	 * Checks if string is an integer
	 *  
	 * @param str string
	 * @param max digit is 10
	 * @return if its an integer
	 */
	private static boolean isInteger(String str, int max) {
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
