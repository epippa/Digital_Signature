import java.security.NoSuchAlgorithmException;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String charset = "abcdef0123456789";
        int pwdLength = 4;
        int chainLength = 50;
        int numChains = 2000;
        String hashAlgorithm = "MD5";

        RainbowTable rainbow = new RainbowTable(charset, pwdLength, chainLength, numChains, hashAlgorithm);

        System.out.println("Generating Rainbow Table...");
        rainbow.generateTable();

        // Test 1
        String hash1 = "eda95e1f923c19c1660d75a8abc76f5f"; // Expected: 7ba5
        String result1 = rainbow.lookupTable(hash1);
        System.out.println("Decryption result for hash1: " + result1);

        // Test 2
        String hash2 = "550ca021b146568558b503248a69a6c7"; // Expected: 464c
        String result2 = rainbow.lookupTable(hash2);
        System.out.println("Decryption result for hash2: " + result2);
    }
}
