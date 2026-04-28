package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modele.GraphExplorationLogger;
import vue.GraphVisualizer.GraphVisualizerView;

public class GraphBox extends VBox {
    private GraphVisualizerView visualizerHamiltonPath = new GraphVisualizerView();
    private GraphVisualizerView visualizerVisitedPath = new GraphVisualizerView();

    private Label hamiltonLabel = new Label("chemin de hamilton :");
    private Label visitedLabel = new Label("Sommais Visite :");

    public GraphBox() {
        initUI();
    }

    public GraphBox(GraphExplorationLogger graphExplorationLogger) {
        initUI();
        setLogger(graphExplorationLogger);
    }

    private void initUI() {
        visualizerHamiltonPath.setPrefSize(500, 500);
        visualizerVisitedPath.setPrefSize(500, 500);

        this.getChildren().addAll(hamiltonLabel, visualizerHamiltonPath, visitedLabel, visualizerVisitedPath);
    }

    public void setLogger(GraphExplorationLogger graphExplorationLogger) {
        visualizerHamiltonPath.setLogger(graphExplorationLogger);
        visualizerVisitedPath.setLogger(graphExplorationLogger);
        visualizerHamiltonPath.setDisplayList(graphExplorationLogger.getChHamiltonienPath());
    }
}
