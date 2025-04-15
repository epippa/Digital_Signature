import java.security.NoSuchAlgorithmException;

public class Test1 {

	public static void main(String[] args)
	{      
		Hash hash = new Hash();
		String ciphertext;
		try {
			ciphertext = hash.getDigest("security", "SHA-256");
			System.out.println(ciphertext);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
