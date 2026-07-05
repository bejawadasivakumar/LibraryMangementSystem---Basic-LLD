package Library.model;

import java.time.LocalDate;

public class Transaction {
	
	private int transactionId;
	private Member member;
	private Book book;
	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private TransactionStatus status;
	private double fine;
	
	public Transaction() {
	}
	public Transaction(int transactionId, Member member, Book book, LocalDate borrowDate, LocalDate dueDate,
			LocalDate returnDate, TransactionStatus status, double fine) {
		super();
		this.transactionId = transactionId;
		this.member = member;
		this.book = book;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
		this.fine = fine;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LocalDate getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", member=" + member + ", book=" + book + ", borrowDate="
				+ borrowDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", status=" + status + ", fine="
				+ fine + "]";
	}

}
