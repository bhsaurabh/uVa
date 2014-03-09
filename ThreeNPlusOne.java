import java.util.Scanner;
import java.util.Hashtable;

public class Main {
    // Main is the solution class needed by uVa judge
    // This program can involve too many computations. Store Numbers in a hashtable
    Hashtable<Long, Long> table;

    public Main() {
        table = new Hashtable<Long, Long>();
    }

    private long getCycleCount(long num) {
        // get length till 1 is reached
        if (table.get(num) != null) return table.get(num);
        long count = 1; // maintains length of cycle
        if (num == 1)   return count;
        else {
            if (num % 2 != 0)   count += getCycleCount(3*num + 1);
            else    count += getCycleCount(num >> 1);   // bit operations are much faster
        }
        table.put(num, count);  // add to hashtable for later lookups
        return count;
    }

    private long getMaxCycleInInterval(long i, long j) {
        // Using long as numbers can quickly grow beyond int's length
        /* First check if i < j */
        if (i > j)  {
            // swap i and j
            long temp = i;
            i = j;  j = temp;
        }
        
        long maxLength = 0; // keeps track of maximum cycle length
        // get cycle lengths for every number in range [i, j]
        for (long k = i; k <= j; k++) {
            long cycleCount = getCycleCount(k);
            if (cycleCount > maxLength) maxLength = cycleCount;
        }
        return maxLength;
    }

    public void run() {
        // handles execution flow
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long i = sc.nextLong();
            long j = sc.nextLong();
            // calculate and display
            System.out.println(i + " " + j + " " + getMaxCycleInInterval(i, j));
        }
    }

    /* For unit testing */
    public static void main(String[] args) {
        Main obj = new Main();
        obj.run();  // abstract method has to be imlemented
    }
}