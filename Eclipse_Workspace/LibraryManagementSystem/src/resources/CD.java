package resources;

import java.text.ParseException;
import java.util.Date;

import utils.Utilities;

public class CD extends Resource {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public final String	ARTIST;
	public final String	LABEL;
	public final Date	RELEASE_DATE;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public CD(int id, String title, String location, String artist, String label, Date releaseDate) {
		
		super(id, title, ResourceType.CD, location);
		this.ARTIST			= artist;
		this.LABEL			= label;
		this.RELEASE_DATE	= releaseDate;
		
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
		
		return String.join("*", super.getDBStr(), this.ARTIST, this.LABEL,
				Utilities.DATE_FORMAT.format(this.RELEASE_DATE));
		
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
			throw new IllegalArgumentException("String does not contain 7 fields. Cannot parse as CD.");
		}
		
		try {
			date = Utilities.DATE_FORMAT.parse(bits[6]);
		} catch (NumberFormatException | ParseException e) {
			System.out.println("Invalid release date on resource \"" + bits[1] + "\"! Using null date...");
			date = null;
		}
		
		return new CD(Integer.parseInt(bits[0]), bits[1], bits[3], bits[4], bits[5], date);
		
	}
	
}
