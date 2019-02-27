package resources;

import java.text.ParseException;
import java.util.Date;

import utils.Utilities;

public class Book extends Resource {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public final String	AUTHOR;
	public final String	PUBLISHER;
	public final Date	PUBLISH_DATE;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public Book(int id, String title, String location, String author, String publisher, Date publishDate) {
		
		super(id, title, ResourceType.BOOK, location);
		this.AUTHOR			= author;
		this.PUBLISHER		= publisher;
		this.PUBLISH_DATE	= publishDate;
		
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
	@Override
	public String getDBStr() {
		
		return String.join("*", super.getDBStr(), this.AUTHOR, this.PUBLISHER,
				Utilities.DATE_FORMAT.format(this.PUBLISH_DATE));
		
	}
	
	/**
	 * Parses a database string into a resource object.
	 * 
	 * @param DBStr The string representing the resource to parse.
	 * @return The represented resource object.
	 */
	@Override
	public Resource parseResource(String DBStr) {
		
		String[]	bits	= DBStr.split("*");
		Date		date;
		
		if (bits.length != 7) {
			throw new IllegalArgumentException("String does not contain 7 fields. Cannot parse as Book.");
		}
		
		try {
			date = Utilities.DATE_FORMAT.parse(bits[6]);
		} catch (NumberFormatException | ParseException e) {
			System.out.println("Invalid publish date on resource \"" + bits[1] + "\"! Using null date...");
			date = null;
		}
		
		return new Book(Integer.parseInt(bits[0]), bits[1], bits[3], bits[4], bits[5], date);
		
	}
	
}
