package consoleMenus;

public class LoginMenu extends ConsoleMenu {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	private static final String		TITLE	= "Login Menu";
	private static final String[]	OPTIONS	= {
			"Library Admin",
			"Library Clerk",
			"Student",
			"Faculty",
			"Exit",
	};
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	public LoginMenu() {
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
				AdminMenu adminMenu = new AdminMenu();
				adminMenu.runMenu();
				break;
			case 2:
				ClerkMenu clerkMenu = new ClerkMenu();
				clerkMenu.runMenu();
				break;
			case 3:
				UserMenu studentMenu = new UserMenu("student");
				studentMenu.runMenu();
				break;
			case 4:
				UserMenu facultyMenu = new UserMenu("faculty");
				facultyMenu.runMenu();
				break;
			case 5:
				System.out.println("Exiting...\n");
				EXIT = true;
				break;
			default:
				System.out.println("An unknown error occured.");
		}
		
	}
}
