import java.util.Scanner;

public class Main implements Runnable {
	private Scanner sc;
	
	/* Constructor */
	public Main() {
		this.sc = new Scanner(System.in);
	}
	
	/* A deck of cards that can be shuffled */
	private class Deck {
		
		private int[] cards;	// 52 cards in correct order
		private int[][] shuffle;	// stores the shuffle array
		private int n;	// number of possible shuffles
		
		public Deck() {
			this.cards = new int[52];
			// Put cards in correct order
			for (int i = 0; i < cards.length; i++) 
				cards[i] = i;
		}
		
		/* Takes 52 integers as input showing the particular shuffle */
		private void getShuffleArray() {
			for (int j = 0; j < n; j++) 
				for (int i = 0; i < 52; i++)	shuffle[j][i] = sc.nextInt()-1;
		}
		
		/* Shuffle the deck */
		private void performShuffle(int num) {
			// num is the shuffle number observed
			// make a defensive copy of the array
			int[] cardsCopy = new int[52];
			System.arraycopy(cards, 0, cardsCopy, 0, cards.length);	// not re-inventing the wheel here
			for (int j = 0; j < 52; j++) {
				int i = shuffle[num-1][j];  // as array indexing starts from 0
				// i in position j means that ith card in deck moved to position j
                cards[j] = cardsCopy[i];    // this is our final array
			}
		}
		
        /* Display the final cards stack as per problem */
        private void displayFinalStack() {
            for (int j = 0; j < 52; j++) {
                int i = cards[j]+1;
                if (i <= 13) {
                    // clubs is the suite
                    if (i == 10)    System.out.print("Jack ");
                    else if (i == 11)   System.out.print("Queen ");
                    else if (i == 12)   System.out.print("King ");
                    else if (i == 13)   System.out.print("Ace ");
                    else    System.out.print((i+1)%13 + " ");
                    System.out.println("of Clubs");
                }
                else if (i <= 26) {
                    // Diamonds is the suite
                    if (i == 23)    System.out.print("Jack ");
                    else if (i == 24)   System.out.print("Queen ");
                    else if (i == 25)   System.out.print("King ");
                    else if (i == 26)   System.out.print("Ace ");
                    else    System.out.print((i+1)%13 + " ");
                    System.out.println("of Diamonds");
                }
                else if (i <= 39) {
                    // Hearts is the suite
                    if (i == 36)    System.out.print("Jack ");
                    else if (i == 37)   System.out.print("Queen ");
                    else if (i == 38)   System.out.print("King ");
                    else if (i == 39)   System.out.print("Ace ");
                    else    System.out.print((i+1)%13 + " ");
                    System.out.println("of Hearts");
                }
                else {
                    // Spades is the suite
                    if (i == 49)    System.out.print("Jack ");
                    else if (i == 50)   System.out.print("Queen ");
                    else if (i == 51)   System.out.print("King ");
                    else if (i == 52)   System.out.print("Ace ");
                    else    System.out.print((i+1)%13 + " ");
                    System.out.println("of Spades");
                }
            }
        }

		/* Runnable's abstract method */
		public void run() {
			// take number of possible shuffles
			this.n = sc.nextInt();
			this.shuffle = new int[n][52];	// store each shuffle
			getShuffleArray();
			sc.nextLine();	// to avoid any blank spaces
			// perform the shuffles seen
			while (sc.hasNextInt()) {
				int shuffleNum = sc.nextInt();
				performShuffle(shuffleNum);
			}
            displayFinalStack();
		}
		
	}
	
	public static void main(String[] args) {
		// create an object
		Main obj = new Main();
		obj.run();
	}
	
	/* Abstract method from Runnable */
	public void run() {
	    // get number of test cases
        int n = sc.nextInt();
        sc.nextLine();  // to take on blank line
        for (int i = 0; i < n; i++) {
            Deck deck = new Deck();
            deck.run();
            if (i != n-1) {
                System.out.println();
                sc.nextLine();
            }
        }
	}
}
