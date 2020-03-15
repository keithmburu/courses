import java.util.ArrayList;
public class PrimeGenerator implements Runnable {
    int start, end;
    ArrayList<Integer> primes;
    public PrimeGenerator(int start, int end, ArrayList<Integer> primes) {

    }

    private boolean isPrime(int num) {
	if(num == 1) { return true;}
	if(num == 2) { return true;}
	if(num % 2 == 0) { return false;}
	for(int i = 3; i < num; i++) {
	    if(num % i == 0) {
		return false;
	    }
	}
	return true;
    }

    public static void main(String[] args) {

    }

}
