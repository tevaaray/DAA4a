package graph;

import java.util.*;

/**
 * Simple directed graph class with optional node durations.
 * Each node can have a "duration" (used later in scheduling / DAG shortest path).
 *
 * Features:
 * - adjacency list representation
 * - add directed edges
 * - set/get node durations
 * - basic validation and readable toString()
 */
public class Graph {
    private final int n;                      // number of vertices
    private final List<List<Integer>> adj;    // adjacency list
    private final double[] duration;          // duration (weight) of each node

    /**
     * Create an empty directed graph with n nodes and no edges.
     * All node durations are set to 1.0 by default.
     */
    public Graph(int n) {
        if (n < 0) throw new IllegalArgumentException("Number of nodes must be non-negative");
        this.n = n;
        this.adj = new ArrayList<>(n);
        this.duration = new double[n];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            duration[i] = 1.0;
        }
    }

    /** Number of vertices in the graph. */
    public int size() { return n; }

    /** Add a directed edge u -> v. */
    public void addEdge(int u, int v) {
        checkNode(u);
        checkNode(v);
        adj.get(u).add(v);
    }

    /** Return unmodifiable list of neighbors of node u. */
    public List<Integer> neighbors(int u) {
        checkNode(u);
        return Collections.unmodifiableList(adj.get(u));
    }

    /** Set duration (weight) for node v. */
    public void setDuration(int v, double d) {
        checkNode(v);
        if (d < 0) throw new IllegalArgumentException("Duration must be non-negative");
        duration[v] = d;
    }

    /** Get duration (weight) for node v. */
    public double getDuration(int v) {
        checkNode(v);
        return duration[v];
    }

    /** Internal validation of node index. */
    private void checkNode(int v) {
        if (v < 0 || v >= n) throw new IndexOutOfBoundsException("Node index out of range: " + v);
    }

    /** Pretty-print graph structure (for debugging). */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph with " + n + " nodes\n");
        for (int i = 0; i < n; i++) {
            sb.append(i)
                    .append(" (dur=")
                    .append(duration[i])
                    .append("): ")
                    .append(adj.get(i))
                    .append("\n");
        }
        return sb.toString();
    }
}
