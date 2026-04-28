package modele.algorithm;

import modele.Graph;
import modele.GraphExplorationLogger;
import modele.registry.IPathAlgorithm;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BackTrackingAlgorithm implements IPathAlgorithm {
    @Override
    public String getId() {
        return "backtracking";
    }

    @Override
    public String getDisplayName() {
        return "Back tracking algorithm";
    }

    @Override
    public Integer getStartingNode() {
        return 0;
    }

    @Override
    public GraphExplorationLogger compute(Graph input) {
        long startTime = System.nanoTime();

        int n = input.getChListAdjacence().size();

        ArrayList<Integer> path         = new ArrayList<>();
        ArrayList<Integer> visited      = new ArrayList<>();

        path.add(this.getStartingNode());
        visited.add(this.getStartingNode());

        hamiltonianBackTrack(this.getStartingNode(), n, path, visited, input);

        long duration = System.nanoTime() - startTime;
        double ms = duration / 1_000_000.0;
        return new GraphExplorationLogger(input, visited, path, ms);
    }

    private boolean hamiltonianBackTrack(int current, int n, ArrayList<Integer> path,
                                         ArrayList<Integer> visited, Graph input) {
        if (path.size() == n) {
            return true;
        }

        for (int voisin : input.getChListAdjacence().getOrDefault(current, new ArrayList<>())) {
            if (!path.contains(voisin)) {
                path.add(voisin);
                visited.add(voisin);

                if (hamiltonianBackTrack(voisin, n, path, visited, input)) {
                    return true;
                }

                path.remove(path.size() - 1);
                visited.add(-current);
            }
        }
        return false;
    }


}
