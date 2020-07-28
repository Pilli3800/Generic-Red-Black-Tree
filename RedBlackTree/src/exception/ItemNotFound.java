package exception;

@SuppressWarnings("serial")
public class ItemNotFound extends Exception{

	public ItemNotFound(String message) {
		super(message);
	}

	public ItemNotFound() {
		super();
	}
}
