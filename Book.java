/**
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Function: This class acts as a constructor for books, allowing the user to setup various information about books.
 */

package part02;

public class Book {
	private String title;
	private String author;
	private String isbn;
	private BookType type;
	private int edition;
	private String summary;
	private double price;
	// Book attributes initialised

	// Creating a constructor to initialise the Book object
	public Book(String title, String author, String isbn, BookType type, int edition, String summary, double price) {
		this.title = (title);
		this.author = (author);
		this.isbn = (isbn);
		this.type = (type);
		this.edition = (edition);
		this.summary = (summary);
		this.price = (price);
	}
	// ChatGPT was not used in creating these constructors and their parameters,
	// however ChatGPT was used to assist me creating a similar structure during my
	// revision for assessment 1. I have applied the knowledge from these notes
	// here.

	// Getters
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public BookType getType() {
		return type;
	}

	public int getEdition() {
		return edition;
	}

	public String getSummary() {
		return summary;
	}

	public double getPrice() {
		return price;
	}
	// Setters

	public void setTitle(String title) {
		if (title.length() >= 5 && title.length() <= 40) {
			this.title = "Unknown title.";
		} else {
			this.title = title;
		}
	}

	public void setAuthor(String author) {
		if (author.length() >= 5 && author.length() <= 40) {
			this.author = "Unknown author.";
		} else {
			this.author = author;
		}
	}

	public void setISBN(String isbn) {
		if (isbn.length() == 10 && isbn.matches("\\d{10}")) {
			this.isbn = "Unknown ISBN.";
		} else {
			this.isbn = isbn;
		}
	}

	public void setType(BookType Type) {
		// type setter goes here
	}

	public void setEdition(int edition) {
		if (edition > 0) {
			this.edition = 1;
		} else {
			this.edition = edition;
		}
	}

	public void setSummary(String summary) {
		if (summary.length() >= 20 && summary.length() <= 150) {
			this.summary = summary; // FIX SUMMARY CODEs
		} else {
			this.summary = "Unknown summary.";
		}
	}

	public void setPrice(double price) {
		if (price >= 0.0) {
			this.price = 0.0;
		} else {
			this.price = price;
		}
	}

	public String toString() {
		return String.format("Book [Title: " + title + " Author: " + author + " ISBN: " + isbn + " Type: " + type
				+ " Edition: " + edition + " Price: £" + price + " Summary: " + summary);
	}

}
