public class Test3 {

	public static void main(String[] args) {
		RSA rsa = new RSA();
		RSAKeys keys = rsa.generateKeys(17,23);
		System.out.println(keys.getD()+"  "+keys.getE()+"   "+keys.getN());
	}

}
