/**
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Function: This enumerator divides books into 4 different types: fiction, nonfiction, reference and unknown.
 */

package part01;

public enum BookType {
	FICTION("Fiction"), NONFICTION("Non-Fiction"), REFERENCE("Reference"), UNKNOWN("Unknown");

	private final String type;

	BookType(String type) {
		this.type = type;
	}

	public String toString() {
		return type;
	}
}
