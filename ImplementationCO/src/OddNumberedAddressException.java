
@SuppressWarnings("serial")
public class OddNumberedAddressException extends Exception {
	private String message = "Address must be an even number";
	
	public OddNumberedAddressException() {
		super();
	}
	public OddNumberedAddressException(String message) {
		super(message);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
