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
				for (int i = 0; i < 52; i++)	shuffle[j][i] = sc.nextInt();
		}
		
		/* Shuffle the deck */
		private void performShuffle(int num) {
			// num is the shuffle number observed
			// make a defensive copy of the array
			int[] cardsCopy = new int[52];
			System.arraycopy(cards, 0, cardsCopy, 0, cards.length);	// not re-inventing the wheel here
			for (int j = 0; j < 52; j++) {
				int i = shuffle[num][j];
				// i in position j means that ith card in deck moved to position j
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
		}
		
	}
	
	public static void main(String[] args) {
		// create an object
		Main obj = new Main();
		obj.run();
	}
	
	/* Abstract method from Runnable */
	public void run() {
		
	}
}