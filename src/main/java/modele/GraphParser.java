package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class GraphParser {
    private static ArrayList<Graph> chGraphs = new ArrayList<>();

    /**
     *  La fonction prend un Fichier en entre et renvoie un Objet de l'instance Graph
     *  chaque ligne correspond a un sommet de 0 a n et le contenu de la ligne represente les voisins separer par un espace
     * @param file fichier que l'on veut parser
     * @return Graph
     * @throws FileNotFoundException envoie une execption si le fichier n'est pas trouver
     */
    public static Graph parseGraph(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            int lineCount = -1;
            LinkedHashMap<Integer, ArrayList<Integer>> graph = new LinkedHashMap<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isBlank()) continue;
                String[] tokens = line.split(" ");
                lineCount++;
                ArrayList<Integer> neighbours = new ArrayList<>();
                for (String token : tokens) {
                    neighbours.add(Integer.parseInt(token));
                }
                graph.put(lineCount, neighbours);
            }
            return new Graph(graph);
        }
    }

    public static Graph parseEdgeList(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {

            String firstLine = scanner.nextLine().trim();
            Graph graph = new Graph();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isBlank()) continue;

                String[] tokens = line.split(" ");
                int from = Integer.parseInt(tokens[0]);
                int to   = Integer.parseInt(tokens[1]);

                graph.addEdge(from, to);
            }

            return graph;
        }
    }

    public void preloadGraph(String filePath) {
        try {
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get(filePath));
            for (Path path : ds) {
                File file = new File(path.toString());
                Graph tmpGraph = parseEdgeList(file);
                chGraphs.add(tmpGraph);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Graph> getGraphs() {
        return chGraphs;
    }
}
