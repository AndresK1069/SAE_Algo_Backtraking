package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Graph;
import modele.GraphExplorationLogger;
import vue.GraphVisualizer.GraphVisualizerView;

import java.util.ArrayList;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //todo Remove this
        // --- Build a test graph ---
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.addEdge(1, 3);
        graph.addEdge(0, 2);

        ArrayList<Integer> visitedNodes = new ArrayList<>();
        visitedNodes.add(0);
        visitedNodes.add(1);
        visitedNodes.add(3);
        visitedNodes.add(2);
        visitedNodes.add(4);

        ArrayList<Integer> hamiltonianPath = new ArrayList<>();
        hamiltonianPath.add(0);
        hamiltonianPath.add(1);
        hamiltonianPath.add(2);
        hamiltonianPath.add(3);
        hamiltonianPath.add(4);

        GraphExplorationLogger logger = new GraphExplorationLogger(
                graph,
                visitedNodes,
                hamiltonianPath,
                42L
        );

        GraphVisualizerView visualizer = new GraphVisualizerView();
        visualizer.setPrefSize(700, 600);
        visualizer.setLogger(logger);

        Scene scene = new Scene(visualizer, 700, 600);
        stage.setTitle("Graph Visualizer — Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}