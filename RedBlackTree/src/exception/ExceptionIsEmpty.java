package exception;

@SuppressWarnings("serial")
public class ExceptionIsEmpty extends Exception {

	public ExceptionIsEmpty(String message) {
		super(message);
	}

	public ExceptionIsEmpty() {
		super();
	}
}
