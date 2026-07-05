package Library;

import java.util.List;
import java.util.Scanner;

import Library.exception.AlreadyBorrowedException;
import Library.exception.BookNotAvailableException;
import Library.exception.BookNotFoundException;
import Library.exception.BorrowLimitExistsException;
import Library.exception.MemberAlreadyExistsException;
import Library.exception.MemberNotFoundException;
import Library.model.Book;
import Library.model.BookCategory;
import Library.model.BookStatus;
import Library.model.FacultyMember;
import Library.model.Member;
import Library.model.SpecificMember;
import Library.model.StudentMember;
import Library.model.Transaction;
import Library.service.BookService;
import Library.service.BorrowAndReturnService;
import Library.service.MemberManagementService;
import Library.serviceImpl.BookServiceImpl;
import Library.serviceImpl.BorrowAndReturnServiceImpl;
import Library.serviceImpl.MemberManagementServiceImpl;

public class LibraryApplication {
	static Scanner sc = new Scanner(System.in);
	static BookService bookService = new BookServiceImpl();
	static MemberManagementService memberManagementService = new MemberManagementServiceImpl();
	static BorrowAndReturnService borrowAndReturnService = new BorrowAndReturnServiceImpl();
	
	public static void main(String[] args) {

		while(true) {
		System.out.println("...LIBRARY MANAGEMENT SYSTEM...");
		System.out.println("1.Admin");
		System.out.println("2.Member");
		System.out.println("3.exist");
		int choice = sc.nextInt();
		try {
		switch(choice) {
		case 1 : adminMenu();
		         break;
		case 2 : memberMenu();
		         break;
		case 3 : System.out.println("Thank you");
		         System.exit(0);
		default : System.out.println("Invalid Choice");
		}
		}
		 catch(AlreadyBorrowedException |
	                BookNotFoundException |
	                BookNotAvailableException |
	                BorrowLimitExistsException |
	                IllegalArgumentException |
	                MemberAlreadyExistsException |
	                MemberNotFoundException e) {
	        	System.out.println(e.getMessage());
	        }
		}
	}
	private static void adminMenu() {
		while(true) {
			System.out.println("====ADMIN====");
			
			System.out.println("1.Add Book");
	        System.out.println("2.Update Book");
	        System.out.println("3.Delete Book");
	        System.out.println("4.Search Book By Title");
	        System.out.println("5.Search Book By Author");
	        System.out.println("6.Add Member");
	        System.out.println("7.Update Member");
	        System.out.println("8.Delete Member");
	        System.out.println("9.List Members");

	        System.out.println("10.Back");
	        System.out.println("Enter Choice:");
	        int choice = sc.nextInt();
	        
	        switch(choice){

            case 1:
            	addBook();
                break;

            case 2:
                updateBook();
                break;

            case 3:
                deleteBook();
                break;

            case 4:
                System.out.println(searchBookByTitle());
                break;

            case 5:
                System.out.println(searchBookByAuthor());
                break;

            case 6:
                addMember();
                break;

            case 7:
                updateMember();
                break;

            case 8:
                deleteMember();
                break;

            case 9:
                System.out.println(showMembers());
                break;

            case 10:
                return;

            default:
                System.out.println("Invalid");
        }
		}
	}
	private static void memberMenu() {
		while (true) {

            System.out.println("\n========== MEMBER ==========");
            System.out.println("1.Borrow Book");
            System.out.println("2.Return Book");
            System.out.println("3.Back");
            System.out.println("Enter Choice:");
            int choice = sc.nextInt();
            switch(choice) {
            
            case 1: 
            	borrowBook();
                break;
                       
            case 2: 
            	returnBook();
                break;
                
            case 3: 
            	return;
            	
            default: 
            	System.out.println("Invalid");
            
            }
		}
	}
	private static void addBook() {
		sc.nextLine();
		System.out.println("Book Title :");
		String title = sc.nextLine();
		System.out.println("Book Author :");
		String author = sc.nextLine();
		System.out.println("Book Categories:");
		for (BookCategory category : BookCategory.values()) {
            System.out.println(category.ordinal() + 1 + "." + category);
        }

        int choice = sc.nextInt();
        BookCategory category = BookCategory.values()[choice - 1];
        System.out.println("Total available copies :");
        int availableCopies = sc.nextInt();
        Book book = new Book(title,author,category,BookStatus.AVAILABLE,availableCopies);
        bookService.addBook(book);
	}
	
