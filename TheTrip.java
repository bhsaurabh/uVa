import java.util.Scanner;
public class Main implements Runnable {
    private Scanner sc;

    private class Trip {
        // inner class to handle transactions for a single trip
        private double[] payments;  // payment of each student
        private int students;   // number of students

        public Trip(int students) {
            this.students = students;
            this.payments = new double[students];
        }

        public void getPayments() {
            /* Get payments made by individual student from STDIN */
            for (int i = 0; i < students; i++)
                payments[i] = sc.nextDouble();
        }

        public void showPayments() {
            /* Debugging method to display payments array */
            for (int i = 0; i < students; i++)
                System.out.print(payments[i] + " ");
            System.out.println();
        }

        private double getMean() {
            // returns the mean of the payments
            double sum = 0.0;
            for (int i = 0; i < students; i++){
                sum += payments[i];
            }
            return Math.floor(sum / students*100) / 100;
        }

        public double getCashFlow() {
            /* Returns the total cash flow
             * Flow from ppl who have paid less to those paid more
             * => ans=(mean)*(numOfSudentsPaidLess)-(totalPaidBythem) */
            double mean = getMean();
            double cashflow = 0.0;
            // iterate over all student payments
            /* rounding logic:
             * Math.round(value*10^decimals) / (10^decimals)*/
            for (int i = 0; i < students; i++) {
                if (payments[i] < mean)
                    cashflow += mean - Math.floor(payments[i]*100)/100;
            }
            return cashflow;
        }

    }

    public Main() {
        /* Constructor */
        sc = new Scanner(System.in);
    }

    public static void main(String[] args)  {
	    // create an object
	    Main obj = new Main();
	    obj.run();	// abstract method for Runnable        
    }

    public void run() {
        // method to control flow of program
        while (true) {
            int students = sc.nextInt();    // <=1000
            if (students == 0)  break;
            // initialise a trip with numOfStudents = students
            Trip newTrip = new Trip(students);
            newTrip.getPayments();
            System.out.println("$" + String.format("%.2f", newTrip.getCashFlow()));
        }
    }
}
