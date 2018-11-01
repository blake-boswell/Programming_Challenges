# Graph Theory

## Degree Properties
- The sum of vertex degrees in any undirected graph = 2 * (number of edges)
- Sum of in degrees = sum of out degrees
### Trees
- Undirected graphs which contain no cycles
- A leaf of a tree is a vertex of degree 1
- Every n-vertex tree contains n - 1 edges
### Rooted Trees
- Directed graphs where every node except the root has in-degree 1
- Leaves are nodes with out-degree 0
#### Binary Trees
- Rooted trees where every vertex has an out-degree of 0 or 2
- At least half the vertices of all binary trees must be leaves
### Spanning Tree
- Subset of edges such that E' is a tree on V, given G = (V, E) and E' is a subset of E

### Connectivity
- A graph is connected if there is an undirected path between every pair of vertices
#### Vertex (edge) Connectivity
- The smallest number of vertices (edges) which must be delted to disconnect the graph
#### Articulation Vertex
- A single vertex whose deltion disconnects the graph
#### Biconnected
- A graph without an articulation vertex
#### Bridge
- A single edge whose deletion disconnects the graph
#### Edge-Biconnected
- A graph without a bridge
#### Strongly Connected Components
- Partitioning the graph into chunks such that there are directed paths between all pairs of vertices within a given chunk

### Cycles in Graphs
#### Eulerian Cycle
- Visits every edge of the graph exactly once
- Undirected graph contains an Eulerian cycle if it is connected and every vertex is even degree
    - The circuit must enter and exit every vertex, so all degrees must be even
    - Can find a cycle with DFS, then delete the edges on this cycle will leave each vertex with an even degree
#### Eulerian Paths
- Visit every edge exactly once, but may not end up where they started from
#### Hamiltonian Cycle
- Visits every vertex of the graph exactly once
- No effeicient algorithms
    - Solve with backtracking (backtrack whenever there does not exist an edge from the latest vertex to an unvisited one)
    - If the graph is larger we need a different formula

### Planar Graphs
- There is a tight relation between the number of vertices n, edges m, and faces f of any planar graph
- Euler's formula states that n - m + f = 2
- Trees contain n - 1 edges, so any planar drawing of a tree has exactly one face (the outside face)
- Algorithms to test the planarity of a graph & find non-corssing embeddings
    - Euler's formula gives a way to prove certain graphs are not planar

## Minimum Spanning Trees
- The spanning tree whose sum of edge weights is the smallest possible
- The answer wheenever we need to connect a set of points by the smallest amount of roadway, wire, or pipe
- 2 main algorithms
    - Kruskal's
    - Prim's
    ![graph example](https://github.com/blake-boswell/Programming_Challenges/tree/master/chapter10/images/graph_example.png)
        - Pick vertex: a
		- Look @ vertex a
		- Pick least cost edge ( a -> c , total cost = 5)
		- Pick another edge that connects our current spanning tree to the rest that we want
		- Pick least cost edge ( c -> e, total cost += 7, total cost = 12)
		- Pick another ( e -> d, total cost += 8, total cost = 20)
		- Pick another (d -> b, total cost += 7, total cost = 27)
		- End (our tree connects the same number of vertices as the original spanning tree has) n - 1 edges (assuming n vertices)

## Network Flows and Bipartite Matching
- Weight of an edge (i,j) measures the capacity of the pipe
- Network flow problem asks for the max amount of flow which can be sent from vertex s to vertex t, while respecting the max capacity of each pipe

### Bipartite Matching
- A type of network flow problem
- A matching in a graph is a subset of edges such that no two edges share a vertex
- A matching pairs off certain vertices such that every vertex is in at most one such pair
- Graph G is bipartite or 2 colorable if the vertices can be divided into 2 sets (L and R) such that all edges in G have one vertex in L and one vertex in R
- EXAMPLE: certain vertices represent jobs to be done, others represent people who can potentially do them. The existence of an edge (j,p) means that job j can potentially be done by person p

### Ford-Fulkerson augmenting path algorithm
- For each edge, keep track of both the amount of flow going through the edge && remaining residual capacity
- Look for any path from source (connected to every vertex in L by a weight 1) to sink (connected to every vertex in R by a weight 1) that increases total flow
- Use it to augment the total flow
- Terminate on the optimal flow when no augmenting path exists

- Computes maximum flow from source to sink
    - Max amount of anything you can move from a starting node to an ending node
- Do this by finding augmenting paths from source to sink
    - Augmenting Path:
    - Edges are non-full && forward
    - OR Non-empty and backward
- While we can find an augmenting path
1. Find augmenting path
2. Compute the bottleneck capactity
    - Edge in the path with the smallest capacity
3. Augment each edge and the total flow

