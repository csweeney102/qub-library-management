/**
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Function: This class represents a library system that manages a collection of books. It provides methods to add, remove, search, borrow, return and list books.
 */

package part01;

public class Library {
	private LibraryBook[] books;
	private int bookCount;
	private static final int MAX_BOOKS = 100;

	public Library() {
		this.books = new LibraryBook[MAX_BOOKS];
		this.bookCount = 0;
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
		LibraryBook[] result = new LibraryBook[bookCount];
		for (int i = 0; i < bookCount; i++) {
			result[i] = books[i];
		}
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