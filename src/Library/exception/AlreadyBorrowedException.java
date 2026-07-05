package Library.exception;

public class AlreadyBorrowedException extends RuntimeException{

	public AlreadyBorrowedException(String message) {
		super(message);
	}
}
