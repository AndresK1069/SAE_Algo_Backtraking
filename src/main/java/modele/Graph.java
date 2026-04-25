package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private final HashMap<Integer, ArrayList<Integer>> chListAdjacence;

    public Graph(HashMap<Integer, ArrayList<Integer>> parListAdjacence) {
        this.chListAdjacence = parListAdjacence;
    }

    public Graph() {
        this.chListAdjacence = new HashMap<>();
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
