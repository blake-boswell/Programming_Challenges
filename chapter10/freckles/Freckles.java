// UVa 10034

import java.util.*;
import java.lang.Math;

/**
 * Plan: Minimum Spanning Tree
 * 
 * EX:
 * Goal to connect all freckles in least amount of ink
 * Vertices: (1, 1) (2, 2) (2, 4) = {a, b, c}
 * <a -> b> = distance(a, b) = sqrt((b.x - a.x)^2 + (b.y - a.y)^2) = sqrt(2) = 1.414213562
 * <a -> c> = distance(a, c) = sqrt(1 + 9) = sqrt(10) X
 * <b -> c> = distance(b, c) = sqrt(4) = 2
 * solution = <a -> b -> c>
 * distance = 3.414213562 (round to 2 decimals) = 3.41
 * 
 */

class Point {
    public static int count = 0;
    public int id;
    public double x;
    public double y;
    Point(double x, double y) {
        this.id = count;
        this.x = x;
        this.y = y;
        count++;
    }
    public String toString() {
        return this.id + ": (" + this.x + ","  + this.y + ")";
    }
}

class Graph {
    public int numVertices;
    public int numEdges;
    public LinkedList<Edge> edges[];
    Graph(Point vertices[], int numVertices) {
        this.numEdges = 0;
        this.numVertices = numVertices;
        this.edges = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            edges[i] = new LinkedList<>();
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    Edge edge = new Edge(vertices[i], vertices[j]);
                    addEdge(vertices[i], edge);
                    numEdges++;
                }
                
            }
        }
        // System.out.println("Vertices:");
        // for (int i = 0; i < numVertices; i++) {
        //     System.out.println("\t" + vertices[i]);
        // }
        // System.out.println("\nEdges:");
        // for (int i = 0; i < numVertices; i++) {
        //     for (int j = 0; j < this.edges[i].size(); j++) {
        //         System.out.println("\t" + i + " -> " + this.edges[i].get(j).to + "\tweight: " + this.edges[i].get(j).weight);
        //     }
        // }
    }
    // Make the insertion keep the lightest weight edge as the first
    // Idea from: https://stackoverflow.com/questions/18144820/inserting-into-sorted-linkedlist-java
    public void addEdge(Point point, Edge edge) {
        this.numEdges++;
        if (this.edges[point.id].size() == 0) {
            // Create the first edge in the linked list
            this.edges[point.id].add(edge);
        } else if (this.edges[point.id].get(0).weight > edge.weight) {
            // Insert at the beginning of the linked list
            this.edges[point.id].add(0, edge);
        } else if (this.edges[point.id].get(this.edges[point.id].size() - 1).weight < edge.weight) {
            // Insert to end of the linked list
            this.edges[point.id].add(this.edges[point.id].size(), edge);
        } else {
            // Somewhere in the middle
            int pos = 0;
            while (this.edges[point.id].get(pos).weight < edge.weight) {
                pos++;
            }
            this.edges[point.id].add(pos, edge);
        }
    }

    public void removeEdge(int fromId, int toId) {
        for (int i = 0; i < this.edges[fromId].size(); i++) {
            if (this.edges[fromId].get(i).to.id == toId) {
                this.edges[fromId].remove(i);
                break;
            }
        }
        for (int i = 0; i < this.edges[toId].size(); i++) {
            if (this.edges[toId].get(i).to.id == fromId) {
                this.edges[toId].remove(i);
                break;
            }
        }
    }

    public void removeAllToEdges(int toId) {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < this.edges[i].size(); j++) {
                if (this.edges[i].get(j).to.id == toId) {
                    this.edges[i].remove(j);
                    break;
                }
            }
        }
    }

    public double findShortestPathSize() {
        double length = 0;
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Double> weights = new ArrayList<>();
        // Add first edge to the path
        visited.add(0);
        length += this.edges[0].get(0).weight;
        weights.add(this.edges[0].get(0).weight);
        int toId = this.edges[0].get(0).to.id;
        visited.add(toId);
        // removeEdge(0, toId);
        // removeAllToEdges(toId);
        while (visited.size() < numVertices) {
            double shortestWeight = Double.MAX_VALUE;
            int fromId = 0;
            for (int vertexId : visited) {
                for (int j = 0; j < this.edges[vertexId].size(); j++) {
                    if (this.edges[vertexId].get(j).weight < shortestWeight && visited.contains(this.edges[vertexId].get(j).to.id) == false) {
                        shortestWeight = this.edges[vertexId].get(j).weight;
                        fromId = vertexId;
                        toId = this.edges[vertexId].get(j).to.id;
                    }
                }
            }
            length += shortestWeight;
            weights.add(shortestWeight);
            visited.add(toId);
            // removeEdge(fromId, toId);
            // removeAllToEdges(toId);
        }
        

        // Set<Integer> uniques = new HashSet<Integer>(path);
        // System.out.println("Path:\n");
        // for (int i = 0; i < visited.size(); i++) {
        //     System.out.print(visited.get(i));
        //     if (i > 0) {
        //         System.out.print("\t" + weights.get(i - 1));
        //     }
        //     System.out.println();
        // }


        return length;
    }
}

class Edge {
    public Point to;
    public double weight;
    Edge(Point from, Point to) {
        this.to = to;
        this.weight = this.distance(from, to);
    }
    public double distance(Point from, Point to) {
        return Math.sqrt(Math.pow(to.x - from.x, 2) + Math.pow(to.y - from.y, 2)); 
    }
}

class Main {
    public static void main(String[] args) {
        Main freckles = new Main();
        freckles.run();
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        int numTestCases = in.nextInt();
        int currentCase = 1;
        while (currentCase <= numTestCases) {
            Point.count = 0;
            int numFreckles = in.nextInt();
            Point freckles[] = new Point[numFreckles];
            for (int i = 0; i < numFreckles; i++) {
                double x = in.nextDouble();
                double y = in.nextDouble();
                freckles[i] = new Point(x, y);
            }
            if (numFreckles == 1) {
                System.out.printf("%.2f", 0.00);
                System.out.println("\n");
            } else {
                Graph graph = new Graph(freckles, numFreckles);
                double size = graph.findShortestPathSize();
                System.out.printf("%.2f", size);
                System.out.println();
                if (currentCase != numTestCases) {
                    System.out.println();
                }
            }
            // print(freckles);
            
            // print(freckles);
            currentCase++;
        }
    }

    public void print(Point freckles[]) {
        for (int i = 0; i < freckles.length; i++) {
            System.out.println("[" + i + "]\t" + freckles[i]);
        }
    }
}