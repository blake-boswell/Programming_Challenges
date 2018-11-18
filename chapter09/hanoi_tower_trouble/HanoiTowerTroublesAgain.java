// UVa 10276

import java.util.*;
import java.lang.Math;

class Main {
    public static void main(String[] args) {
        Main hanoi = new Main();
        hanoi.run();
    }

    void run() {
        Scanner in = new Scanner(System.in);
        int numTestCases = in.nextInt();
        int currentCaseNum = 1;
        while (currentCaseNum <= numTestCases) {
            int numPegs = in.nextInt();
            LinkedList<LinkedList<Integer>> towers = new LinkedList<>();
            for (int i = 0; i < numPegs; i++) {
                towers.add(new LinkedList<Integer>());
            }
            boolean hasMoves = true;
            int ballNum = 1;
            // int ballCount = 0;
            while (hasMoves) {
                hasMoves = false;
                for (int i = 0; i < numPegs; i++) {
                    if (towers.get(i).isEmpty()) {
                        // Push it to the stack
                        towers.get(i).add(ballNum);
                        ballNum++;
                        hasMoves = true;
                        break;
                    } else if (isPerfectSquare(towers.get(i).getLast() + ballNum)) {
                        towers.get(i).add(ballNum);
                        ballNum++;
                        hasMoves = true;
                        break;
                    }
                }
            }
            
            System.out.println(ballNum - 1);
            currentCaseNum++;
        }
    }

    public boolean isPerfectSquare(int sum) {
        double sqrtSum = Math.sqrt(sum);
        int sqrtSumInt = (int)sqrtSum;
        if ((sqrtSumInt * sqrtSumInt) == sum) {
            return true;
        }
        return false;
    }
}