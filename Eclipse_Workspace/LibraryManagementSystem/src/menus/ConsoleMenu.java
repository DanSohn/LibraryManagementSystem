package menus;

import utils.Utilities;

public abstract class ConsoleMenu {
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	private final String	TITLE;
	private final String[]	OPTIONS;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	ConsoleMenu (String title, String[] options) {
		this.TITLE		= title;
		this.OPTIONS	= options;
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	void printMenu() {
		
		/*
		 * Header
		 */

		System.out.println(Utilities.multiplyString("*", 80));
		
		// Center the title in the asterisk box
		System.out.println(
			"**" + 
			Utilities.multiplyString(" ", (int) Math.floor((76 - this.TITLE.length())/2)) +
			this.TITLE +
			Utilities.multiplyString(" ", (int) Math.ceil((76 - this.TITLE.length())/2)) + 
			"**"
		);
		
		System.out.println(Utilities.multiplyString("*", 80));
		
		/*
		 * Options
		 */
		
		System.out.println();
		
		for (int i = 0; i < this.OPTIONS.length; i++) {
			System.out.println((i + 1) + ". " + this.OPTIONS[i]);
		}
		
	}

}
