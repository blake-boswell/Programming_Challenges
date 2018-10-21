import java.io.*;
import java.util.*;
import java.math.BigInteger;

// Using the UVa Judge template as a reference

class Main {
    public static void main(String args[]) {
        Main program = new Main();
        program.Begin();
    }

    void Begin() {
        // Read with Buffered I/O
        Scanner in = new Scanner(System.in);
        BigInteger numA;
        BigInteger numB;
        ArrayList<BigInteger> fibNumbers = new ArrayList<BigInteger>();
        fibNumbers.add(new BigInteger("1"));
        fibNumbers.add(new BigInteger("2"));
        boolean run = true;
        while (in.hasNext()) {
            numA = in.nextBigInteger();
            numB = in.nextBigInteger();
            
            if (numA.compareTo(BigInteger.ZERO) == 0 && numA.equals(numB)) {
                // System.out.println("Inputs: " + numA.toString() + numB.toString());
                run = false;
            } else {
                
                // boolean found = false;
                int count = 0;
                int startIndex = getStartIndex(numA, fibNumbers);
                int endIndex = -1;
                
                if (startIndex == -1) {
                    // Don't have enought fib numbers cached (didn't find the start index in our loop)
                    // Add them until the number is greater than or equal to numB
                    endIndex = addFibNumbers(numB, fibNumbers);
                } else {
                    endIndex = getEndIndex(startIndex, numB, fibNumbers);
                    if (endIndex == -1) {
                        endIndex = addFibNumbers(numB, fibNumbers);
                    }
                }
                startIndex = getStartIndex(numA, fibNumbers);
                // System.out.println("Start, End: \n\t" + startIndex + " -> " + fibNumbers.get(startIndex) + ", " + endIndex + " -> " + fibNumbers.get(endIndex));
                System.out.println(endIndex - startIndex);
            }
        }
    }

    private int getEndIndex(int startIndex, BigInteger numB, ArrayList<BigInteger> fibNumbers) {
        for (int i = startIndex; i < fibNumbers.size(); i++) {
            if (fibNumbers.get(i).compareTo(numB) == 1) {
                return i ;
            }
        }
        return -1;
    }

    private int getStartIndex(BigInteger numA, ArrayList<BigInteger> fibNumbers) {
        for (int i = 0; i < fibNumbers.size(); i++) {
            if (fibNumbers.get(i).compareTo(numA) != -1) {
                // start of fib numbers in range
                // found = true;
                return i;
            }
        }
        return -1;
    }

    private int addFibNumbers(BigInteger max, ArrayList<BigInteger> fibNumbers) {
        // System.out.println("Adding Fib Numbers:");
        int lastIndex = fibNumbers.size() - 1;
        BigInteger largestValue = fibNumbers.get(lastIndex);
        // System.out.println("\tLast index: " + lastIndex + "\n\tLargest Value (@here): " + largestValue);
        
        while (largestValue.compareTo(max) == -1) {
            // while our largest value is not greater than or equal to the max we want
            BigInteger current = fibNumbers.get(lastIndex);
            BigInteger prev = fibNumbers.get(lastIndex - 1);
            BigInteger sum = current.add(prev);
            // System.out.println("\tAdded: " + sum.toString());
            fibNumbers.add(sum);
            lastIndex++;
            largestValue = sum;
        }
        return lastIndex;
    }
}