/*
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Description: This class allows users to interact with the QUBLibrary program through the IDE's built in console. There are 5 predetermined books added, but users can add, remove, borrow, return the books on the system.
 */
package part02;

import java.util.Scanner;

public class QUBLibrary {
	private Library library;
	private Menu menu;
	private Scanner scanner;

	public QUBLibrary() {
		library = new Library();
		scanner = new Scanner(System.in);
		String[] options = { "List all books", "List books by status", "Add a book", "Remove a book", "Borrow a book",
				"Return a book", "Display ranked list", "Exit" };
		menu = new Menu("QUB Library Menu", options, scanner);
		library.add(new LibraryBook("Harry Potter and the Philosopher's Stone", "JK Rowling", "1234567890",
				BookType.FICTION,
				"Harry Potter embarks on a thrilling adventure in the second installment of the renowned series.", 1,
				5.99, null));
		library.add(new LibraryBook("Fundamentals of Mathematics for Computing", "Matthew Collins", "0987654321",
				BookType.NONFICTION,
				"Mathematical essentials for computing are explained, including set theory and propositinal logic.", 1,
				19.99, null));
		library.add(new LibraryBook("The God Delusion", "Richard Dawkins", "1112233445", BookType.FICTION,
				"Richard Dawkins explains his athiestic views, giving a philosophical insight into the existence of God.",
				2, 30.0, "goddelusion.jpg"));
		library.add(new LibraryBook("The New Testament", "Unknown", "1225202400", BookType.UNKNOWN,
				"The New Testament tells the story of Jesus Christ, his teachings, crucifixion, and resurrection, along with the early Christian Church’s growth and teachings.",
				7, 9.99, null));
		library.add(new LibraryBook("Object-Oriented Programming", "Paul Sage", "1027102956", BookType.NONFICTION,
				"Paul Sage introduces object orientated programming concepts, such as inheritence, encapsulation and alogirthms.",
				3, 47.50, null));
	}

	public void run() {
		boolean running = true;
		while (running) {
			int choice = menu.getUserChoice();
			switch (choice) {
			case 1:
				listAllBooks();
				break;
			case 2:
				listBooksByStatus();
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
				displayRankedList();
				break;
			case 8:
				running = false;
				System.out.println("Exiting QUB Library. Goodbye!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
		scanner.close();
	}

	private void listAllBooks() {
		LibraryBook[] books = library.list();
		if (books.length == 0) {
			System.out.println("No books available.");
		} else {
			for (LibraryBook book : books) {
				System.out.println(book);
			}
		}
	}

	private void listBooksByStatus() {
		System.out.println("Enter book status (AVAILABLE, ON_LOAN, WITHDRAWN): ");
		String statusStr = scanner.nextLine().toUpperCase();
		try {
			BookStatus status = BookStatus.valueOf(statusStr);
			LibraryBook[] books = library.list(status);
			if (books.length == 0) {
				System.out.println("No books found with status: " + status);
			} else {
				for (LibraryBook book : books) {
					System.out.println(book);
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid status entered.");
		}
	}

	private void addBook() {
		System.out.println("Enter book title: ");
		String title = scanner.nextLine();

		System.out.println("Enter author name: ");
		String author = scanner.nextLine();

		System.out.println("Enter ISBN (10 digits): ");
		String isbn = scanner.nextLine();

		BookType type = null;
		while (type == null) {
			System.out.println("Enter book type (FICTION, NONFICTION, REFERENCE, UNKNOWN): ");
			String typeStr = scanner.nextLine().toUpperCase();
			try {
				type = BookType.valueOf(typeStr);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid book type entered. Please enter FICTION, NONFICTION, REFERENCE, or UNKNOWN."); // chatGPT
																												// was
																												// used
																												// to
																												// demonstrate
																												// how
																												// to
																												// add
																												// this.
																												// i
																												// used
																												// a
																												// similar
																												// method
																												// for
																												// edition,
																												// price
																												// etc.
			}
		}

		int edition = 0;
		while (true) {
			System.out.println("Enter edition number: ");
			if (scanner.hasNextInt()) {
				edition = scanner.nextInt();
				scanner.nextLine();
				if (edition > 0)
					break;
				System.out.println("Edition must be a positive number.");
			} else {
				System.out.println("Invalid edition number. Please enter a valid integer.");
				scanner.nextLine();
			}
		}

		System.out.println("Enter book summary (20-150 characters): ");
		String summary = scanner.nextLine();

		double price = 0.0;
		while (true) {
			System.out.println("Enter price (£): ");
			if (scanner.hasNextDouble()) {
				price = scanner.nextDouble();
				scanner.nextLine();
				if (price >= 0)
					break;
				System.out.println("Price cannot be negative.");
			} else {
				System.out.println("Invalid price. Please enter a valid number.");
				scanner.nextLine();
			}
		}

		System.out.println("Enter cover image filename (or press Enter to skip): ");
		String image = scanner.nextLine();
		if (image.isEmpty())
			image = null;

		library.add(new LibraryBook(title, author, isbn, type, summary, edition, price, image));
		System.out.println("Book added successfully!");
	}

	private void removeBook() {
		System.out.println("Enter book ID to remove: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		LibraryBook book = library.search(id);
		if (book != null && book.getStatus() != BookStatus.ON_LOAN) {
			library.remove(id);
			System.out.println("Book removed successfully.");
		} else {
			System.out.println("Book not found or is currently on loan.");
		}
	}

	private void borrowBook() {
		System.out.println("Enter book ID to borrow: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		if (library.borrowBook(id)) {
			System.out.println("Book borrowed successfully!");
		} else {
			System.out.println("Book is unavailable or does not exist.");
		}
	}

	private void returnBook() {
		System.out.println("Enter book ID to return: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		if (library.returnBook(id)) {
			System.out.println("Book returned successfully!");
		} else {
			System.out.println("Book not found or not on loan.");
		}
	}

	private void displayRankedList() {
		LibraryBook[] rankedBooks = library.mostPopular();
		if (rankedBooks.length == 0) {
			System.out.println("No books available.");
		} else {
			for (LibraryBook book : rankedBooks) {
				System.out.println(
						book.getTitle() + " - " + book.getAuthor() + " (Borrowed " + book.getLoanCount() + " times)");
			}
		}
	}

	public static void main(String[] args) {
		QUBLibrary app = new QUBLibrary();
		app.run();
	}
}