package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import graph.Graph;
import java.io.FileReader;
import java.io.IOException;


public class GraphLoader {

    /**
     * Loads a graph from a JSON file and returns it as a Graph object.
     */
    public static Graph loadFromJson(String path) {
        try (FileReader reader = new FileReader(path)) {
            // Parse JSON content from file
            JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();

            boolean directed = obj.get("directed").getAsBoolean();
            int n = obj.get("n").getAsInt();
            Graph g = new Graph(n, directed);

            // Read edge list
            JsonArray edges = obj.getAsJsonArray("edges");
            for (JsonElement el : edges) {
                JsonObject e = el.getAsJsonObject();
                int u = e.get("u").getAsInt();
                int v = e.get("v").getAsInt();
                double w = e.has("w") ? e.get("w").getAsDouble() : 1.0;
                g.addEdge(u, v, w);
            }

            return g;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + path, e);
        }
    }
}
