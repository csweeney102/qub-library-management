/*
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Description:This class extends the book class, and outputs information about a book to the console.
 */
package part01;

public class LibraryBook extends Book implements Lendable{
	private int id;
	private static int nextID = 0; // ChatGPT was used here to assist with bug fixing, where all books would have
									// the same ID
	private BookStatus status;
	private String image;
	private int loanCount;

	// Constructor
	public LibraryBook(String title, String author, String isbn, BookType type, String summary, int edition,
			double price, String coverImage) {
		super(title, author, isbn, type, edition, summary, price);
		this.id = (nextID++);
		// this.nextID = (nextID);
		this.status = BookStatus.AVAILABLE;
		; // Need to find out how to fix books, right now no book can be borrowed or
			// returned - update problem was fixed by using chatgpt, changed this.status =
			// status; to what you see here
		this.image = coverImage;
		this.loanCount = 0;
	}

	// Getters
	public int getID() {
		return id;
	}

	public int getNextID() {
		return nextID;
	}

	public BookStatus getStatus() {
		return status;
	}

	public String getImage() {
		return image;
	}

	public int getLoanCount() {
		return loanCount;
	}

// Setters
	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public void setLoanCount(int i) {
		this.loanCount = i; // This was changed by chatgpt from this.loancount = loancount;
	}

	public String toString() {
		return "LibraryBook[ID=" + id + ", Title: " + getTitle() + ", Author: " + getAuthor() + ", ISBN: " + getIsbn()
				+ ", Type: " + getType() + ", Edition: " + getEdition() + ", Summary: " + getSummary() + ", Status: "
				+ status + ", Loan Count: " + loanCount + ", Image: " + image + "]";
	}

	@Override
	public boolean checkout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkin() {
		// TODO Auto-generated method stub
		return false;
	}

}
