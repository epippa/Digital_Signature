public class SignedMessage {
	
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int[] getSignature() {
		return signature;
	}
	public void setSignature(int[] signature) {
		this.signature = signature;
	}
	private int[] signature;

}
