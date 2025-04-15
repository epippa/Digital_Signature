import java.security.NoSuchAlgorithmException;

public class Test5 {
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
		DigitalSignature ds = new DigitalSignature();
		SignedMessage sm = ds.sign("Hello best!", "SHA-256", 235, 391);
		sm.setMessage("Hello best!");
		boolean check = ds.verifySignature(sm,"SHA-256", 3, 391);
		System.out.println(check);
	}

}
