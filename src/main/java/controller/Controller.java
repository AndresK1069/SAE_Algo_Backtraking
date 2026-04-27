package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import modele.Graph;
import modele.GraphParser;
import modele.registry.AlgorithmRegistry;
import modele.registry.IPathAlgorithm;
import vue.VboxRoot;

import java.io.IOException;
import java.util.ArrayList;

public class Controller implements EventHandler {
    private VboxRoot root;

    public Controller(VboxRoot root) throws IOException {
        this.root = root;
        this.init();
    }

    private void init() throws IOException {
        AlgorithmRegistry registry = new AlgorithmRegistry();
        GraphParser graphParser = new GraphParser();
        graphParser.preloadGraph("data/");

        ComboBox<String> comboAlgo = (ComboBox<String>) root.lookup("#listAlgorithm");
        ComboBox<Integer> comboGraph = (ComboBox<Integer>) root.lookup("#listGraphDisponible");

        this.addAlgo(registry,comboAlgo);
        this.addGraphDisponible(graphParser,comboGraph);
    }

    @Override
    public void handle(Event event) {

    }

    private void addAlgo(AlgorithmRegistry registry , ComboBox<String> comboAlgo) {
        for (IPathAlgorithm algo : registry.getChAlgorithmsValues()) {
            comboAlgo.getItems().add(algo.getId());
        }
    }

    private void addGraphDisponible(GraphParser graphParser , ComboBox<Integer> comboGraph) {
        ArrayList<Graph> graphs = graphParser.getGraphs();
        for (int i  = 0; i < graphs.size(); i++) {
            comboGraph.getItems().add(i);
        }
    }
}
