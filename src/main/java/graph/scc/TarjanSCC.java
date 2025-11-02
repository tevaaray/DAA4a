package graph.scc;

import graph.Graph;
import metrics.Metrics;
import java.util.*;

/**
 * Tarjan's algorithm to find Strongly Connected Components (SCCs).
 * Runs in O(V + E) time using DFS and low-link values.
 */
public class TarjanSCC {
    private final Graph g;
    private final int n;
    private final Metrics m;
    private int time;
    private int[] disc, low;
    private boolean[] stackMember;
    private Deque<Integer> stack;
    private List<List<Integer>> sccList;

    public TarjanSCC(Graph g, Metrics m) {
        this.g = g;
        this.n = g.size();
        this.m = m;
    }

    /** Run Tarjan's algorithm and return list of SCCs */
    public List<List<Integer>> run() {
        disc = new int[n];
        low = new int[n];
        stackMember = new boolean[n];
        stack = new ArrayDeque<>();
        sccList = new ArrayList<>();
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);

        m.startTimer();
        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) dfs(i);
        }
        m.stopTimer();
        return sccList;
    }

    /** Recursive DFS utility for Tarjan */
    private void dfs(int u) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        stackMember[u] = true;
        m.dfsVisits++;

        for (Graph.Edge e : g.getAdj(u)) {  // <-- fixed here
            int v = e.to;
            m.dfsEdges++;

            if (disc[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // head of SCC found
        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int v = stack.pop();
                stackMember[v] = false;
                scc.add(v);
                if (v == u) break;
            }
            sccList.add(scc);
        }
    }

    public List<List<Integer>> getSCCs() {
        return sccList;
    }
}
