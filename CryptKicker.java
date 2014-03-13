import java.util.*;

public class Main implements Runnable {
	private Scanner sc;

	private class Crypt implements Runnable {
		private int N;	// number of words in dictionary
		private String[] dict;	// dictionary of legible words
		private String[] input;	// encrypted text
		private Hashtable<Character, Character> substitution;	// characer substitution dictionary <encryped:decrypted>

		/* Custom comparator to reverse-sort strings based on length */
		private class ReverseStringComparator implements Comparator<String> {
			public int compare(String s1, String s2) {
				return (s1.length() - s2.length()) * (-1);
			}
		}

		/* Constructor */
		public Crypt(int N) {
			this.N = N;
			this.dict = new String[N];
            this.substitution = new Hashtable<Character, Character>();
		}

		/* Read words from STDIN into dicionary */
		private void getDictionary() {
			for (int i = 0; i < N; i++)
				dict[i] = sc.nextLine();	// hopefully lower case
		}

		/* Useful for debugging */
		private void showDictionary() {
            System.out.print("DEBUG: Dictionary is: ");
			for (int i = 0; i < N; i++)
				System.out.print(dict[i] + " ");
			System.out.println();	// goto new line after showing dictionary
		}

		/* Read Encrypted text */
		private void getInput() {
			String inputText = sc.nextLine();
            System.out.println("DEBUG: Your Input is = " + inputText);
            char[] chars = inputText.toCharArray();
            for (char c : chars)
				substitution.put(c, '\u0000');
			input = inputText.split("\\s+");	// split on space
		}

		/* Adds word to substitution dictionary */
		private void addCharsToDict(String encrypted, String decrypted, Hashtable<Character, Character> substitution) {
			char[] decryptedWord = decrypted.toCharArray();
			char[] encryptedWord = encrypted.toCharArray();
			for (int i = 0; i < encryptedWord.length; i++)
				substitution.put(encryptedWord[i], decryptedWord[i]);
		}

		/* Make guesses */
        private boolean recurse(int wordPos, Hashtable<Character, Character> s) {
            String word = input[wordPos];
            System.out.println("DEBUG:" +  word);
            // get list of dictionary words that are possibly this word
            ArrayList<String> possibleWords = new ArrayList<String>();
            int len = word.length();    // avoid recomputations
            for (String w : dict)
                if (w.length() == len)  possibleWords.add(w);
            // Transform word as per subs hashtable
            char[] wordChars = word.toCharArray();
            boolean[] transformedChars = new boolean[wordChars.length]; // keeps track of which positions were transformed
            // TODO: Use bit-vectors for representing above
            for (int i = 0; i < wordChars.length; i++) {
                char replacement = s.get(wordChars[i]);
                if (replacement != '\u0000') {
                    // replace and add record to transformedChars
                    wordChars[i] = replacement;
                    transformedChars[i] = true;
                }
            }
            // now compare to each dictionary word. Add more mappings and recurse
            for (String w : possibleWords) {
                System.out.println("DEBUG: Comparing " + word + " with " + w);
                // use transformedChars to scan appopriate position
                boolean useless = false;
                for (int i = 0; i < transformedChars.length; i++)
                    if (transformedChars[i]) {
                        char dictChar = w.charAt(i);    // character in dictionary word
                        if (dictChar != wordChars[i]) {
                            // useless word
                            System.out.println("\tDEBUG: Defintely not");
                            useless = true;
                            break;
                        }
                    }
                if (useless)    continue;
                System.out.println("\tDEBUG: Could be a valid substitution");
                // maybe this word (w) matched. Lets add it to subs dictionary and recurse
                // create a new dictionary where substitutions will be stored
                Hashtable<Character, Character> subs = new Hashtable<Character, Character>();
                // add existing keys from s into subs
                for (char key : s.keySet()) subs.put(key, s.get(key));
                // add new mappings too!
                for (int i = 0; i < w.length(); i++)
                    subs.put(wordChars[i], w.charAt(i));

                // recurse!
                // TODO: add a stopping condition
                boolean result = recurse(wordPos+1, subs);
                if (result) return result;    
            }
            // TODO: Change this
            return false;
        }

		/* Abstract method to be overriddent */
		public void run() {
            getDictionary();
            //DEBUG: showDictionary();
            while (sc.hasNext())
                getInput();
            //System.out.println("DEBUG: " + input);
			// sort the input based on length
			/*Arrays.sort(input, new ReverseStringComparator());
            System.out.print("DEBUG: ");
            for (int i = 0; i < input.length; i++)
                System.out.print(input[i]);*/
            // take the 1st element (presumably largest) and compare it with dictionary
            boolean result = recurse(0, substitution);// give position of largest word (0 for us as it is reverse-sorted)
            System.out.println(result);
		}
	}

	public Main() {
		this.sc = new Scanner(System.in);
	}

	/* Execute required methods in correct order */
	public void run() {
        int dictLength = sc.nextInt();
        sc.nextLine();
        Crypt solver = new Crypt(dictLength);
        solver.run();
	}

	public static void main(String[] args) {
		Main obj = new Main();
		obj.run();
	}
}
