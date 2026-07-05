package Library.DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Library.model.Book;
import Library.model.Member;
import Library.model.Transaction;

public class DataBase {
	
	public static Map<String,Book> books = new HashMap<>();
	public static Map<String,Member> members = new HashMap<>();
	public static List<Transaction> transactions = new ArrayList<>();
	public static final int MAX_BOOKS_BORROWED_LIMIT_PER_STUDENT = 3;
	public static final int MAX_BOOKS_BORROWED_LIMIT_PER_FACULTY = 5;
	public static final int MAX_ISSUE_DAYS_FOR_STUDENT = 5;
	public static final int MAX_ISSUE_DAYS_FOR_FACULTY = 10;
	public static final int DAY_FINE_FOR_FACULTY = 5;
	public static final int DAY_FINE_FOR_STUDENT = 10;

}

/*
why static is used?
database class is not creating object in the BookServiceImpl class. Below is example of writing
DataBase db = new DataBase();
db.books.put(book.getTitle(), book);
so,
without creating DataBase class object we use static here and we are writing like
DataBase.books.put(book.getTitle(),book);
*/