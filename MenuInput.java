import java.util.Scanner;

public class MenuInput {

	private int maxChoice;
	
	public MenuInput(int maxChoice) {
		this.maxChoice = maxChoice;
	}
	
	public int getInput() {
		
		Scanner in = new Scanner(System.in);
		boolean isValid = false;
		int choice = 3;
		
		while (!isValid) {
			try{
				System.out.print("Please make selection: ");
				choice = Integer.parseInt(in.nextLine());
				
				if (choice < 0 || choice > maxChoice) {
					System.out.println("Error: Selection out of range");
				}
				else {
					isValid = true;
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Error: Invalid selection");
			}
		}
		
		return choice;
	}
	
}
