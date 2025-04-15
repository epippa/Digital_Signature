import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class Hash {

    public String getDigest(String inputString, String hashAlgorithm) throws NoSuchAlgorithmException {
        
        MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
        byte[] inputBytes = inputString.getBytes(StandardCharsets.UTF_8); //Garantisce compatibilità cross-platform
        byte[] bytes = md.digest(inputBytes);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02x", bytes[i] & 0xFF));
        }
        return sb.toString();
    }
}