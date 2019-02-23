
public class AdminMenu {

	public static final int NUM_CHOICES = 5;
	boolean exit;
	private MenuInput menuInput = new MenuInput(NUM_CHOICES);

	private void printHeader() {
		
		System.out.println("*****************************************");
		System.out.println("**                Admin                **");
		System.out.println("*****************************************");		
		
	}
	
	private void printMenu() {
		
		System.out.println("1. Add Item to Database");
		System.out.println("2. Check Item Status");
		System.out.println("3. Browse User Information");
		System.out.println("4. Verify User Registration");
		System.out.println("5. Logout");
		
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
			exit = true;
			break;
		default:
			System.out.println("An unknown error occured");
		}
		
	}
	
}
