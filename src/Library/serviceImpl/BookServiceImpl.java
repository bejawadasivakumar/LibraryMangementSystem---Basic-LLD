package Library.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import Library.DataBase.DataBase;
import Library.exception.BookNotFoundException;
import Library.model.Book;
import Library.service.BookService;

public class BookServiceImpl implements BookService{

	@Override
	public void addBook(Book book) {
		DataBase.books.put(book.getTitle(),book);
		System.out.println("Book added successfully...");
	}

	@Override
	public void updateBook(Book book) {
		Book bookDetails = DataBase.books.get(book.getTitle());
		if(bookDetails == null) {
			throw new BookNotFoundException("Book is not found to update...");
		}
		bookDetails.setAuthor(book.getAuthor());
		bookDetails.setAvailableCopies(book.getAvailableCopies());;
		DataBase.books.put(bookDetails.getTitle(), bookDetails);
		System.out.println("Book updated");
	}

	@Override
	public void deleteBook(String title) {
		Book book = DataBase.books.get(title);
		if(book == null) {
			throw new BookNotFoundException("Book is not found to delete..."); 
		}
		DataBase.books.remove(title);
		System.out.println("book deleted successfully...");
	}

	@Override
	public Book searchByTitle(String title) {
		Book book = DataBase.books.get(title);
		if(book == null) {
			throw new BookNotFoundException("Book is not found to search by title..."); 
		} 
		return book;
	}

	@Override
	public List<Book> searchByAuthor(String author) {
		List<Book> books = new ArrayList<>();
		for(Book  book : DataBase.books.values()) {
			if(book.getAuthor().equalsIgnoreCase(author)) {
				books.add(book);
			}
		}
		if(books.isEmpty()) {
		throw new BookNotFoundException("Book is not found to search by author..."); 
		}
		return books;
	}

}
