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
            HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

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

    public void preloadGraph(String filePath) {
        try {
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get(filePath));
            for (Path path : ds) {
                File file = new File(path.toString());
                Graph tmpGraph = parseGraph(file);
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
