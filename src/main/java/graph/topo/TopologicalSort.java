package graph.topo;

import graph.Graph;
import metrics.Metrics;
import java.util.*;

/**
 * Topological sorting for DAG using Kahn's algorithm.
 *
 * It assumes the graph is a DAG (no cycles).
 * If a cycle exists, the result will contain fewer nodes than graph.size().
 */
public class TopologicalSort {

    /**
     * Perform topological sort using Kahn's algorithm.
     * @param g DAG graph
     * @param m metrics collector
     * @return list of vertices in topological order
     */
    public static List<Integer> sort(Graph g, Metrics m) {
        int n = g.size();
        int[] indeg = new int[n];

        // Compute in-degree of each vertex
        for (int u = 0; u < n; u++) {
            for (int v : g.neighbors(u)) {
                indeg[v]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) q.add(i);
        }

        List<Integer> order = new ArrayList<>();

        m.startTimer();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            m.kahnPops++;

            for (int v : g.neighbors(u)) {
                indeg[v]--;
                m.kahnPushes++;
                if (indeg[v] == 0) q.add(v);
            }
        }
        m.stopTimer();
        return order;
    }
}
