import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.GraphExplorationLogger;
import modele.GraphParser;
import modele.registry.AlgorithmRegistry;
import modele.registry.IPathAlgorithm;
import vue.VboxRoot;

import java.util.Optional;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AlgorithmRegistry registry = new AlgorithmRegistry();
        GraphParser graphParser = new GraphParser();
        graphParser.preloadGraph("data/");
        Optional<IPathAlgorithm> algo = registry.getAlgorithmById("backtracking");

        GraphExplorationLogger log = algo.get().compute(graphParser.getGraphs().get(5));

        System.out.println("Path:         " + log.getChHamiltonienPath());
        System.out.println("Path size:    " + log.getChHamiltonienPath().size());
        System.out.println("Visited:      " + log.getChVisitedNodes());
        System.out.println("Visited size: " + log.getChVisitedNodes().size());
        System.out.println("Time (ms):    " + log.getChExplorationTime());
        System.out.println(log.getChGraph());


        //TODO REMOVE ABOVE

        VboxRoot root = new VboxRoot(log);
        Controller controller = new Controller(root);
        Scene scene = new Scene(root, 700, 600);
        stage.setTitle("Graph Visualizer");
        stage.setScene(scene);
        stage.show();
    }
}