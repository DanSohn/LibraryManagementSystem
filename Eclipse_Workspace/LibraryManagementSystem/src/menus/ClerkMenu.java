package menus;

public class ClerkMenu {

	public static final int NUM_CHOICES = 4;
	boolean exit;
	private MenuInput menuInput = new MenuInput(NUM_CHOICES);

	private void printHeader() {
		
		System.out.println("*****************************************");
		System.out.println("**                Clerk                **");
		System.out.println("*****************************************");		
		
	}
	
	private void printMenu() {
		
		System.out.println("1. Register User");
		System.out.println("2. Item Return");
		System.out.println("3. Fine Payment");
		System.out.println("4. Logout");
		
	}
	
	public void runMenu() {
		printHeader();
		while (!exit) {
			printMenu();
			int choice = menuInput.getInput();
			processInput(choice);
		}
	}
	
	private void processInput(int choice) {
		
		switch(choice) {
		case 1:
			System.out.println("Clerk can enter student name, ID etc. and create a data base entry");
			break;
		case 2:
			System.out.println("Clerk enters returned item ID and it updates it's status in the database");
			break;
		case 3:
			System.out.println("Clerk can update a user's amount owed in the database, and take them off the blacklist if they have paid their fine off in full");
			break;
		case 4:
			System.out.println("Logging Out\n");
			exit = true;
			break;
		default:
			System.out.println("An unknown error occured");
		}
		
	}
	
}
