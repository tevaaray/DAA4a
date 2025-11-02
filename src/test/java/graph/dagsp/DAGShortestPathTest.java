package graph.dagsp;

import graph.Graph;
import metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DAGShortestPathTest {

    @Test
    void testShortestPath() {
        Graph g = new Graph(6, true);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        Metrics m = new Metrics();
        double[] dist = DAGShortestPath.shortestPath(g, 1, m);

        assertEquals(0, dist[1]);
        assertTrue(dist[5] < 10); // sanity check
    }

    @Test
    void testLongestPath() {
        Graph g = new Graph(6, true);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        Metrics m = new Metrics();
        double[] dist = DAGShortestPath.longestPath(g, 1, m);

        assertEquals(0, dist[1]);
        assertTrue(dist[5] > 0); // must be longer than direct path
    }
}
