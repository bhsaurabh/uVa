/* 3n + 1 problem
 * ID: 100 */
import java.util.Scanner;

public class Main implements Runnable {
	/* For even numbers do n / 2 for odd numbers do (3n+1)... This should converge to 1
	 * Input: series of integer pairs
	 * Output: pair of integers followed by max_cycle_length for i <= n <= j */

	private int getCycleLength(int num) {
		// does 3n+1 calculations to get to 1 eventually and returns cycle length
		int count = 0;	// initialise cycle length to 0
		while (num >= 1) {
			count++;
			if (num == 1)	break;
			else if (num % 2 == 0)	num = num / 2;
			else	num = (3 * num) + 1;
		}
		return count;
	}

	private int getMaxCycle(int i, int j) {
		// find max cycle length in interval [i, j]
		assert i <= j;
		int maxLength = 0;
		for (int k = i; k <= j; k++) {
			int cycleLength = getCycleLength(k);
			if (cycleLength > maxLength)	maxLength = cycleLength;
		}
		return maxLength;
	}

	private void execute() {
		// main code that takes input and calls appropriate functions
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			// use this as the range
			System.out.println(i + " " + j + " " + getMaxCycle(i, j));
		}
	}

	public static void main(String[] args) {
		Main obj = new Main();
		obj.run();
	}

	public void run() {
		execute();
	}
}