package modele.Client;

import modele.Graph;
import modele.GraphParser;

import java.io.File;
import java.io.FileNotFoundException;

public class ClientGraphParser {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data/graph1.txt");
        Graph graph = GraphParser.parseGraph(file);
        System.out.println(graph);
    }
}
