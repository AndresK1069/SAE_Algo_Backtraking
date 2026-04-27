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

import java.util.Optional;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AlgorithmRegistry registry = new AlgorithmRegistry();

        GraphParser graphParser = new GraphParser();
        graphParser.preloadGraph("data/");

        Optional<IPathAlgorithm> algo = registry.getAlgorithm("backtracking");

        GraphExplorationLogger log = algo.get().compute(graphParser.getGraphs().get(5));


        System.out.println("Path:         " + log.getChHamiltonienPath());
        System.out.println("Path size:    " + log.getChHamiltonienPath().size());
        System.out.println("Visited:      " + log.getChVisitedNodes());
        System.out.println("Visited size: " + log.getChVisitedNodes().size());
        System.out.println("Time (ms):    " + log.getChExplorationTime());
        System.out.println(log.getChGraph());

        VboxRoot root =new VboxRoot(log);

        Scene scene = new Scene(root, 700, 600);
        stage.setTitle("Graph Visualizer — Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}