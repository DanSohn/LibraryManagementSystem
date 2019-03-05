package enums;

import java.util.ArrayList;

public enum UserType {
	
	/*
	 * 
	 * ENUM TYPES
	 * 
	 */
	
	ADMIN(true, 0),
	CLERK(true, 0),
	FACULTY(false, 8),
	STUDENT(false, 5);
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	public final boolean	IS_STAFF;
	public final int		MAX_SIGNED_OUT_RESOURCES;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a user type.
	 * 
	 * @param isStaff               If the user type is a staff member or not.
	 * @param maxSignedOutResources How many resources this user type can have
	 *                              signed out at once.
	 */
	private UserType(boolean isStaff, int maxSignedOutResources) {
		
		this.IS_STAFF					= isStaff;
		this.MAX_SIGNED_OUT_RESOURCES	= maxSignedOutResources;
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public static UserType[] getStaffTypes() {
		
		ArrayList<UserType> al = new ArrayList<UserType>();
		
		for (UserType type : UserType.values()) {
			if (type.IS_STAFF) {
				al.add(type);
			}
		}
		
		return al.toArray(new UserType[al.size()]);
		
	}
	
	public static UserType[] getNonStaffTypes() {
		
		ArrayList<UserType> al = new ArrayList<UserType>();
		
		for (UserType type : UserType.values()) {
			if (!type.IS_STAFF) {
				al.add(type);
			}
		}
		
		return al.toArray(new UserType[al.size()]);
		
	}
	
}
