
public class LoginMenu {

	public static final int NUM_CHOICES = 5;
	boolean exit;
	private MenuInput menuInput = new MenuInput(NUM_CHOICES);

	private void printHeader() {
		
		System.out.println("*****************************************");
		System.out.println("**             Login Menu              **");
		System.out.println("*****************************************");		
		
	}
	
	private void printMenu() {
		
		System.out.println("1. Library Admin");
		System.out.println("2. Library Clerk");
		System.out.println("3. Student");
		System.out.println("4. Faculty");
		System.out.println("5. Exit");
		
	}
	
	public void runMenu() {
		while (!exit) {
			printHeader();
			printMenu();
			int choice = menuInput.getInput();
			processInput(choice);
		}
	}
	
	private void processInput(int choice) {
		
		switch(choice) {
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
			System.out.println("Exiting");
			exit = true;
			break;
		default:
			System.out.println("An unknown error occured");
		}
		
	}	
}
