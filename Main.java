import java.util.Scanner;
public class Main implements Runnable {
    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
        sc.useDelimiter(System.getProperty("line.separator"));  // use a blank line as a delimier
    }

    private class Machine implements Runnable {
        /* Represents a single machine. We use a single machine for each test case */
        private int[] registers;
        private int[] ram;
        private int ip; // instruction pointer

        public Machine() {
            // initialise variables
            this.registers = new int[10];   // each machine has 10 registers
            this.ram = new int[1000];   // each machine has 1000 words of RAM
            this.ip = 0;    // start from 1st RAM instruction

            // all registers are initialised to 0
            for (int i = 0; i < 10; i++)    registers[i] = 0;
        }

        /* Read instructions to be stored in RAM from STDIN */
        public void getRamInsructions() {
            int i = 0;
            while (sc.hasNextInt()) ram[i++] = sc.nextInt();
            // fill in remaining RAM instructions with 000
            while (i < 1000)    ram[i++] = 0;
        }

        private void evaluate(int instr) {
            // evaluates a single instruction
            int opCode = instr / 100;
            int d = (instr - (opCode*100)) / 10;
            int n = instr % 10;
            switch(opCode) {
                case 2:
                    // set register d to n
                    registers[d] = n;
                    break;
                case 3:
                    // add n to register d
                    registers[d] += n;
                    registers[d] %= 1000;
                    break;
                case 4:
                    // multiply register d by n
                    registers[d] *= n;
                    registers[d] %= 1000;
                    break;
                case 5:
                    // set register d to value of register s
                    registers[d] = registers[n];
                    break;
                case 6:
                    // add value of register n to register d
                    registers[d] = (registers[d] + registers[n]) % 1000;
                    break;
                case 7:
                    // multiply register d by the value of register n
                    registers[d] *= registers[n];
                    registers[d] %= 1000;
                    break;
                case 8:
                    // set reg d to value in ram whose address is in reg n
                    registers[d] = ram[registers[n]];
                    break;
                case 9:
                    // set value in ram whose address is in reg[n] to reg[d]
                    ram[registers[n]] = registers[d];
                    break;
                case 0:
                    // goto locaion in reg d unless reg n is 0
                    if (registers[n] != 0)
                        ip = registers[d];
                    break;
            }
        }

        public void run() {
            // executes insructions from RAM
            // 100 is halt instruction
            int count = 0;  // keep track of number of executed instructions
            while (ip < 1000) {
                count += 1;
                int instruction = ram[ip++];
                if (instruction == 100) break;  // halt
                evaluate(instruction);
            }
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.run();
    }

    /* Call other functions accordingly */
    public void run() {
        // get number of cases
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            Machine machine = new Machine();
            machine.getRamInsructions();
            machine.run();
            // separae outputs
            if (i != cases-1)   System.out.println();
            sc.nextLine();  // as we have a free space, and hasNextInt() will fail
        }
    }
}