import java.util.Scanner;
public class Main implements Runnable {
    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    private class MineMapper {
        /* Processes a single minefield */

        private char[][] map;   // the minefield
        private int rows, cols; // presumably <= 100

        public MineMapper(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.map = new char[rows][cols];
        }

        public void getMineField() {
            /* Get mine data from STDIN */
            for (int i = 0; i < rows; i++) {
                char[] input = sc.nextLine().toCharArray();
                for (int j = 0; j < cols; j++)  map[i][j] = input[j];
            }
        }

        public void showMineField() {
            /* DEBUG function -> Shows you the mine field */
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++)
                    System.out.print(map[i][j]);
                System.out.println();   // change line for next row
            }
        }

        public void printMineFieldInfo() {
            /* Displays information about the minefield */
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (map[i][j] == '*') {
                        // this is a mine
                        System.out.print('*');
                        continue;   // no need for further processing
                    }
                    int count = 0;  // number of adjacent mines
                    // check up
                    if (i >= 1) count += checkRow(i-1, j);
                    // check current row
                    count += checkRow(i, j);    // we can use checkrow as current cell is not a mine
                    // check down
                    if (i < rows-1) count += checkRow(i+1, j);
                    System.out.print(count);
                }
                System.out.println();   // row over change line
            }
        }

        /* helper method to printMineFieldInfo() */
        private int checkRow(int row, int col) {
            int count  = 0; // number of mines of interest in this row
            // check left of col
            if ((col >= 1) && (map[row][col-1] == '*'))  count++;
            // check col position
            if (map[row][col] == '*')   count++;
            //check right of col
            if ((col+1 < cols) && (map[row][col+1] == '*')) count++;
            return count;
        }
    }

    public static void main(String[] args) {
        // create an object
        Main obj = new Main();
        obj.run();
    }

    public void run() {
        // main function to call other functions
        int count = 1;
        while (true) {
            int i = sc.nextInt();   int j = sc.nextInt();
            // read newline character
            sc.nextLine();
            if ((i == 0) && (j == 0))   break;
            if (count != 1) System.out.println();
            MineMapper mapper = new MineMapper(i, j);
            mapper.getMineField();
            System.out.println("Field #" + count++ + ":");
            mapper.printMineFieldInfo();
        }
    }
}