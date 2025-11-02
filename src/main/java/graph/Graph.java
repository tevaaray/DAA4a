package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic directed/undirected graph structure with weighted edges.
 * Used across SCC, TopologicalSort, and DAG Shortest Path modules.
 */
public class Graph {

    private final int n;
    private final boolean directed;
    private final List<List<Edge>> adj;
    private final double[] duration; // optional node weights

    /** Edge inner class */
    public static class Edge {
        public final int from;
        public final int to;
        public final double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /** Create graph with n vertices, default directed=false */
    public Graph(int n) {
        this(n, false);
    }

    /** Create graph with n vertices and directed flag */
    public Graph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        this.adj = new ArrayList<>();
        this.duration = new double[n];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            duration[i] = 0;
        }
    }

    /** Add weighted edge */
    public void addEdge(int u, int v, double weight) {
        adj.get(u).add(new Edge(u, v, weight));
        if (!directed) {
            adj.get(v).add(new Edge(v, u, weight));
        }
    }

    /** Add unweighted edge (default weight = 1) */
    public void addEdge(int u, int v) {
        addEdge(u, v, 1.0);
    }

    /** Get adjacency list for vertex u */
    public List<Edge> getAdj(int u) {
        return adj.get(u);
    }

    /** Return number of vertices */
    public int size() {
        return n;
    }

    /** Optional node duration (used in DAG-SP variant) */
    public void setDuration(int node, double d) {
        duration[node] = d;
    }

    public double getDuration(int node) {
        return duration[node];
    }
}
