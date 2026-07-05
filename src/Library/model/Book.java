package Library.model;

public class Book {
	
	private String title;
	private String author;
	private BookCategory category;
	private BookStatus status;
	private int availableCopies;
	public Book(String title, String author, BookCategory category, BookStatus status, int availableCopies) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.availableCopies = availableCopies;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public BookCategory getCategory() {
		return category;
	}
	public void setCategory(BookCategory category) {
		this.category = category;
	}
	public BookStatus getStatus() {
		return status;
	}
	public void setStatus(BookStatus status) {
		this.status = status;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", category=" + category + ", status=" + status
				+ ", availableCopies=" + availableCopies + "]";
	}
}
