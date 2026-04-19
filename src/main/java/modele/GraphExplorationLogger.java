package modele;

import java.util.ArrayList;

public class GraphExplorationLogger {
    private final ArrayList<Integer> chVisitedNodes;
    private final ArrayList<Integer> chHamiltonienPath;
    private final long chExplorationTime;
    private final Graph chGraph;

    public GraphExplorationLogger(Graph parGraph,ArrayList<Integer> parVisitedNodes , ArrayList<Integer> parHamiltonienPath, long parExplorationTime) {
        this.chGraph = parGraph;
        this.chVisitedNodes = parVisitedNodes;
        this.chHamiltonienPath = parHamiltonienPath;
        this.chExplorationTime = parExplorationTime;
    }

    public Graph getChGraph() {
        return chGraph;
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
