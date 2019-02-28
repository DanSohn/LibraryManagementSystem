package resources;

import java.text.ParseException;
import java.util.Date;

import enums.ResourceType;
import utils.Utilities;

public class OnlineArticle extends Resource {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public final String	AUTHOR;
	public final String	DOI;
	public final Date	PUBLISH_DATE;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public OnlineArticle(int id, String title, String location, String author, String doi, Date publishDate) {
		
		super(id, title, ResourceType.ONLINE_ARTICLE, location);
		this.AUTHOR			= author;
		this.DOI			= doi;
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
		
		return String.join("*", super.getDBStr(), this.AUTHOR, this.DOI,
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
			throw new IllegalArgumentException("String does not contain 7 fields. Cannot parse as Online Article.");
		}
		
		try {
			date = Utilities.DATE_FORMAT.parse(bits[6]);
		} catch (NumberFormatException | ParseException e) {
			System.out.println("Invalid publish date on resource \"" + bits[1] + "\"! Using null date...");
			date = null;
		}
		
		return new OnlineArticle(Integer.parseInt(bits[0]), bits[1], bits[3], bits[4], bits[5], date);
		
	}
	
}
