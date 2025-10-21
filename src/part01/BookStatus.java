/**
 * Author: Cathaoir Sweeney
 * Student Number: 40407247
 * Function: This enumerator gives the books 4 different lending statuses - available, on loan, withdrawn and unknown. 
 */

package part01;

public enum BookStatus {
	AVAILABLE("Available"), ON_LOAN("On loan"), WITHDRAWN("Withdrawn"), UNKNOWN("Unknown");

	private String status;

	BookStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return status;
	}
}
