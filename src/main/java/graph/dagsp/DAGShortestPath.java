package graph.dagsp;

import graph.Graph;
import metrics.Metrics;
import graph.topo.TopologicalSort;

import java.util.*;

/**
 * Implements shortest and longest path algorithms for a DAG.
 *
 * Assumes edge weights are non-negative.
 *
 * For longest path, we invert weights or use max-DP over topological order.
 */
public class DAGShortestPath {

    /**
     * Compute shortest distances from a single source in a DAG.
     */
    public static double[] shortestPath(Graph g, int source, Metrics m) {
        int n = g.size();
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[source] = 0;

        List<Integer> order = TopologicalSort.sort(g, m);

        m.startTimer();
        for (int u : order) {
            if (dist[u] != Double.POSITIVE_INFINITY) {
                for (Graph.Edge edge : g.getAdj(u)) {
                    int v = edge.to;
                    double weight = edge.weight;
                    if (dist[v] > dist[u] + weight) {
                        dist[v] = dist[u] + weight;
                        m.relaxations++;
                    }
                }
            }
        }
        m.stopTimer();
        return dist;
    }

    /**
     * Compute the longest (critical) path using DP over topological order.
     */
    public static double[] longestPath(Graph g, int source, Metrics m) {
        int n = g.size();
        double[] dist = new double[n];
        Arrays.fill(dist, Double.NEGATIVE_INFINITY);
        dist[source] = 0;

        List<Integer> order = TopologicalSort.sort(g, m);

        m.startTimer();
        for (int u : order) {
            if (dist[u] != Double.NEGATIVE_INFINITY) {
                for (Graph.Edge edge : g.getAdj(u)) {
                    int v = edge.to;
                    double weight = edge.weight;
                    if (dist[v] < dist[u] + weight) {
                        dist[v] = dist[u] + weight;
                        m.relaxations++;
                    }
                }
            }
        }
        m.stopTimer();
        return dist;
    }

    /**
     * Reconstruct one optimal path (shortest or longest) from parent array.
     */
    public static List<Integer> reconstructPath(int[] parent, int target) {
        List<Integer> path = new ArrayList<>();
        for (int v = target; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }
}
