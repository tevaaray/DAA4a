package graph.scc;

import graph.Graph;
import java.util.*;

/**
 * Builds condensation graph (DAG) from list of SCCs.
 * Each SCC becomes a node in the new graph.
 */
public class CondensationBuilder {

    public static Graph build(Graph original, List<List<Integer>> sccs) {
        int n = sccs.size();
        Graph dag = new Graph(n, true);

        // map each vertex to its SCC index
        int[] compIndex = new int[original.size()];
        for (int i = 0; i < sccs.size(); i++) {
            for (int v : sccs.get(i)) {
                compIndex[v] = i;
            }
        }

        // build edges between components
        Set<String> added = new HashSet<>();
        for (int u = 0; u < original.size(); u++) {
            for (Graph.Edge e : original.getAdj(u)) {  // <-- fixed here
                int a = compIndex[u];
                int b = compIndex[e.to];
                if (a != b) {
                    String key = a + "->" + b;
                    if (!added.contains(key)) {
                        dag.addEdge(a, b, e.weight);
                        added.add(key);
                    }
                }
            }
        }
        return dag;
    }
}
