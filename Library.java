/**
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Function: This class represents a library system that manages a collection of books. It provides methods to add, remove, search, borrow, return and list books.
 */

package part02;

//import java.util.Scanner;

public class Library {
	private LibraryBook[] books;
	private int bookCount;
	private static final int MAX_BOOKS = 100;

	public Library() {
		this.books = new LibraryBook[MAX_BOOKS];
		this.bookCount = 0;
		add(new LibraryBook("Harry Potter and the Philosopher's Stone", "JK Rowling", "1234567890", BookType.FICTION,
				"Harry Potter embarks on a thrilling adventure in the second installment of the renowned series.", 1,
				5.99, "harrypotter.jpg"));
		add(new LibraryBook("Fundamentals of Mathematics for Computing", "Matthew Collins", "0987654321",
				BookType.NONFICTION,
				"Mathematical essentials for computing are explained, including set theory and propositinal logic.", 1,
				19.99, "computingmaths.jpg"));
		add(new LibraryBook("The God Delusion", "Richard Dawkins", "1112233445", BookType.FICTION,
				"Richard Dawkins explains his athiestic views, giving a philosophical insight into the existence of God.",
				2, 30.0, "goddelusion.jpg"));
		add(new LibraryBook("The New Testament", "Unknown", "1225202400", BookType.UNKNOWN,
				"The New Testament tells the story of Jesus Christ, his teachings, crucifixion, and resurrection, along with the early Christian Church’s growth and teachings.",
				7, 9.99, "newtestament.jpeg"));
		add(new LibraryBook("Object-Oriented Programming", "Paul Sage", "1027102956", BookType.NONFICTION,
				"Paul Sage introduces object orientated programming concepts, such as inheritence, encapsulation and alogirthms.",
				3, 47.50, "java.jpg"));
	}

	public boolean add(LibraryBook book) {
		if (book.getPrice() > 0 && bookCount < MAX_BOOKS) {
			books[bookCount++] = book;
			return true;
		}
		return false;
	}

	public boolean remove(int id) {
		for (int i = 0; i < bookCount; i++) {
			if (books[i].getID() == id) {
				books[i] = books[bookCount - 1];
				books[bookCount - 1] = null;
				bookCount--;
				return true;
			}
		}
		return false;
	}

	public LibraryBook search(int id) {
		for (int i = 0; i < bookCount; i++) {
			if (books[i].getID() == id) {
				return books[i];
			}
		}
		return null;
	}

	public boolean borrowBook(int id) {
		LibraryBook book = search(id);
		if (book != null && book.getStatus() == BookStatus.AVAILABLE) {
			book.setStatus(BookStatus.ON_LOAN);
			book.setLoanCount(book.getLoanCount() + 1);
			return true;
		}
		return false;
	}

	public boolean returnBook(int id) {
		LibraryBook book = search(id);
		if (book != null && book.getStatus() == BookStatus.ON_LOAN) {
			book.setStatus(BookStatus.AVAILABLE);
			return true;
		}
		return false;
	}

	public LibraryBook[] list() {
		System.out.println("Debug: bookCount = " + bookCount);

		if (bookCount == 0) {
			System.out.println("Debug: No books available in the system.");
		}

		LibraryBook[] result = new LibraryBook[bookCount];
		for (int i = 0; i < bookCount; i++) {
			result[i] = books[i];
		}

		System.out.println("Debug: Returning " + result.length + " books.");
		return result;
	}

	public LibraryBook[] list(BookStatus status) {
		int count = 0;
		for (int i = 0; i < bookCount; i++) {
			if (books[i].getStatus() == status) {
				count++;
			}
		}

		LibraryBook[] result = new LibraryBook[count];
		int index = 0;
		for (int i = 0; i < bookCount; i++) {
			if (books[i].getStatus() == status) {
				result[index++] = books[i];
			}
		}
		return result;
	}

	public LibraryBook[] mostPopular() {
		LibraryBook[] sortedBooks = new LibraryBook[bookCount];

		for (int i = 0; i < bookCount; i++) {
			sortedBooks[i] = books[i];
		}

		// ChatGPT was used to assist with debugging here
		for (int i = 0; i < bookCount - 1; i++) {
			for (int j = 0; j < bookCount - i - 1; j++) {
				if (sortedBooks[j].getLoanCount() < sortedBooks[j + 1].getLoanCount()) {
					LibraryBook temp = sortedBooks[j];
					sortedBooks[j] = sortedBooks[j + 1];
					sortedBooks[j + 1] = temp;
				}
			}
		}
		return sortedBooks;
	}
}