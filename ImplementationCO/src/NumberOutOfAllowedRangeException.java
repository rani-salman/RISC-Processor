
@SuppressWarnings("serial")
public class NumberOutOfAllowedRangeException extends Exception {
	private String message = "Number is out of the allowed range.";
	
	public NumberOutOfAllowedRangeException() {
		super();
	}
	public NumberOutOfAllowedRangeException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
