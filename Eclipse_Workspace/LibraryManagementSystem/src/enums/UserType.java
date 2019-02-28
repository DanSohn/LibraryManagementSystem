package enums;

public enum UserType {
	
	/*
	 * 
	 * ENUM TYPES
	 * 
	 */
	
	LIBRARIAN(0),
	CLERK(0),
	FACULTY(8),
	STUDENT(5);
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public final int MAX_SIGNED_OUT_RESOURCES;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a user type.
	 * 
	 * @param maxSignedOutResources How many resources this user type can have
	 *                                  signed out at once.
	 */
	private UserType(int maxSignedOutResources) {
		
		this.MAX_SIGNED_OUT_RESOURCES = maxSignedOutResources;
		
	}
	
}
