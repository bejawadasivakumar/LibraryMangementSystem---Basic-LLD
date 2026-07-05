package Library.service;

import Library.model.Transaction;

public interface BorrowAndReturnService {

	void borrowBook(String userName, String bookTitle);
	void returnBook(String userName, String bookTitle);
}
