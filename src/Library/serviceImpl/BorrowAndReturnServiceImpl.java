package Library.serviceImpl;

import java.time.LocalDate;

import Library.DataBase.DataBase;
import Library.exception.AlreadyBorrowedException;
import Library.exception.BookNotAvailableException;
import Library.exception.BookNotFoundException;
import Library.exception.BorrowLimitExistsException;
import Library.exception.MemberNotFoundException;
import Library.model.Book;
import Library.model.BookStatus;
import Library.model.Member;
import Library.model.SpecificMember;
import Library.model.StudentMember;
import Library.model.Transaction;
import Library.model.TransactionStatus;
import Library.service.BorrowAndReturnService;
import Library.service.MemberRuleService;

public class BorrowAndReturnServiceImpl implements BorrowAndReturnService{
	int i = 0;

	@Override
	public void borrowBook(String userName, String bookTitle) {
		
		Book book = DataBase.books.get(bookTitle);
		Member member = DataBase.members.get(userName);
		
		// 1. Book validation
		if(book == null) {
			throw new BookNotFoundException("book not found");
		}
		
		// 2. Member validation
		if(member == null) {
			throw new MemberNotFoundException("Member not found");
		}
		
		// 3. Book availability
		if(book.getAvailableCopies() <= 0) {
			throw new BookNotAvailableException("Book not available to borrow...");
		}
		
		// 4. Check if already borrowed the same book
		for (Transaction transaction : DataBase.transactions) {

		    if (transaction.getMember().getName().equalsIgnoreCase(userName)
		            && transaction.getBook().getTitle().equalsIgnoreCase(bookTitle)
		            && transaction.getStatus() == TransactionStatus.BORROWED) {

		        throw new AlreadyBorrowedException(
		                "You have already borrowed this book.");
		    }
		}
		
		 // 5. Count active borrowed books
		int borrowedBooks = 0;
		for(Transaction transaction : DataBase.transactions) {
			if(transaction.getMember().getName().equalsIgnoreCase(userName) && transaction.getStatus() == TransactionStatus.BORROWED) {
				borrowedBooks++;
			}
		}
		
		// 6. Borrow limit validation
		MemberRuleService rule;
		if(member instanceof StudentMember) {
			rule = new StudentRuleServiceImpl();
		}
		else {
			rule = new FacultyRuleServiceImpl();
		}
		int limit = rule.maxBorrowLimit();
		
//		if(member.getMember() == SpecificMember.STUDENT && borrowedBooks >= DataBase.MAX_BOOKS_BORROWED_LIMIT_PER_STUDENT) {
//			throw new BorrowLimitExistsException("Student borrow limit reached.");
//		}
//		if(member.getMember() == SpecificMember.FACULTY && borrowedBooks >= DataBase.MAX_BOOKS_BORROWED_LIMIT_PER_FACULTY) {
//			throw new BorrowLimitExistsException("Faculty borrow limit reached.");
//		}
		if(borrowedBooks >= limit) {
			throw new BorrowLimitExistsException("borrow limit reached.");
		}
		
		// 7. Borrow date
		LocalDate borrowDate = LocalDate.now();
		LocalDate dueDate;
		if(member.getMember() == SpecificMember.STUDENT) {
			dueDate = borrowDate.plusDays(DataBase.MAX_ISSUE_DAYS_FOR_STUDENT);
		}
		else {
			dueDate = borrowDate.plusDays(DataBase.MAX_ISSUE_DAYS_FOR_FACULTY);
		}
		
		// 8. Create Transaction
		Transaction transaction = new Transaction();
		transaction.setTransactionId(i++);
		transaction.setMember(member);
		transaction.setBook(book);
		transaction.setBorrowDate(borrowDate);
		transaction.setDueDate(dueDate);
		transaction.setReturnDate(null);
		transaction.setStatus(TransactionStatus.BORROWED);
		transaction.setFine(0);
		
		// 9. Save transaction
		DataBase.transactions.add(transaction);
		
		//10. Update available copies
		book.setAvailableCopies(book.getAvailableCopies() - 1);
		if(book.getAvailableCopies() == 0) {
			book.setStatus(BookStatus.NOT_AVAILABLE);
		}
	
		System.out.println("Book Borrowed Successfully");
		System.out.println();
		System.out.println("========== BORROW SUCCESS ==========");
		System.out.println("Transaction Id : " + transaction.getTransactionId());
		System.out.println("Member         : " + userName);
		System.out.println("Book           : " + bookTitle);
		System.out.println("Borrow Date    : " + borrowDate);
		System.out.println("Due Date       : " + dueDate);
		System.out.println("Status         : " + transaction.getStatus());
		System.out.println("====================================");
	}

	@Override
	public void returnBook(String userName, String bookTitle) {
		
		Book book = DataBase.books.get(bookTitle);
		Member member = DataBase.members.get(userName);
		
		// 1. Book validation
		if(book == null) {
			throw new BookNotFoundException("book not found");
		}
		
		// 2. Member validation
		if(member == null) {
			throw new MemberNotFoundException("Member not found");
		}
		
		Transaction transaction = null;
		for (Transaction t : DataBase.transactions) {

		    if (t.getMember().getName().equalsIgnoreCase(userName)
		            && t.getBook().getTitle().equalsIgnoreCase(bookTitle)
		            && t.getStatus() == TransactionStatus.BORROWED) {

		        transaction = t;
		        break;
		    }

		}
		if (transaction == null) {
	        throw new IllegalArgumentException("This book is not currently borrowed.");
	    }
		
		LocalDate returnDate = LocalDate.now();
		transaction.setReturnDate(returnDate);
		transaction.setStatus(TransactionStatus.RETURNED);
		
		//calculate fine
		double fine = 0;
		int extraDays = 0;
		MemberRuleService rule;
		if(member instanceof StudentMember) {
			rule = new StudentRuleServiceImpl();
		}
		else {
			rule = new FacultyRuleServiceImpl();
		}
		
		if(returnDate.isAfter(transaction.getDueDate())) {
			extraDays = returnDate.getDayOfYear() - transaction.getDueDate().getDayOfYear();
		}
		double fineCharges = rule.calulateFine(extraDays);
		
//		if(transaction.getMember().getMember() == SpecificMember.STUDENT) {
//			fine = extraDays * DataBase.DAY_FINE_FOR_STUDENT;
//		}
//		else {
//			fine = extraDays * DataBase.DAY_FINE_FOR_FACULTY;
//		}
//		transaction.setFine(fine);
		transaction.setFine(fineCharges);
		
		// Increase available copies
	    book.setAvailableCopies(book.getAvailableCopies() + 1);
	    if(book.getAvailableCopies() > 0) {
	    	book.setStatus(BookStatus.AVAILABLE);
	    }
	   
	    System.out.println("Book Returned Successfully");
	    System.out.println();
	    System.out.println("========== RETURN SUCCESS ==========");
	    System.out.println("Transaction Id: " + transaction.getTransactionId());
	    System.out.println("Member        : " + userName);
	    System.out.println("Book          : " + bookTitle);
	    System.out.println("Return Date   : " + returnDate);
	    System.out.println("Borrow Date    : " + transaction.getBorrowDate());
		System.out.println("Due Date       : " + transaction.getDueDate());
		System.out.println("Status         : " + transaction.getStatus());
	    System.out.println("Fine          : ₹" + fine);
	    System.out.println("====================================");
	}
	
}
