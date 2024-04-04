import java.time.Instant;

// Find prime numbers using the most efficient algorithm - Sieve of  Eratosthenes
public class PrimeNumberFinder {
	private long limit;
	PrimeNumberFinder(long limit) {
		this.limit = limit;
	}
	public long getLimit () {
		return limit;
	}

	private boolean isOdd (int num) {
		return num % 2 != 0;
	}

	public void findPrimeNumbers () {

		// Start execute time
		long start = Instant.now().getEpochSecond();
		System.out.println("Starting at seconds " + start);

		// By nature we don't consider 1 as a prime number as
		// every number is divisible by 1 and itself.
		// Hence, we start counting from 2.
		boolean[] isPrime = new boolean[ (int) getLimit() + 1];
		isPrime[0] = isPrime[1] = false;
		isPrime[2] = true; // 2 event tho even, 2 is always a prime number. It is the smallest prime number.
		for (int i = 3; i <= getLimit(); i++) if (isOdd(i)) isPrime[i] = true; // Initially mark all the odd numbers starting from 3 till limit as a prime number

		// All the multiples of a prime number should be greater than the prime number itself and also should be divisible by it
		// Those numbers are termed as composite numbers.
		// In this algorithm we will find the composite numbers of a prime number and mark them as non-prime in the given limit
		for (int i = 3; i <= getLimit(); i++) {
			// I will be using Sieve by odd numbers; a sub-variant of the main algo
			// to only operate on odd numbers as by default every even number is non-prime.
			// This will make the algo more efficient.
			if (isPrime[i]  && (long) i*i <= getLimit()) {
				for (int j = i * i; j <= getLimit(); j+=i) isPrime[j] = false;
			}
		}

		// Print all the prime numbers
		for (int i = 2; i <= getLimit(); i++) {
			if (isPrime[i]) System.out.printf("%d ", i);
		}

		// End execute time
		long end = Instant.now().getEpochSecond();
		System.out.println();
		System.out.println("Ending at seconds " + end);

		System.out.println("Execution time in seconds: " + (end - start));
	}
}
