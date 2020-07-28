package exception;

@SuppressWarnings("serial")
public class ItemDuplicated extends Exception {

	public ItemDuplicated(String message) {
		super(message);
	}

	public ItemDuplicated() {
		super();
	}
}
