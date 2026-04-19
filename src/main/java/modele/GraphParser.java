package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GraphParser {
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
