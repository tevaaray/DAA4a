package graph.topo;

import graph.Graph;
import metrics.Metrics;
import java.util.*;

/**
 * Kahn's algorithm for topological sorting of a DAG.
 * Returns one valid topological order.
 */
public class TopologicalSort {

    /** Topological sort using Kahn's algorithm (BFS + indegree) */
    public static List<Integer> sort(Graph g, Metrics m) {
        int n = g.size();
        int[] indeg = new int[n];

        // calculate indegree for each vertex
        for (int u = 0; u < n; u++) {
            for (Graph.Edge e : g.getAdj(u)) {  // <-- fixed here
                indeg[e.to]++;
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
            for (Graph.Edge e : g.getAdj(u)) {  // <-- fixed here
                indeg[e.to]--;
                if (indeg[e.to] == 0) {
                    q.add(e.to);
                    m.kahnPushes++;
                }
            }
        }
        m.stopTimer();
        return order;
    }
}
