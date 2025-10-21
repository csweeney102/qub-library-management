/* 
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Function: This replicates the function of QUBLibrary but in a separate window. This allows images to be displayed, and provides a much more aesthetically pleasing interface to the end user.
 */

package part02;

import console.Console;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class QUBLibraryUpdated {
	private static Console console;
	private static Library library;
	private static int accessibility = 20;

	public static void main(String[] args) {
		console = new Console(true);
		library = new Library();

		setupUI();
		mainMenu();
	}

	private static void setupUI() {
		console.setSize(500, 500);
		console.setVisible(true);
		console.setBgColour(Color.WHITE);
		console.setColour(Color.RED);
		console.setFont(new Font("Arial", Font.BOLD, accessibility)); // I had planned for an accessibility feature that
																		// would allow the user to change the font size.
																		// I decided against this in the end.
	}

	private static void mainMenu() {
		while (true) {
			console.clear();
			ImageIcon qub = new ImageIcon("Images/qublibraryupdated.png"); // Path will need to be changed if viewing on
																			// a Windows device, as this was coded on
																			// macOS
			console.println(qub);
			console.println();
			console.println(); // Spaces added because of bug causing text to overlay on QUB logo.
			console.println("Welcome to Queen's Univeristy Belfast Library software!");
			console.println("1. List all Books:");
			console.println("2. List all books by Status:");
			console.println("3. Add a Book:");
			console.println("4. Remove a Book:");
			console.println("5. Borrow a Book:");
			console.println("6. Return a Book:");
			console.println("7. Display ranked list:");
			console.println("8. Exit");
			// console.println("\n" + "\n" + "\n" + "(Press 9 to increase font size)");
// intuitive menu layout designed with the QUB colour scheme & logo
			console.print("Enter choice: ");
			String input = console.readLn();
			try {
				int choice = Integer.parseInt(input);
				switch (choice) {
				case 1:
					viewBooks();
					break;
				case 2:
					viewBooksByStatus();
					break;
				case 3:
					addBook();
					break;
				case 4:
					removeBook();
					break;
				case 5:
					borrowBook();
					break;
				case 6:
					returnBook();
					break;
				case 7:
					rankedList();
					break;
				case 8:
					console.clear();
					// console.println("Exiting...");
					System.exit(0);
					return;
				default:
					console.println("Invalid choice. Try again.");
				}
			} catch (NumberFormatException e) {
				console.println("Invalid input. Please enter a number.");
			}
		}
	}

	private static void viewBooks() { // allows the user to view the books in descending order of the IDs
		console.clear();

		LibraryBook[] books = library.list();

		console.println("Debug: Checking books list..."); // ChatGPT was used to debug an issue where no books were
															// showing up. I copied the 5 library entries from
															// QUBLibrary to Library.
		console.println("Number of books: " + (books == null ? "null" : books.length));

		if (books == null || books.length == 0) {
			console.println("No books available.");
		} else {
			for (LibraryBook book : books) {
				console.println(book.toString());

				if (book.getImage() != null && !book.getImage().isEmpty()) {
					String imagePath = "Images/" + book.getImage();
					console.println(new ImageIcon(imagePath)); // prints the correct image for the book
				}

				console.println("------------------------");
			}
		}

		console.print("Press Enter to go back... ");
		console.readLn();
	}

	private static void viewBooksByStatus() {
		console.clear();
		console.println("View Books by Status");
		console.println("Choose a status to view:");
		console.println("1. Available");
		console.println("2. On Loan");
		console.println("3. Withdrawn"); 

		console.print("Enter choice: ");
		String input = console.readLn();

		BookStatus selectedStatus;
		if (input.equals("1")) {
			selectedStatus = BookStatus.AVAILABLE;
		} else if (input.equals("2")) {
			selectedStatus = BookStatus.ON_LOAN;
		} else if (input.equals("3")) {
			selectedStatus = BookStatus.WITHDRAWN;
		} else {
			console.println("Invalid choice. Returning to main menu.");
			console.readLn();
			return;
		}

		LibraryBook[] booksByStatus = library.list(selectedStatus);

		console.clear();
		console.println("Books with status: " + selectedStatus);

		if (booksByStatus.length == 0) {
			console.println("No books found with this status.");
		} else {
			for (LibraryBook book : booksByStatus) {
				console.println(
						"ID: " + book.getID() + " | Title: " + book.getTitle() + " | Author: " + book.getAuthor());
				console.println("   ISBN: " + book.getIsbn() + " | Edition: " + book.getEdition());
				console.println("   Loan Count: " + book.getLoanCount());

				if (book.getImage() != null && !book.getImage().isEmpty()) {
					String imagePath = "Images/" + book.getImage();
					console.println(new ImageIcon(imagePath));
				}

				console.println("------------------------");
			}
		}

		console.print("Press Enter to go back... ");
		console.readLn();
	}

	private static void addBook() {
		console.clear();
		console.println("Add a New Book");

		String title = "";
		while (title.length() < 5 || title.length() > 40) {
			console.print("Enter Title (5-40 characters): ");
			title = console.readLn();
			if (title.length() < 5 || title.length() > 40) {
				console.println("Title must be between 5 and 40 characters.");
			} // validation checks for titles & etc.
		}

		String author = "";
		while (author.length() < 5 || author.length() > 40) {
			console.print("Enter Author (5-40 characters): ");
			author = console.readLn();
			if (author.length() < 5 || author.length() > 40) {
				console.println("Author must be between 5 and 40 characters.");
			}
		}

		String isbn = "";
		while (isbn.length() != 10 || !isbn.matches("\\d{10}")) {
			console.print("Enter ISBN (10 digits): ");
			isbn = console.readLn();
			if (isbn.length() != 10 || !isbn.matches("\\d{10}")) {
				console.println("ISBN must be 10 digits.");
			}
		}

		BookType type = null;
		while (type == null) {
			console.print("Enter Type (FICTION/NONFICTION/REFERENCE/UNKNOWN): ");
			String typeInput = console.readLn().toUpperCase();
			try {
				type = BookType.valueOf(typeInput);
			} catch (IllegalArgumentException e) {
				console.println("Invalid type. Please enter FICTION, NONFICTION, REFERENCE, or UNKNOWN.");
			}
		}

		String summary = "";
		while (summary.length() < 20 || summary.length() > 150) {
			console.print("Enter Summary (20-150 characters): ");
			summary = console.readLn();
			if (summary.length() < 20 || summary.length() > 150) {
				console.println("Summary must be between 20 and 150 characters.");
			}
		}

		int edition = 0;
		while (edition < 1) {
			try {
				console.print("Enter Edition (>= 1): ");
				edition = Integer.parseInt(console.readLn());
				if (edition < 1) {
					console.println("Edition must be greater than or equal to 1.");
				}
			} catch (NumberFormatException e) {
				console.println("Invalid edition. Please enter a valid number.");
			}
		}

		double price = 0.0;
		while (price <= 0) {
			try {
				console.print("Enter Price: ");
				price = Double.parseDouble(console.readLn());
				if (price <= 0) {
					console.println("Price must be greater than 0.");
				}
			} catch (NumberFormatException e) {
				console.println("Invalid price. Please enter a valid number.");
			}
		}

		// String formattedPrice = String.format("£%.2f", price); attempted to format
		// price

		console.print("Enter Image File Name (or leave blank): ");// 3 book covers are added to the images folder but
		// are intentionally left unused for testing
		// purposes. They are hamilton.jpg,
		// Crimeandpunishment.png and bemorechill.jpg
		String coverImage = console.readLn();

		LibraryBook newBook = new LibraryBook(title, author, isbn, type, summary, edition, price,
				coverImage.isEmpty() ? null : coverImage);

		if (library.add(newBook)) {
			console.println("Book added successfully!");
		} else {
			console.println("Failed to add book.");
		}

		console.readLn();

		if (library.add(newBook)) {
			console.println("Book added successfully!");
		} else {
			console.println("Failed to add book.");
		}

		console.readLn();
	}

	private static void removeBook() {
		console.clear();
		console.println("Remove a Book");

		console.print("Enter Book ID to remove: ");
		String input = console.readLn();

		int id;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			console.println("Invalid input. Please enter a valid numerical ID.");
			console.readLn();
			return;
		}

		LibraryBook book = library.search(id);

		if (book == null) {
			console.println("Book not found.");
		} else if (book.getStatus() == BookStatus.ON_LOAN) {
			console.println("Cannot remove this book. It is currently on loan.");
		} else {
			book.setStatus(BookStatus.WITHDRAWN);
			console.println("Book has been successfully withdrawn from the system.");
		}

		console.print("Press Enter to continue... ");
		console.readLn();
	}

	private static void borrowBook() {
		console.clear();

		console.print("Enter Book ID to Borrow: ");
		String input = console.readLn();

		int id = 0;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			console.println("Invalid input. Please enter a valid ID.");
			return;
		}

		if (library.borrowBook(id)) {
			console.println("Book borrowed successfully!");
		} else {
			console.println("Cannot borrow this book.");
		}

		console.print("Press Enter to continue... ");
		console.readLn();
	}

	private static void returnBook() {
		console.clear();

		console.print("Enter Book ID to Return: ");
		String input = console.readLn();

		int id = 0;
		try {
			id = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			console.println("Invalid input. Please enter a valid ID.");
			return;
		}

		if (library.returnBook(id)) {
			console.println("Book returned successfully!");
		} else {
			console.println("Cannot return this book.");
		}

		console.print("Press Enter to continue... ");
		console.readLn();
	}

	private static void rankedList() {
		console.clear();
		console.println("Most Popular Books");

		LibraryBook[] rankedBooks = library.mostPopular();

		if (rankedBooks.length == 0) {
			console.println("No books available.");
		} else {
			for (int i = 0; i < rankedBooks.length; i++) {
				LibraryBook book = rankedBooks[i];

				console.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor());
				console.println("   Loan Count: " + book.getLoanCount());
				console.println("   Status: " + book.getStatus());

				if (book.getImage() != null && !book.getImage().isEmpty()) {
					String imagePath = "Images/" + book.getImage();
					console.println(new ImageIcon(imagePath));
				}

				console.println("------------------------");
			}
		}

		console.print("Press Enter to go back... ");
		console.readLn();
	}
}