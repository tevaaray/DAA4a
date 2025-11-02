package graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    void testAddEdge() {
        Graph g = new Graph(3, true);
        g.addEdge(0, 1, 2.5);
        g.addEdge(1, 2, 1.0);

        assertEquals(3, g.size());
        assertEquals(1, g.getAdj(0).size());
        assertEquals(1, g.getAdj(1).size());
        assertEquals(0, g.getAdj(2).size());

        Graph.Edge e = g.getAdj(0).get(0);
        assertEquals(0, e.from);
        assertEquals(1, e.to);
        assertEquals(2.5, e.weight);
    }
}
