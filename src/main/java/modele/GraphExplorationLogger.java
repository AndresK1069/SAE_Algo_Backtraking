package modele;

import java.util.ArrayList;

public class GraphExplorationLogger {
    private final ArrayList<Integer> chVisitedNodes;
    private final ArrayList<Integer> chHamiltonienPath;
    private final double chExplorationTime;
    private final Graph chGraph;

    public GraphExplorationLogger(Graph parGraph,ArrayList<Integer> parVisitedNodes , ArrayList<Integer> parHamiltonienPath, double parExplorationTime) {
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

    public double getChExplorationTime() {
        return chExplorationTime;
    }

    @Override
    public String toString() {
        return String.format(
                "Path:         %s%n" +
                        "Path size:    %d%n" +
                        "Visited:      %s%n" +
                        "Visited size: %d%n" +
                        "Time (ms):    %.3f%n" +
                        "Graph:        %s",
                chHamiltonienPath,
                chHamiltonienPath.size(),
                chVisitedNodes,
                chVisitedNodes.size(),
                chExplorationTime,
                chGraph.toString().replace("\n", " | ")
        );
    }

}
