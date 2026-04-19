package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private final HashMap<Integer, ArrayList<Integer>> chListAdjacence;

    public Graph(HashMap<Integer, ArrayList<Integer>> parListAdjacence) {
        this.chListAdjacence = parListAdjacence;
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
