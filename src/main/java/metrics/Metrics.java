package metrics;

/**
 * Metrics helper class to measure performance and count operations.
 *
 * Used across SCC, TopologicalSort, and DAG Shortest Path modules.
 */
public class Metrics {

    // --- SCC metrics ---
    public int dfsVisits = 0;
    public int dfsEdges = 0;

    // --- Topological sort metrics ---
    public int kahnPushes = 0;
    public int kahnPops = 0;

    // --- DAG shortest/longest path metrics ---
    public int relaxations = 0;

    // --- Timing ---
    private long startTime;
    private long endTime;

    /** Start measuring execution time */
    public void startTimer() {
        startTime = System.nanoTime();
    }

    /** Stop measuring execution time */
    public void stopTimer() {
        endTime = System.nanoTime();
    }

    /**
     * Return elapsed time in milliseconds (ms)
     */
    public double getElapsedMs() {
        if (endTime == 0) return 0;
        return (endTime - startTime) / 1_000_000.0;
    }

    /** Reset all metrics */
    public void reset() {
        dfsVisits = 0;
        dfsEdges = 0;
        kahnPushes = 0;
        kahnPops = 0;
        relaxations = 0;
        startTime = 0;
        endTime = 0;
    }

    @Override
    public String toString() {
        return String.format(
                "DFS visits=%d, DFS edges=%d, Kahn pushes=%d, Kahn pops=%d, relax=%d, time=%.3f ms",
                dfsVisits, dfsEdges, kahnPushes, kahnPops, relaxations, getElapsedMs()
        );
    }
}