	private static void updateBook() {
		sc.nextLine();
		System.out.println("Book Title :");
		String title = sc.nextLine();
		System.out.println("Book Author :");
		String author = sc.nextLine();
		System.out.println("Book Categories:");
		for (BookCategory category : BookCategory.values()) {
            System.out.println(category.ordinal() + 1 + "." + category);
        }

        int choice = sc.nextInt();
        BookCategory category = BookCategory.values()[choice - 1];
        System.out.println("Total available copies :");
        int availableCopies = sc.nextInt();
        Book book = new Book(title,author,category,BookStatus.AVAILABLE,availableCopies);
        bookService.updateBook(book);
	}
	private static void deleteBook() {
		sc.nextLine();
		System.out.println("Book Title : ");
		String title = sc.nextLine();
		bookService.deleteBook(title);
	}
	private static Book searchBookByTitle() {
		sc.nextLine();
		System.out.println("Book Title to Search:");
		String title = sc.nextLine();
		return bookService.searchByTitle(title);
	}
	private static List<Book> searchBookByAuthor(){
		sc.nextLine();
		System.out.println("Book author to search:");
		String author = sc.nextLine();
		return bookService.searchByAuthor(author);
	}
	public static void addMember() {
		sc.nextLine();
		System.out.println("Member name:");
		String name = sc.nextLine();
		System.out.println("Member email:");
		String email = sc.nextLine();
		System.out.println("Member Type:");
		for(SpecificMember member : SpecificMember.values()) {
			System.out.println(member.ordinal() + 1 + "." + member);
		}
		int choice = sc.nextInt();
		SpecificMember member = SpecificMember.values()[choice - 1];
		Member person;
		if(member == SpecificMember.STUDENT) {
		     person = new StudentMember(name,email,member);
		}
		else {
			 person = new FacultyMember(name,email,member);
		}
		memberManagementService.addMember(person);
	}
	public static void updateMember() {
		sc.nextLine();
		System.out.println("Member name:");
		String name = sc.nextLine();
		System.out.println("Member email:");
		String email = sc.nextLine();
		System.out.println("Member Type:");
		for(SpecificMember member : SpecificMember.values()) {
			System.out.println(member.ordinal() + 1 + "." + member);
		}
		int choice = sc.nextInt();
		SpecificMember member = SpecificMember.values()[choice - 1];
		Member person;
		if(member == SpecificMember.STUDENT) {
		     person = new StudentMember(name,email,member);
		}
		else {
			 person = new FacultyMember(name,email,member);
		}
		memberManagementService.updateMember(person);
	}
	public static void deleteMember() {
		sc.nextLine();
		System.out.println("Enter Member name:");
		String name = sc.nextLine();
		memberManagementService.deleteMember(name);
	}
	public static List<Member> showMembers(){
		return memberManagementService.getMembers();
	}
	private static void borrowBook() {
		sc.nextLine();
		System.out.println("Book title:");
		String title = sc.nextLine();
		System.out.println("Member name:");
		String name = sc.nextLine();
		borrowAndReturnService.borrowBook(name, title);
	}
	private static void returnBook() {
		sc.nextLine();
		System.out.println("Book title");
		String title = sc.nextLine();
		System.out.println("Member name");
		String name = sc.nextLine();
	    borrowAndReturnService.returnBook(name, title);
	}
}
