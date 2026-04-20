package modele.Client;

import modele.Graph;
import modele.GraphParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClientGraphParser {
    public static void main(String[] args) throws IOException {
        GraphParser graphParser = new GraphParser();
        graphParser.preloadGraph("data/");

        System.out.println(graphParser.getGraphs().get(0));
    }
}
