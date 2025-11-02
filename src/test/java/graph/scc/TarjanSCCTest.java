package graph.scc;

import graph.Graph;
import metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for TarjanSCC and condensation DAG.
 */
public class TarjanSCCTest {

    @Test
    public void testSimpleGraphWithCycle() {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0); // cycle (0,1,2)
        g.addEdge(3, 4);

        TarjanSCC tarjan = new TarjanSCC(g, new Metrics());
        List<List<Integer>> comps = tarjan.run();

        assertEquals(5, comps.stream().mapToInt(List::size).sum());
        assertTrue(comps.stream().anyMatch(c -> c.size() == 3)); // check cycle
    }
}
