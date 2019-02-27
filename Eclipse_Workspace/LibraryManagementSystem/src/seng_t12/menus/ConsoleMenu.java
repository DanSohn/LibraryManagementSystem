package seng_t12.menus;

import java.util.Scanner;

import utils.Utilities;

public abstract class ConsoleMenu {
	
	/*
	 * 
	 * CONSTANTS & VARIABLES
	 * 
	 */
	
	private final String	TITLE;
	private final String[]	OPTIONS;
	
	protected boolean EXIT = false;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	ConsoleMenu(String title, String[] options) {
		this.TITLE		= title;
		this.OPTIONS	= options;
	}
	
	/*
	 * 
	 * ABSTRACT METHODS
	 * 
	 */
	
	protected abstract void runOption(int option);
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	void runMenu() {
		
		Scanner	consoleIn	= new Scanner(System.in);
		int		option		= 0;
		
		while (!EXIT) {
			
			Utilities.clearConsole();
			
			/*
			 * Header
			 */
			
			System.out.println(Utilities.multiplyString("*", 80));
			
			// Center the title in the asterisk box
			System.out.println(
					"**" + Utilities.multiplyString(" ", (int) Math.floor((76 - this.TITLE.length()) / 2)) + this.TITLE
							+ Utilities.multiplyString(" ", (int) Math.ceil((76 - this.TITLE.length()) / 2)) + "**");
			
			System.out.println(Utilities.multiplyString("*", 80));
			
			/*
			 * Options
			 */
			
			System.out.println();
			
			for (int i = 0; i < this.OPTIONS.length; i++) {
				System.out.println((i + 1) + ". " + this.OPTIONS[i]);
			}
			
			/*
			 * User Input
			 */
			
			System.out.print("Please select an option: ");
			
			try {
				option = Integer.parseInt(consoleIn.nextLine());
			} catch (NumberFormatException nfe) {
				option = -1;
			}
			
			/*
			 * Input Logic
			 */
			
			if (option < 1 || option > this.OPTIONS.length) {
				System.out.println("Invalid option! Please pick a number from 1 to " + this.OPTIONS.length);
			} else {
				runOption(option);
			}
			
			Utilities.waitMilliseconds(1000);
			
		}
		
		consoleIn.close();
		
	}
	
}
