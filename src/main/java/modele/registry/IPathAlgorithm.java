package modele.registry;

import modele.Graph;
import modele.GraphExplorationLogger;

public interface IPathAlgorithm {
    String getId();
    String getDisplayName();
    Integer getStartingNode();
    GraphExplorationLogger compute(Graph input);
}
