package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Graph;
import modele.GraphExplorationLogger;
import modele.GraphParser;
import modele.registry.AlgorithmRegistry;
import modele.registry.IPathAlgorithm;
import vue.GraphVisualizer.GraphVisualizerView;

import java.util.ArrayList;
import java.util.Optional;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //todo Remove this
        // --- Build a test graph ---
        Graph g = new Graph();

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        g.addEdge(0, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 9);

        g.addEdge(5, 2);
        g.addEdge(6, 4);
        g.addEdge(7, 5);

        AlgorithmRegistry registry = new AlgorithmRegistry();

        GraphParser graphParser = new GraphParser();
        graphParser.preloadGraph("data/");

        Optional<IPathAlgorithm> algo = registry.getAlgorithm("backtracking");

        //GraphExplorationLogger log = algo.get().compute(graphParser.getGraphs().get(4));
        GraphExplorationLogger log = algo.get().compute(g);
        if (log == null) {
            System.out.println("No graph with a Hamiltonian path found!");
            return;
        }

        System.out.println("Path:         " + log.getChHamiltonienPath());
        System.out.println("Path size:    " + log.getChHamiltonienPath().size());
        System.out.println("Visited:      " + log.getChVisitedNodes());
        System.out.println("Visited size: " + log.getChVisitedNodes().size());
        System.out.println("Time (ms):    " + log.getChExplorationTime());

        GraphVisualizerView visualizer = new GraphVisualizerView();
        visualizer.setPrefSize(700, 600);
        visualizer.setLogger(log);
        //visualizer.setDisplayList(log.getChHamiltonienPath()); // ← swap to path-only view

        Scene scene = new Scene(visualizer, 700, 600);
        stage.setTitle("Graph Visualizer — Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}