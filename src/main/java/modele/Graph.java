package modele;

import java.util.ArrayList;
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
        for(Integer i : this.chListAdjacence.keySet()) {
            chListAdjacence.get(i);
            sb.append(i).append(" : ").append(this.chListAdjacence.get(i)).append("\n");
        }
        return sb.toString();
    }
}
