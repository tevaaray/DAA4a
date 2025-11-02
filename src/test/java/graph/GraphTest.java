package graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic unit test for Graph class.
 */
public class GraphTest {

    @Test
    public void testAddEdgeAndDurations() {
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.setDuration(2, 5.0);

        assertEquals(3, g.size());
        assertTrue(g.neighbors(0).contains(1));
        assertEquals(5.0, g.getDuration(2));
    }
}
