package graph.topo;

import graph.Graph;
import metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TopologicalSortTest {

    @Test
    void testSimpleDAG() {
        Graph g = new Graph(6, true);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        Metrics m = new Metrics();
        List<Integer> order = TopologicalSort.sort(g, m);

        System.out.println("Topological order: " + order);

        assertEquals(6, order.size(), "Order should contain all vertices");
        assertTrue(order.indexOf(5) < order.indexOf(2));
        assertTrue(order.indexOf(2) < order.indexOf(3));
        assertTrue(order.indexOf(3) < order.indexOf(1));
    }
}
