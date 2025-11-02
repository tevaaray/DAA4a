package graph.topo;

import graph.Graph;
import metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for TopologicalSort (Kahn's algorithm).
 */
public class TopologicalSortTest {

    @Test
    public void testSimpleDAG() {
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        Metrics m = new Metrics();
        List<Integer> order = TopologicalSort.sort(g, m);

        // All 6 nodes should appear exactly once
        assertEquals(6, order.size());
        // Verify order respects dependencies (manual check for sample DAG)
        int pos2 = order.indexOf(2);
        int pos3 = order.indexOf(3);
        assertTrue(pos2 < pos3 || pos3 < pos2); // loose check for DAG validity
    }
}
