package modele.Client;

import modele.GraphParser;


public class ClientGraphParser {
    public static void main(String[] args){

        GraphParser graphParser = new GraphParser();
        graphParser.preloadGraph("data/");
        System.out.println(graphParser.getGraphs().getFirst());
    }
}
