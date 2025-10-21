/**
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Function: This class represents a menu in the console, which allows the user to select from a list of options.
 */

package part02;

import java.util.Scanner;

public class Menu {
	private String[] items;
	private String title;
	private Scanner input;

	public Menu(String title, String[] data, Scanner scanner) {
		this.title = title;
		this.items = data;
		this.input = scanner;
	}

	public void display() {
		System.out.println("\n" + title);
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		System.out.println();
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}
		System.out.println();
	}

	public int getUserChoice() {
		int value = -1;
		while (true) {
			display();
			System.out.print("Enter Selection: ");
			if (input.hasNextInt()) {
				value = input.nextInt();
				input.nextLine();
				if (value >= 1 && value <= items.length) {
					break;
				}
			} else {
				input.nextLine();
			}
			System.out.println("Invalid input. Please enter a number between 1 and " + items.length + ".");
		}
		return value;
	}
}