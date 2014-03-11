import java.util.Scanner;
/* TODO: Make this more space efficient. Can boolean array be replaced by a bit-vector? */
public class Main implements Runnable{
	/* Application enry point */
	private Scanner sc;

	public Main() {
		sc = new Scanner(System.in);
	}

	private class Sequence implements Runnable {
		private boolean[] differences;	// differences that are to be stored
		private int num;	// number of elements in sequence
		
		public Sequence(int n) {
			this.num = n;
			differences = new boolean[n];	// will let index represent difference (0 is additional)
			differences[0] = true;
			// By default all booleans would be false
		}

		private void getInput() {
			// just compare 2 side-by-side inputs
			// get the first element
			int prev = sc.nextInt();
			int next;
			for (int i = 1; i < num; i++) {	// start from 1 as 1 input is taken
				next = sc.nextInt();
				int diff = Math.abs(next - prev);
				// make appropriate changes at this location
				if (diff < num)	
					differences[diff] |= true;
				prev = next;	// for next comparison
			}
		}

		private boolean checkJolly() {
			boolean result = true;
			for (int i = 1; i < num; i++)	result &= differences[i];
			return result; 
		}

		private void printJolly(boolean isJolly) {
			if (isJolly)	System.out.println("Jolly");
			else	System.out.println("Not Jolly");
		}

		private void printDifferences() {
			/* Debugging routine: Prints the differences array */
			for (int i = 1; i < num; i++)
				System.out.print(differences[i] + " ");
			System.out.println();
		}

		public void run() {
			// call methods in correct order
			getInput();
			boolean isJolly = checkJolly();
			printJolly(isJolly);
		}

	}

	public static void main(String[] args) {
		// Instantiate and run
		Main obj = new Main();
		obj.run();
	}

	public void run() {
		while (sc.hasNext()) {
			int num = sc.nextInt();
			Sequence seq = new Sequence(num);
			seq.run();
		}
	}
}