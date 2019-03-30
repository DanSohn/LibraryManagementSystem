package utils;

public class UserMenu {

	public static final int NUM_CHOICES = 6;
	boolean exit;
	//private MenuInput menuInput = new MenuInput(NUM_CHOICES);
	private String userType;
	private String userEmail;
	// Since the only difference between Student and Faculty is the number of items
	// they can have checked out at once, I though maybe we can just use a variable to
	// differentiate between them later on in the program. Feel free to improve the implementation!
	// 2 possible values: "student" and "faculty"
	public UserMenu(String userType, String userEmail) {
		this.userType = userType;
		this.userEmail = userEmail;
	}

	private void printHeader() {

		System.out.println("*****************************************");
		System.out.println("**                User                 **");
		System.out.println("*****************************************");

	}

	private void printMenu() {

		System.out.println("1. Check Out Item");
		System.out.println("2. Check Item Location");
		System.out.println("3. Reserve Item");
		System.out.println("4. Renew Item");
		System.out.println("5. Browse Scholarly Papers, Journals and Articles");
		System.out.println("6. View Checked Out Items");
		System.out.println("7. Logout");

	}

	/*public void runMenu() {
		printHeader();
		while (!exit) {
			printMenu();
			int choice = menuInput.getInput();
			processInput(choice);
		}
	}*/

	private void processInput(int choice) {

		switch(choice) {
		case 1:
			System.out.println("User enters ID of the item they want to check out, we update database");
			break;
		case 2:
			System.out.println("User enters ID/Name of an item they want and we print it's location in the library");
			break;
		case 3:
			System.out.println("User enters ID/Name of the item they want to reserve, update database appropriately");
			break;
		case 4:
			System.out.println("User picks an item from their borrowed list and renews it, update due date");
			break;
		case 5:
			System.out.println("User can browse a list of links to articles, papers etc.");
			break;
		case 6:
			System.out.println("User can view a list of items he has out, including due date.");
			//getBooks();
			break;
		case 7:
			System.out.println("Logging Out\n");
			exit = true;
			break;
		default:
			System.out.println("An unknown error occured");
		}

	}

    
	
    /*
    public static void main(String[] args){
        System.out.println(getBooks("john.smith@staff.ca"));
    }
    */

}
