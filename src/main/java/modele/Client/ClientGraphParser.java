package modele.Client;

import modele.GraphParser;

import java.io.IOException;

public class ClientGraphParser {
    public static void main(String[] args) throws IOException {
        GraphParser graphParser = new GraphParser();
        graphParser.preloadGraph("data/");

        System.out.println(graphParser.getGraphs().getFirst());
    }
}
