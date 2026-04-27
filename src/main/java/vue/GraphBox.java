package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modele.GraphExplorationLogger;
import vue.GraphVisualizer.GraphVisualizerView;

public class GraphBox extends VBox {
    GraphVisualizerView visualizerHamiltonPath = new GraphVisualizerView();
    GraphVisualizerView visualizerVisitedPath = new GraphVisualizerView();

    public GraphBox(GraphExplorationLogger graphExplorationLogger) {
        visualizerHamiltonPath.setPrefSize(500, 500);
        visualizerVisitedPath.setPrefSize(500, 500);

        visualizerHamiltonPath.setLogger(graphExplorationLogger);
        visualizerHamiltonPath.setDisplayList(graphExplorationLogger.getChHamiltonienPath());
        visualizerVisitedPath.setLogger(graphExplorationLogger);

        Label hamiltonLabel = new Label("chemin de hamilton :");
        Label visitedLabel = new Label("Sommais Visite :");

        this.getChildren().addAll(hamiltonLabel,visualizerHamiltonPath, visitedLabel,visualizerVisitedPath);
    }

    public GraphBox(){

    }
}
