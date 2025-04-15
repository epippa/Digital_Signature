import java.security.NoSuchAlgorithmException;

public class DigitalSignature {
	public SignedMessage sign(String message, String algorithm, int d, int n) throws NoSuchAlgorithmException {
		SignedMessage sm = new SignedMessage();
		sm.setMessage(message);
		// create digital signature and add it to the SignedMessage
		Hash h = new Hash();
		String digest = h.getDigest(message, algorithm);
		RSA rsa = new RSA();
		int[] ds = rsa.encrypt(digest, d, n);
		sm.setSignature(ds);
		return sm;
	}
	
	public boolean verifySignature(SignedMessage signedMessage, String algorithm, int e, int n) throws NoSuchAlgorithmException {
		String message = signedMessage.getMessage();
		// get the digital signature and check its validity. Return true if the signature is valid, false otherwise
		int[] digitSign = signedMessage.getSignature();
		RSA rsa = new RSA();
		String digest = rsa.decrypt(digitSign, e, n);
		Hash h = new Hash();
		String digest2 = h.getDigest(message, algorithm);
		if (digest.equals(digest2)) return true;
		return false;
	}
}
