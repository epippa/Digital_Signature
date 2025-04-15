public class Test4 {
	
	public static void main(String[] args) {
		RSA rsa = new RSA();
		rsa.generateKeys();
		System.out.println(rsa.decrypt(rsa.encrypt("security", 3, 391), 235, 391));
	}
}
