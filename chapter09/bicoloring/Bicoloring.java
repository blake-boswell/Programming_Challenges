// UVa 10004

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

// Using the UVa Judge template as a reference

class Main {
    public static void main(String args[]) {
        Main bicoloring = new Main();
        bicoloring.Begin();
    }

    void Begin() {
        // Read with Buffered I/O
        boolean testing = false;
        Scanner in = new Scanner(System.in);
        int numVertices = in.nextInt();
        while (numVertices != 0) {
            int numEdges = in.nextInt();
            boolean graph[][] = new boolean[numVertices][numVertices];

            int edgeCount = 0;
            while (edgeCount < numEdges) {
                int edgeFrom = in.nextInt();
                int edgeTo = in.nextInt();
                graph[edgeFrom][edgeTo] = true;
                edgeCount++;
            }
            // printGraph(graph, numVertices);
            // System.out.println("\n\nChecking for odd cycles:");
            if (testing) {
                if (checkBicolorable(graph, numVertices)) {
                    try(FileWriter fw = new FileWriter("out.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw))
                    {
                        out.println("BICOLORABLE.");
                    } catch (IOException e) {
                        //exception handling left as an exercise for the reader
                        e.printStackTrace();
                    }
                } else {
                    try(FileWriter fw = new FileWriter("out.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw))
                    {
                        out.println("NOT BICOLORABLE.");
                    } catch (IOException e) {
                        //exception handling left as an exercise for the reader
                        e.printStackTrace();
                    }
                }
            } else {
                
                if (checkBicolorable(graph, numVertices)) {
                    System.out.println("BICOLORABLE.");
                } else {
                    System.out.println("NOT BICOLORABLE.");
                }
            }
            

            numVertices = in.nextInt();
        }
    }

    public static void printGraph(boolean graph[][], int numVertices) {
        System.out.println("\n\nPrinting Graph\n");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print((graph[i][j] ? 1 : 0)+ " ");
            }
            System.out.println();
        }
    }

    // Loop through all vertices, ensuring that we get disconnected graphs
    public static boolean checkBicolorable(boolean graph[][], int numVertices) {
        // Colors 1 0 -1 (red, none, black)
        final int RED = 1;
        final int NONE = 0;
        final int BLACK = -1; 
        int colors[] = new int[numVertices];
        // for (int i = 0; i < numVertices; i++) {
        //     System.out.println(colors[i]);
        //     colors[i] = 0;
        // }
        
        for (int i = 0; i < numVertices; i++) {
            if (colors[i] == NONE && !checkBicolorableUtil(graph, numVertices, colors)) {
                return false;
            }
        }
        return true;

    }

    public static boolean checkBicolorableUtil(boolean graph[][], int numVertices, int colors[]) {
        final int RED = 1;
        final int NONE = 0;
        final int BLACK = -1;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        colors[0] = RED;
        queue.add(0);
        while (!queue.isEmpty()) {
            // pop first ele in queue
            int currentVertex = queue.poll();

            // Check for self-loop
            if (graph[currentVertex][currentVertex]) {
                return false;
            }

            // Check uncolored adjacencies
            for (int i = 0; i < numVertices; i++) {
                if (graph[currentVertex][i]) {
                    if (colors[i] == NONE) {
                        // Opposite of parent (currentVertex)
                        colors[i] = -1 * colors[currentVertex];
                        // Add to the queue to check this vertex's adjacent vertices
                        queue.add(i);
                    } else if (colors[i] == colors[currentVertex]) {
                        return false;
                    }
                }
            }
        }
        // for (int i = 0; i < numVertices; i++) {
        //     for (int j = 0; j < numVertices; j++) {
        //         if (graph[i][j] == true) {
        //             if (colors[j] == NONE) {
        //                 // Opposite of parent
        //                 colors[j] = -1 * colors[i];
        //             } else if (colors[j] == colors[i]) {
        //                 return false;
        //             }
        //         }
        //     }
        // }
        return true;
    }
}
