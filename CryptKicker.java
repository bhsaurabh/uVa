import java.util.*;

public class Main implements Runnable {
	private Scanner sc;

	private class Crypt implements Runnable {
		private int N;	// number of words in dictionary
		private String[] dict;	// dictionary of legible words
		private String[] input;	// encrypted text
		private Hashtable<Character, Character> substitution;	// characer substitution dictionary <encryped:decrypted>

		public Crypt(int N) {
			this.N = N;
			this.dict = new String[N];
		}

		/* Read words from STDIN into dicionary */
		private void getDictionary() {
			for (int i = 0; i < N; i++)
				dict[i] = sc.nextLine();	// hopefully lower case
		}

		/* Useful for debugging */
		private void showDictionary() {
			for (int i = 0; i < N; i++)
				System.out.print(dict[i] + " ");
			System.out.println();	// goto new line after showing dictionary
		}

		/* Read Encrypted text */
		private void getInput() {
			String inputText = sc.nextLine();
			for (char c : inputText.toCharArray())
				substitution.put(c, null);
			input = inputText.split("\\s+");	// split on space
		}

		/* Adds word to substitution dictionary */
		private void addCharsToDict(String encrypted, String decrypted) {
			char[] decryptedWord = decrypted.toCharArray();
			char[] encryptedWord = encrypted.toCharArray();
			for (int i = 0; i < encryptedWord.length; i++)
				substitution.put(encryptedWord[i], decryptedWord[i]);
		}

		/* Make guesses */


		/* Abstract method to be overriddent */
		public void run() {}

	}


	public Main() {
		this.sc = new Scanner(System.in);
	}

	/* Execute required methods in correct order */
	public void run() {

	}

	public static void main(String[] args) {
		Main obj = new Main();
		obj.run();
	}
}