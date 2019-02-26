package seng_t12.menus;

public class UserMenu extends ConsoleMenu {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	private static final String		TITLE	= "User";
	private static final String[]	OPTIONS	= {
			"Check Out Item",
			"Check Item Location",
			"Reserve Item",
			"Renew Item",
			"Browse Scholarly Papers, Journals and Articles",
			"Logout"
	};
	
	private static String ACCESS_LEVEL;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	UserMenu(String accessLevel) {
		super(TITLE, OPTIONS);
		this.ACCESS_LEVEL = accessLevel;
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
				System.out.println("User enters ID of the item they want to check out, we update database.");
				break;
			case 2:
				System.out
						.println("User enters ID/Name of an item they want and we print its location in the library.");
				break;
			case 3:
				System.out.println(
						"User enters ID/Name of the item they want to reserve, update database appropriately.");
				break;
			case 4:
				System.out.println("User picks an item from their borrowed list and renews it, update due date.");
				break;
			case 5:
				System.out.println("User can browse a list of links to articles, papers etc.");
				break;
			case 6:
				System.out.println("Logging Out...\n");
				EXIT = true;
				break;
			default:
				System.out.println("An unknown error occured.");
		}
		
	}
	
}
