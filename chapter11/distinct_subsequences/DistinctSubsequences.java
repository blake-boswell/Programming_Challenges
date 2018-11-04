// UVa 10069

import java.util.*;
import java.math.BigInteger;

class Main {
    public static void main(String args[]) {
        Main ds = new Main();
        ds.run();
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        int numTestCases;
        int testCase = 1;
        numTestCases = Integer.parseInt(in.nextLine());
        // System.out.println(numTestCases);
        while (testCase <= numTestCases) {
            String original = in.nextLine();
            // System.out.println(original);
            String sub = in.nextLine();
            // System.out.println(sub);
            int originalLen = original.length();
            int subLen = sub.length();

            // occ[i][j] gives the number of subsequences of sub[1..i] that are in original[1..j]
            // keep the 0th row for empty sub && 0th col for empty original
            /**
             * | | |b|a|b|g|b|a|g|
             * | |1|1|1|1|1|1|1|1|
             * |b|0|1|1|2|2|3|3|3|
             * |a|0|0|1|1|1|1|4|4|
             * |g|0|0|0|0|1|1|1|5| <- answer
             */
            BigInteger occ[][] = new BigInteger[subLen + 1][originalLen + 1];
            // The occurences of an empty string in a sequence is 1
            for (int i = 0; i < originalLen + 1; i++) {
                occ[0][i] = new BigInteger("1");
            }
            // The occurences of a string in an empty sequence is 0
            // Except an empty sequence in an empty sequnence is still 1
            for (int i = 1; i < subLen + 1; i++) {
                occ[i][0] = new BigInteger("0");
            }

            for (int i = 1; i < subLen + 1; i++) {
                for (int j = 1; j < originalLen + 1; j++) {
                    // When the letter in sub we are on appears in original
                    // Add the occurences of the previous sub[1..i-1] in original[1..j-1] with the occurences of sub[1..i] in original[1..j-1]
                    if (sub.charAt(i - 1) == original.charAt(j - 1)) {
                        occ[i][j] = occ[i - 1][j - 1].add(occ[i][j - 1]);
                    } else {
                        occ[i][j] = occ[i][j - 1];
                    }
                }
            }

            // System.out.println("GRID:");
            // for (int i = 0; i < subLen + 1; i++) {
            //     for (int j = 0; j < originalLen + 1; j++) {
            //         System.out.print(occ[i][j] + " ");
            //     }
            //     System.out.println();
            // }

            System.out.println(occ[subLen][originalLen]);
    


            testCase++;
        }
    }
}