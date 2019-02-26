package seng_t12.menus;

public class ClerkMenu extends ConsoleMenu {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	private static final String		TITLE	= "Clerk";
	private static final String[]	OPTIONS	= {
			"Register User",
			"Item Return",
			"Fine Payment",
			"Logout"
	};
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	ClerkMenu() {
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
				System.out.println("Clerk can enter student name, ID etc. and create a data base entry");
				break;
			case 2:
				System.out.println("Clerk enters returned item ID and it updates it's status in the database");
				break;
			case 3:
				System.out.println(
						"Clerk can update a user's amount owed in the database, and take them off the blacklist if they have paid their fine off in full");
				break;
			case 4:
				System.out.println("Logging Out\n");
				EXIT = true;
				break;
			default:
				System.out.println("An unknown error occured");
		}
		
	}
	
}
