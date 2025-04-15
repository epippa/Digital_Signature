//Linear Congruential Generator                     @Emanuele_Pippa_St.Nr.20009
public class LCG {

    /* 
    *  Xn+1 = (a * Xn + c) mod m
    *  num: numero elementi da generare
    *  seed: valore iniziale X0.
    *  multiplier: a
	*  increment: c
    *  mod (m): Modulo > 1
    */
    public static int[] lcg(int num, int seed, int multiplier, int increment, int mod) {

        int[] randomNums = new int[num];
        randomNums[0] = seed;
        for (int i = 1; i < num; i++) {
            int next = (randomNums[i - 1] * multiplier + increment) % mod;
            
            if (next < 0) {
                next += mod; // in caso di valori negativi
            }
            randomNums[i] = next;
        }
        return randomNums;
    }

    public static void main(String[] args) {
        int[] sequence = lcg(17, 0, 5, 4, 16);
        for (int num : sequence) {
            System.out.print(num + " ");
        }
    }
}