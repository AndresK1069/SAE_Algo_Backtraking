package modele.algorithm;

import modele.Graph;
import modele.GraphExplorationLogger;
import modele.registry.IPathAlgorithm;


//TODO delete
public class TestAlgorithm implements IPathAlgorithm {
    @Override
    public String getId() {
        return "TestAlgorithm";
    }

    @Override
    public String getDisplayName() {
        return "Test Algorithm";
    }

    @Override
    public GraphExplorationLogger compute(Graph input) {
        return null;
    }
}
