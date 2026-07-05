package Library.service;

import java.util.List;

import Library.model.Book;

public interface BookService {
	
	void addBook(Book book);
	void updateBook(Book book);
	void deleteBook(String title);
	Book searchByTitle(String title);
	List<Book> searchByAuthor(String author);
	

}
