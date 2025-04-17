import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.security.*;

public class RainbowTable {

	private String charset; 
	private int pwdLength;
	private int chainLength;
	private int numChains;
	private HashMap<String, String>  table;
	private String hashAlgorithm, salt;

	public RainbowTable(String charset, int pwdLength, int chainLength, int numChains, String hashAlgorithm, String salt) {
		this.charset = charset;
		this.pwdLength = pwdLength;
		this.chainLength = chainLength;
		this.numChains = numChains;
		this.hashAlgorithm = hashAlgorithm;
		this.salt = salt;
	}

	public void generateTable() throws NoSuchAlgorithmException {
		table = new HashMap<String,String>();
		String start, end;
		while (table.size()<numChains) {
			start = generateRandomPassword(pwdLength);	//generiamo una combinazione (password) random di 4 caratteri
			end = generateChain(start);		//generiamo la catena -> Hash e Reduction
			if (!table.containsKey(end)){
				table.put(end, start);
				System.out.println("Chain no: "+ table.size());	
			}
		}
	}
	
	private String reduce(String digest, int pos) {
		int temp = pos % (digest.length() + 1 - pwdLength);
		String pwd = digest.substring(temp,temp + pwdLength);
		return pwd;
	}

	private String generateChain(String start) throws NoSuchAlgorithmException
	{
		String pwd = start;
		for (int i = 0; i < chainLength; i++){
			String hash = getDigest(pwd);
			reduce(hash, i);
		}
		return pwd;
	}


	private String getDigest(String inputString) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		byte[] inputBytes = inputString.getBytes(StandardCharsets.UTF_8); //Garantisce compatibilità cross-platform
		byte[] bytes = md.digest((inputBytes+salt).getBytes()); 	//    H(p1*s*)  R1  H(p2*s*)
														//	p1 ------>  h1  ------>  p2 ....	(P1 deve diventare H1, ecc.)
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < bytes.length; i++) {
			sb.append(String.format("%02x", bytes[i] & 0xFF));
		}
		return sb.toString();
	}

	private String generateRandomPassword(int passwordLength){
		StringBuilder builder = new StringBuilder(passwordLength);
		for (int i = 0; i < passwordLength; i++){
			builder.append(charset.charAt((int) (Math.random() * charset.length())));
		}
		return builder.toString();
	}

	public String lookupTable(String hashToFind) throws NoSuchAlgorithmException{
		String digest = null;
		String pwd = null;
		for (int i = chainLength - 1; i >= 0; i--)
		{
			digest = hashToFind;
			for (int j = i; j < chainLength; j++)
			{
				//reduce.. private String reduce(String digest, int pos);
				//hash..   with java.security
			}
			if (table.containsKey(pwd)){
				lookupChain(table.get(pwd), hashToFind);
				//if the hash can be decrypted return otherwise continue
			}
		}
		return "Sorry, I couldn't decrypt this digest :-(";
	}
	
	private String lookupChain(String start, String hashToFind) throws NoSuchAlgorithmException{
		String pwd = start;
		for (int i = 0; i < chainLength; i++){
			String digest = getDigest(pwd);
			if(digest.equals(hashToFind)) {
                //if the hash has been found return otherwise continue
				return pwd;
			}
			//reduce.. private String reduce(String digest, int pos);
			pwd = reduce(digest, i);
			}
		return null;
		
	}
}
