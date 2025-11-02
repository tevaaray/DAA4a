package graph.scc;

import graph.Graph;
import metrics.Metrics;
import java.util.*;

/**
 * Tarjan's algorithm for finding Strongly Connected Components (SCC).
 *
 * Works in O(V + E) time using DFS and low-link values.
 *
 * Each SCC is returned as a list of vertex indices.
 */
public class TarjanSCC {
    private final Graph g;
    private final Metrics m;
    private int time;
    private int[] disc;
    private int[] low;
    private boolean[] onStack;
    private Deque<Integer> stack;
    private List<List<Integer>> components;

    public TarjanSCC(Graph g, Metrics m) {
        this.g = g;
        this.m = (m != null) ? m : new Metrics();
    }

    /**
     * Runs Tarjan's algorithm and returns the list of SCCs.
     */
    public List<List<Integer>> run() {
        int n = g.size();
        disc = new int[n];
        low = new int[n];
        onStack = new boolean[n];
        stack = new ArrayDeque<>();
        components = new ArrayList<>();
        Arrays.fill(disc, -1);
        time = 0;

        m.startTimer();
        for (int v = 0; v < n; v++) {
            if (disc[v] == -1) dfs(v);
        }
        m.stopTimer();
        return components;
    }

    private void dfs(int u) {
        disc[u] = low[u] = time++;
        stack.push(u);
        onStack[u] = true;
        m.dfsVisits++;

        for (int v : g.neighbors(u)) {
            m.dfsEdges++;
            if (disc[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // if u is root of SCC
        if (low[u] == disc[u]) {
            List<Integer> comp = new ArrayList<>();
            while (true) {
                int w = stack.pop();
                onStack[w] = false;
                comp.add(w);
                if (w == u) break;
            }
            components.add(comp);
        }
    }
}
