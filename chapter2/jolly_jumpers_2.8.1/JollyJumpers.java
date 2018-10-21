// Author: Blake Boswell
// UVA code: 10038

import java.util.Scanner;
import java.util.Arrays;

// UVA submission does not like "public class"
// Instead use "class"

class JollyJumpers {
    public static void main(String[] args) {
        // Read input
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            boolean jolly = true;
            String[] numbers = line.split(" ");
            int numInts = Integer.parseInt(numbers[0]);

            // System.out.println("\n\nline: " + line + "\nnumInts: " + numInts);

            if (numInts == 1) {
                System.out.println("Jolly");
            } else {
                boolean[] range = new boolean[numInts];
                Arrays.fill(range, false);
                int previous = 0;
                int current = 0;
                for (int i = 1; i < numbers.length; i++) {
                    current = Integer.parseInt(numbers[i]);
                    // System.out.println("current: " + current);
                    // System.out.println("previous: " + previous);
                    if (i > 1) {
                        int absDifference = current - previous;
                        if (absDifference < 0) {
                            absDifference *= -1;
                        }
                        // System.out.println("absDifference: " + absDifference);
                        if (absDifference < numInts) {
                            range[absDifference] = true;
                        }
                    }
                    previous = current;
                }
                // System.out.println("Table:");
                for (int i = 1; i < numInts; i++) {
                    // System.out.println(i + ": " + range[i]);
                    if (range[i] == false) {
                        jolly = false;  
                    } 
                }

                if (jolly) {
                    System.out.println("Jolly");
                } else {
                    System.out.println("Not jolly");
                }
            }
            // System.out.println("\n\n");
            
        }
        System.exit(0);
    }
}