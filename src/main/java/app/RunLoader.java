package app;

import utils.GraphLoader;
import graph.Graph;

public class RunLoader {
    public static void main(String[] args) {
        Graph g = GraphLoader.loadFromJson("data/tasks.json");
        System.out.println("Loaded graph:");
        System.out.println(g);
    }
}
