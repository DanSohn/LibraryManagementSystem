package seng_t12.menus;

public class AdminMenu extends ConsoleMenu {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	private static final String		TITLE	= "Admin";
	private static final String[]	OPTIONS	= {
			"Add Item to Database",
			"Check Item Status",
			"Browse User Information",
			"Verify User Registration",
			"Logout",
	};
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	AdminMenu() {
		super(TITLE, OPTIONS);
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	@Override
	protected void runOption(int option) {
		
		switch (option) {
			case 1:
				System.out.println("Add different types of items to the database");
				break;
			case 2:
				System.out.println("Admin provides item ID number and we check it's status in the database");
				break;
			case 3:
				System.out.println("Allow admin to search/display etc. the user database");
				break;
			case 4:
				System.out.println("Admin provides user ID and we check if that user is registered in the database");
				break;
			case 5:
				System.out.println("Logging Out\n");
				EXIT = true;
				break;
			default:
				System.out.println("An unknown error occured");
		}
		
	}
	
}
