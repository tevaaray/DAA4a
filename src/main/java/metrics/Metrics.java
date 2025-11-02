package metrics;

/**
 * Metrics collector for instrumentation.
 *
 * It stores operation counters and timing information.
 * Used to compare performance of different graph algorithms.
 */
public class Metrics {
    // ---- SCC (Tarjan) ----
    public long dfsVisits = 0;   // how many nodes were visited
    public long dfsEdges = 0;    // how many edges explored

    // ---- Topological Sort ----
    public long kahnPushes = 0;  // number of nodes pushed to queue
    public long kahnPops = 0;    // number of nodes popped from queue

    // ---- DAG Shortest/Longest Paths ----
    public long relaxations = 0; // number of relax() operations

    // ---- Timing ----
    private long startTime = 0;
    private long endTime = 0;

    /** Start timer. */
    public void startTimer() {
        startTime = System.nanoTime();
    }

    /** Stop timer. */
    public void stopTimer() {
        endTime = System.nanoTime();
    }

    /** Return elapsed time in nanoseconds. */
    public long elapsedNanos() {
        return endTime - startTime;
    }

    /** Reset all counters and timers. */
    public void reset() {
        dfsVisits = dfsEdges = kahnPushes = kahnPops = relaxations = 0;
        startTime = endTime = 0;
    }
}
