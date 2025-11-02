package graph.scc;

import graph.Graph;
import java.util.*;

/**
 * Builds condensation graph (DAG of SCCs).
 */
public class CondensationBuilder {

    public static Result build(Graph original, List<List<Integer>> components) {
        int n = original.size();
        int compCount = components.size();

        int[] compId = new int[n];
        for (int i = 0; i < compCount; i++) {
            for (int v : components.get(i)) {
                compId[v] = i;
            }
        }

        Graph dag = new Graph(compCount);
        Set<String> seen = new HashSet<>();

        for (int u = 0; u < n; u++) {
            int cu = compId[u];
            for (int v : original.neighbors(u)) {
                int cv = compId[v];
                if (cu != cv) {
                    String key = cu + "-" + cv;
                    if (seen.add(key)) dag.addEdge(cu, cv);
                }
            }
        }

        return new Result(dag, compId);
    }

    public static class Result {
        public final Graph dag;
        public final int[] compId;
        public Result(Graph dag, int[] compId) {
            this.dag = dag;
            this.compId = compId;
        }
    }
}
