/* ID: 110102 */
import java.util.*;
public class Minesweeper implements Runnable {
	/* Given an input minefield output its mine-locations and # of adjacent mines */

	private int getAdjacentMineCount(char[][] map, int i, int j, int rows, int cols) {
		// Check location (i, j) and get number of mines adjacent to it
		int count = 0;	// number of mines
		// check upper half (i - 1)
		if (i-1 >= 0) {
			// check j-1, j and j+1
			if (map[i-1][j] == '*')	count++;
			if ((j != cols-1) && (map[i-1][j+1] == '*'))	count++;
			if ((j != 0) && (map[i-1][j-1] == '*'))	count++;
		}
		// check lower half
		if (i+1 < rows) {
			// check j-1, j and j+1
			if (map[i+1][j] == '*')	count++;
			if ((j != cols-1) && (map[i+1][j+1] == '*'))	count++;
			if ((j != 0) && (map[i+1][j-1] == '*'))	count++;
		}
		// now check immediate left and right i.e. [i][j-1] and [i][j+1]
		if ((j != cols-1) && (map[i][j+1] == '*'))	count++;
		if ((j != 0) && (map[i][j-1] == '*'))	count++;
		return count;
	}

	private void printMineData(char[][] map, int rows, int cols, int count) {
		// Given a minefield map output corresponding mine info (count is the minefiled number)
		// Print the mine number
		System.out.println("Field #" + count);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (map[i][j] == '*') {
					// This is a mine. Just print * and carry on
					System.out.print('*');
				}
				else {
					// check borders and count number of mines
					int adjacentMines = getAdjacentMineCount(map, i, j, rows, cols);
					System.out.print(adjacentMines);
				}
			}
			// row over go to next line
			System.out.println();
		}
		System.out.println();		
	}

	public static void main(String[] args) {
		Minesweeper obj = new Minesweeper();
		obj.run();	// execute
	}

	public void run() {
		// Main runner program
		// giving sample test data
		//char[][] map = {{'*', '.', '.', '.'}, {'.', '.', '.', '.'}, {'.', '*', '.', '.'}, {'.', '.', '.', '.'}};
		Scanner sc = new Scanner(System.in);
		while (true) {
			int count = 0;
			int rows = sc.nextInt();
			int cols = sc.nextInt();
			if (rows== 0 && cols == 0)	break;
			char[][] map = new char[rows][cols];
			sc.nextLine();	// to take out any buffer characters
			for (int i = 0; i < rows; i++) {
				// take input as a string
				char[] input = sc.nextLine().toCharArray();
				//System.out.println("Input taken");
				for (int j = 0; j < cols; j++)
					map[i][j] = input[j];
			}
			// now print the mine information
			printMineData(map, rows, cols, ++count);
		}
	}
}