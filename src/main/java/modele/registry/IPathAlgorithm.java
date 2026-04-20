package modele.registry;

import modele.Graph;
import modele.GraphExplorationLogger;

public interface IPathAlgorithm {
    String getId();
    String getDisplayName();
    GraphExplorationLogger compute(Graph input);
}
