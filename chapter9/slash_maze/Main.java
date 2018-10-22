import java.io.*;
import java.util.*;

// Using the UVa Judge template as a reference

// Some globals for readability

// Position struct
// class Position {
//     public int row;
//     public int col;
//     public boolean quad;

//     Position(int row, int col, boolean quad) {
//         this.row = row;
//         this.col = col;
//         this.quad = quad;
//     }
// }

public class Main {
    public static void main(String args[]) {
        Main primaryArithmetic = new Main();
        primaryArithmetic.Begin();
    }

    void Begin() {
        final int SCALE = 3;
        
        // Read in w/ a different scale: upper half of the slant gets 3 blocks, lower half gets 3 blocks, slash gets 3 blocks (slash = true)
        Scanner in = new Scanner(System.in);
        boolean run = true;
        int mazeCount = 1;
        while (run) {
            boolean maze[][];
            int width = in.nextInt() * SCALE;
            int height = in.nextInt() * SCALE;
            if (width != 0 || height != 0) {
                maze = new boolean[height][width];
                // Read width slashes height times
                
                for (int i = 0; i < height / SCALE; i++) {
                    String row = in.next();
                    for (int j = 0; j < width / SCALE; j++) {
                        char slash = row.charAt(j);
                        if (slash == '/') {
                            for (int a = 0; a < SCALE; a++) {
                                for (int b = 0; b < SCALE; b++) {
                                    if (a + b == (SCALE - 1)) {
                                        maze[(SCALE * i) + a][(SCALE * j) + b] = true;
                                    } else {
                                        maze[(SCALE * i) + a][(SCALE * j) + b] = false;
                                    }
                                }
                            }
                        } else {
                            for (int a = 0; a < SCALE; a++) {
                                for (int b = 0; b < SCALE; b++) {
                                    if (a - b == 0) {
                                        maze[(SCALE * i) + a][(SCALE * j) + b] = true;
                                    } else {
                                        maze[(SCALE * i) + a][(SCALE * j) + b] = false;
                                    }
                                }
                            }
                        }
                    }
                }

                System.out.println("Maze #" + mazeCount + ":");
                ArrayList<Integer> cycles = new ArrayList<>();

                boolean alreadyVisited[][] = new boolean[height][width];
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        alreadyVisited[i][j] = maze[i][j];
                    }
                }
                int row = 0;
                int col = 0;
                while (row < height && col < width) {
                    // System.out.println("Row: " + row + "\tCol: " + col);
                    // System.out.println("Already visited? " + alreadyVisited[row][col]);
                    int cycleLength = floodFill(maze, row, col, alreadyVisited, height, width);
                    // System.out.println("Cycle Length: " + cycleLength);
                    if (cycleLength != -1 && cycleLength != 0) {
                        cycles.add(cycleLength/SCALE);
                        // System.out.println("Cycle with length: " + cycleLength/3);
                    } 
                    // Look for next to floodfill
                    while ((row < height && col < width) && (alreadyVisited[row][col] == true || maze[row][col] == true)) {
                        col++;
                        if (col >= width) {
                            row++;
                            col = 0;
                        }
                    }
                }
                if (cycles.size() > 0) {
                    Collections.sort(cycles, Collections.reverseOrder());
                    System.out.println(cycles.size() + " Cycles; the longest has length " + cycles.get(0) + ".");
                } else {
                    System.out.println("There are no cycles.");
                }
                
                
                // Flood fill
                // if any recursive pieces hit a wall, return 0;
                // if no walls are hit return num spaces (falses) touched (divide by 3 later)
                // store the row, col that have been hit in an array

                

                // printMaze(maze, height, width);
                mazeCount++;
            } else {
                run = false;
            }
        }
        
    }

    public int floodFill(boolean maze[][], int row, int col, boolean alreadyVisited[][], int height, int width) {
        if (row >= height || row < 0 || col >= width || col < 0) {
            return -1;
        }
        if (maze[row][col] == true) {
            return 0;
        }
        if (alreadyVisited[row][col] == true) {
            return 0;
        }
        alreadyVisited[row][col] = true;
        int north = floodFill(maze, row - 1, col, alreadyVisited, height, width);
        int east = floodFill(maze, row, col + 1, alreadyVisited, height, width);
        int south = floodFill(maze, row + 1, col, alreadyVisited, height, width);
        int west = floodFill(maze, row, col - 1, alreadyVisited, height, width);
        if (north == -1 || east == -1 || south == -1 || west == -1) {
            return -1;
        }
        return (north + east + south + west + 1);
    }

    public static void printMaze(boolean maze[][], int height, int width) {
        System.out.println("\n\nPrinting Maze\n\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print((maze[i][j]) ? "█" : "O");
            }
            System.out.println();
        }
    }
}