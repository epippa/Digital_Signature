import java.math.BigInteger;

public class RSA {
    public int euclidean(int e, int phi) {
        // compute d with the Extended Euclidean algorithm given e and phi as input
        int pi_minus2 = 0, pi_minus1 = 1, quotient = 0, dividend = phi, divisor = e, pi = 0, remainder, d = 0;

        while (divisor != 0) {
            quotient = dividend / divisor;

            // Conserva il vecchio divisore
            remainder = dividend - quotient * divisor; 

            // Scambia dividendo e divisore
            int temp_divisor = divisor;
            divisor = dividend - quotient * divisor;
            dividend = temp_divisor;

            // Calcola coefficienti per inverso modulare
            pi = pi_minus2 - quotient * pi_minus1;
            pi_minus2 = pi_minus1;
            pi_minus1 = pi;

            // Aggiorna d (equivalente a pi_minus2)
            d = pi_minus2;
        }

        // Controllo GCD (se GCD != 1, non esiste l'inverso)
        if (dividend != 1) return 0;
        // Normalizza risultato
        if (d < 0) d += phi;
        return d;
    }

    public RSAKeys generateKeys(){
		RSAKeys keys = new RSAKeys();
	
		//generate two random prime numbers p and q
        int p,q;
        p = generatePrimeNumber();
        q = generatePrimeNumber();
		int n = p*q;
		int phi = (p-1)*(q-1);

		// compute e: the minimum integer that is coprime with phi greater than 1 and lower than phi
        int e;
        for (e=2; e < phi ; e++){
            if (gcd(e, phi) == 1)
            break;        
        }

		return keys;
	}

	public RSAKeys generateKeys(int p, int q){
		RSAKeys keys = new RSAKeys();
	        
		// calculate n = p*q
        int n = p*q;

		// calculate phi = (p-1)*(q-1)
        int phi = (p-1)*(q-1);

		// compute e: the minimum integer that is coprime with phi greater than 1 and lower than phi
        int e;
        for (e=2; e < phi ; e++){
            if (gcd(e, phi) == 1)
            break;        
        }

		// compute d with the Extended Euclidean algorithm
        int d = euclidean(e, phi);
		
        keys.setE(e);
        keys.setD(d);
        keys.setN(n);

		return keys;
	}

	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
    	
	// Linear Congruential Generator
	private int generatePrimeNumber() {
		int num = (int)(System.currentTimeMillis());
		while((num==(int)(System.currentTimeMillis()) || !isPrime(num))){
		    num = ((num * 1)+ 13)%(1000);
		}
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	private boolean isPrime(int inputNum){
		if (inputNum <= 3 || inputNum % 2 == 0)
		return inputNum == 2 || inputNum == 3;
		int divisor = 3;
		while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0))
		divisor += 2;
		return inputNum % divisor != 0;
	}

    public int[] encrypt(String plaintext, int e, int n){
        //Plaintext -> c = m^e mod(n)
		//plaintext -> each character is converted into ascii
        int[] result = new int[plaintext.length()];
        for (int i=0; i<plaintext.length(); i++){
            char character = plaintext.charAt(i);
            int ascii = (int) character;
            BigInteger bigAscii = BigInteger.valueOf(ascii);    
            result[i] = bigAscii.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n)).intValue();
            System.out.println(result[i]);
        }
		//Documentation: https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html#modPow-java.math.BigInteger-java.math.BigInteger-
		return result;
	}

	public String decrypt(int[] ciphertext, int d, int n){
        // Ciphertext -> m = c^d mod(n)
		// for each number in the ciphertext compute ( pow(number, d) ) mod n:  Use for this method modpow of BigInteger. 
        // Documentation: https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html#modPow-java.math.BigInteger-java.math.BigInteger-
		// each resulting number is converted into a character assuming that this number is ascii code of the character
		
        String result="";        

        for (int i=0; i<ciphertext.length; i++){
            int ascii = BigInteger.valueOf(ciphertext[i]).modPow(BigInteger.valueOf(d), BigInteger.valueOf(n)).intValue();
            result += Character.toString ((char) ascii);
        }
        return result;
	}
	
}