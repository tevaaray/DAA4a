package data;

import com.google.gson.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Graph dataset generator for Smart City / Smart Campus scheduling.
 *
 * Generates random directed graphs with configurable number of nodes, edges, and weights.
 * Each dataset is stored in /data as a JSON file.
 */
public class GraphGenerator {

    private static final Random rand = new Random();

    public static void main(String[] args) throws IOException {
        generateDataset("data/small1.json", 6, 8, true);
        generateDataset("data/small2.json", 8, 10, false);
        generateDataset("data/small3.json", 9, 12, true);

        generateDataset("data/medium1.json", 15, 25, true);
        generateDataset("data/medium2.json", 18, 30, false);
        generateDataset("data/medium3.json", 20, 35, true);

        generateDataset("data/large1.json", 30, 60, true);
        generateDataset("data/large2.json", 40, 100, false);
        generateDataset("data/large3.json", 50, 150, true);

        System.out.println("✅ 9 datasets successfully generated in /data/");
    }

    /**
     * Generates and saves a random graph to JSON file.
     * @param path file path under /data/
     * @param n number of nodes
     * @param edges number of edges
     * @param weighted whether to include weights
     */
    public static void generateDataset(String path, int n, int edges, boolean weighted) throws IOException {
        List<Map<String, Object>> edgeList = new ArrayList<>();

        for (int i = 0; i < edges; i++) {
            int from = rand.nextInt(n);
            int to = rand.nextInt(n);
            if (from == to) continue; // no self-loops
            double w = weighted ? 1 + rand.nextInt(9) : 1.0;

            Map<String, Object> edge = new HashMap<>();
            edge.put("from", from);
            edge.put("to", to);
            edge.put("weight", w);
            edgeList.add(edge);
        }

        Map<String, Object> graphJson = new LinkedHashMap<>();
        graphJson.put("nodes", n);
        graphJson.put("edges", edgeList);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fw = new FileWriter(path)) {
            gson.toJson(graphJson, fw);
        }
    }
}
