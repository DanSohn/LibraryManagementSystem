package resources;

import enums.ResourceType;

public class Resource {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public final int			ID;
	public final String			TITLE;
	public final ResourceType	TYPE;
	public final String			LOCATION;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a resource object.
	 * 
	 * @param id       The ID number for the resource.
	 * @param title    The title of the resource.
	 * @param Type     The type of the resource.
	 * @param location The location of the resource.
	 */
	Resource(int id, String title, ResourceType type, String location) {
		
		this.ID			= id;
		this.TITLE		= title;
		this.TYPE		= type;
		this.LOCATION	= location;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Creates a database string to represent the object in the database text file.
	 * 
	 * @return The string to store the resource in the database.
	 */
	public String getDBStr() {
		
		return String.join("*", Integer.toString(ID), TITLE, TYPE.toString(), LOCATION);
		
	}
	
	/**
	 * Parses a database string into a resource object.
	 * 
	 * @param DBStr The string representing the resource to parse.
	 * @return The represented resource object.
	 */
	public Resource parseResource(String DBStr) {
		
		String[] bits = DBStr.split("*");
		
		if (bits.length != 4) {
			throw new IllegalArgumentException("String does not contain 4 fields. Cannot parse as plain Resource.");
		}
		
		return new Resource(Integer.parseInt(bits[0]), bits[1], ResourceType.valueOf(bits[2]), bits[3]);
		
	}
	
}
