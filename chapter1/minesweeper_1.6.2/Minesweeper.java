// 10189

import java.util.Scanner;

class Minesweeper {
    public static void main(String[] args) {
        Minesweeper myWork = new Minesweeper();
        myWork.start();
        
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        int fieldNum = 1;
        while (in.hasNext()) {
            int numRows = in.nextInt();
            int numCols = in.nextInt();
            in.nextLine();
            if (numRows != 0 && numCols != 0) {
                char[][] inputGrid = new char[numRows][numCols];
                for (int i = 0; i < numRows; i++) {
                    String row = in.nextLine();
                    for (int j = 0; j < numCols; j++) {
                        inputGrid[i][j] = row.charAt(j);
                    }
                }
                char[][] outputGrid = new char[numRows][numCols];
                // init array
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        outputGrid[i][j] = '0';
                    }
                }
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        if (inputGrid[i][j] == '*') {
                            outputGrid[i][j] = '*';
                            // Left
                            if ((j - 1 >= 0) && (inputGrid[i][j - 1] != '*')) {
                                outputGrid[i][j - 1] += 1;
                            }
                            // Right
                            if ((j + 1 < numCols) && (inputGrid[i][j + 1] != '*')) {
                                outputGrid[i][j + 1] += 1;
                            }
                            // Up
                            if ((i - 1 >= 0) && (inputGrid[i - 1][j] != '*')) {
                                outputGrid[i - 1][j] += 1;
                            }
                            // Down
                            if ((i + 1 < numRows) && (inputGrid[i + 1][j] != '*')) {
                                outputGrid[i + 1][j] += 1;
                            }
                            // Up-Left
                            if ((i - 1 >= 0) && (j - 1 >= 0) && (inputGrid[i - 1][j - 1] != '*')) {
                                outputGrid[i - 1][j - 1] += 1;
                            }
                            // Up-Right
                            if ((i - 1 >= 0) && (j + 1 < numCols) && (inputGrid[i - 1][j + 1] != '*')) {
                                outputGrid[i - 1][j + 1] += 1;
                            }
                            // Down-Left
                            if ((i + 1 < numRows) && (j - 1 >= 0) && (inputGrid[i + 1][j - 1] != '*')) {
                                outputGrid[i + 1][j - 1] += 1;
                            }
                            // Down-Right
                            if ((i + 1 < numRows) && (j + 1 < numCols) && (inputGrid[i + 1][j + 1] != '*')) {
                                outputGrid[i + 1][j + 1] += 1;
                            }
                        }
                    }
                }
                // Show output
                System.out.print("Field #" + fieldNum + ":\n");
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        System.out.print(outputGrid[i][j]);
                    }
                    System.out.print("\n");
                }
                fieldNum++;
            } else {
                System.exit(0);
            }
        }
    }
}