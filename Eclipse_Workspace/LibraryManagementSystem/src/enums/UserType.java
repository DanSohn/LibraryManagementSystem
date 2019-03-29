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
	private final static UserField[] FIELDS	= {
			UserField.ID,
			UserField.TYPE,
			UserField.FIRST_NAME,
			UserField.LAST_NAME,
			UserField.EMAIL,
			UserField.PASSWORD,
			UserField.SIGNED_OUT_ARRAY,
			UserField.RESERVE_ARRAY,
			UserField.FINE_AMOUNT,
			UserField.IS_BLACKLISTED
	};

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

	/**
	 * Finds the index of a specified field in a resource string.
	 *
	 * @param field The field to search for.
	 * @return The index of the desired field. -1 if the field is not found.
	 */
	public static int indexOfField(UserField field) {
		
		int i = 0;

		for (UserField foundField : FIELDS) {
			if (field == foundField) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
}
