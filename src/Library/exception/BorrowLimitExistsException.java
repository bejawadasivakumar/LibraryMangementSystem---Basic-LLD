package Library.exception;

public class BorrowLimitExistsException extends RuntimeException{

	public BorrowLimitExistsException(String message) {
		super(message);
	}
}
