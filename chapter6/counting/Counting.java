import java.io.*;
import java.math.BigInteger;
import java.util.*;
// UVa 10198
// Using the UVa Judge template as a reference

class Main {
    public static void main(String args[]) {
        Main counting = new Main();
        counting.Begin();
    }

    void Begin() {
        // Read with Buffered I/O
        Scanner in = new Scanner(System.in);
        ArrayList<BigInteger> ways = new ArrayList<BigInteger>();
        ways.add(BigInteger.ZERO);
        ways.add(new BigInteger("2"));
        ways.add(new BigInteger("5"));
        ways.add(new BigInteger("13"));
        while (in.hasNext()) {
            int desiredSum = in.nextInt();
            // Like fib
            // ways(1) = 2
            // ways(2) = 5
            // ways(3) = 13
            // ways(n) = 2*ways(n-1) + ways(n-2) + ways(n-3)
            BigInteger numWays = getNumWays(ways, desiredSum);
            System.out.println(numWays.toString());
        }
    }

    private BigInteger getNumWays(ArrayList<BigInteger> ways, int num) {
        int lastFilledIndex = ways.size() - 1;
        if (lastFilledIndex >= num) {
            return ways.get(num);
        }
        if (num > lastFilledIndex) {
            for (int i = lastFilledIndex + 1; i <= num; i++) {
                ways.add(ways.get(i-1).multiply(new BigInteger("2")).add(ways.get(i-2)).add(ways.get(i-3)));
            }
        }
        return ways.get(num);
    } 
}