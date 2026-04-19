package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GraphParser {

    /**
     *  La fonction prend un Fichier en entre et renvoie un Objet de l'instance Graph
     *  chaque ligne correspond a un sommet de 0 a n et le contenu de la ligne represente les voisins separer par un espace
     * @param file fichier que l'on veut parser
     * @return Graph
     * @throws FileNotFoundException envoie une execption si le fichier n'est pas trouver
     */
    public static Graph parseGraph(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int lineCount= -1;

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
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
