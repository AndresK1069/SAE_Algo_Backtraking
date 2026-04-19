package modele;

import java.util.ArrayList;

public class GraphExplorationLogger {
    private final ArrayList<Integer> chVisitedNodes;
    private final ArrayList<Integer> chHamiltonienPath;
    private final long chExplorationTime;

    public GraphExplorationLogger(ArrayList<Integer> parVisitedNodes , ArrayList<Integer> parHamiltonienPath, long parExplorationTime) {
        this.chVisitedNodes = parVisitedNodes;
        this.chHamiltonienPath = parHamiltonienPath;
        this.chExplorationTime = parExplorationTime;
    }

    public ArrayList<Integer> getChVisitedNodes() {
        return chVisitedNodes;
    }

    public ArrayList<Integer> getChHamiltonienPath() {
        return chHamiltonienPath;
    }

    public long getChExplorationTime() {
        return chExplorationTime;
    }
}
