package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Graph {
    private final LinkedHashMap<Integer, ArrayList<Integer>> chListAdjacence;

    public Graph(LinkedHashMap<Integer, ArrayList<Integer>> parListAdjacence) {
        this.chListAdjacence = parListAdjacence;
    }

    public Graph() {
        this.chListAdjacence = new LinkedHashMap<>();
    }


    public void addEdge(int from, int to) {
        chListAdjacence.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        chListAdjacence.computeIfAbsent(to,   k -> new ArrayList<>()).add(from);
    }

    public HashMap<Integer, ArrayList<Integer>> getChListAdjacence() {
        return chListAdjacence;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Graph]\n");

        chListAdjacence.keySet().stream()
                .sorted()
                .forEach(key -> {
                    ArrayList<Integer> neighbors = new ArrayList<>(chListAdjacence.get(key));
                    Collections.sort(neighbors);
                    sb.append(key).append(" : ").append(neighbors).append("\n");
                });

        return sb.toString();
    }
}
