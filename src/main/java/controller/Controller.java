package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import modele.Graph;
import modele.GraphExplorationLogger;
import modele.GraphParser;
import modele.registry.AlgorithmRegistry;
import modele.registry.IPathAlgorithm;
import vue.VboxRoot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Controller implements EventHandler<Event> {
    private AlgorithmRegistry chAlgorithmRegistry;
    private GraphParser chGraphParser;
    private ComboBox<String>  chAlgorithmComboBox;
    private ComboBox<Integer> chGraphComboBox;
    private VboxRoot root;

    public Controller() {}

    @Override
    public void handle(Event event) {

        if (event.getSource() instanceof Button btn) {
            switch (btn.getId()) {
                case "btnSubmit":
                    this.submitAction();
            }
        }

    }

    private void init() throws IOException {
        chAlgorithmRegistry = new AlgorithmRegistry();
        chGraphParser = new GraphParser();
        chGraphParser.preloadGraph("data/");

        chAlgorithmComboBox = (ComboBox<String>) root.lookup("#listAlgorithm");
        chGraphComboBox = (ComboBox<Integer>) root.lookup("#listGraphDisponible");

        this.addAlgo(chAlgorithmRegistry,chAlgorithmComboBox);
        this.addGraphDisponible(chGraphParser,chGraphComboBox);
    }

    private void submitAction(){
        String algoID = chAlgorithmComboBox.getSelectionModel().getSelectedItem();
        Integer graphNumber = chGraphComboBox.getSelectionModel().getSelectedItem();

        Optional<IPathAlgorithm> algo = chAlgorithmRegistry.getAlgorithmById(algoID);
        Graph graph = chGraphParser.getGraphs().get(graphNumber);

        GraphExplorationLogger log = algo.get().compute(graph);
        root.setLogger(log);
        root.setTextArea(log.toString());
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

    public void setRoot(VboxRoot root) throws IOException {
        this.root = root;
        this.init();
    }

}
