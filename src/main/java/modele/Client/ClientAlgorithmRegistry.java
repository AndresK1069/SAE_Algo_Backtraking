package modele.Client;

import modele.registry.AlgorithmRegistry;
import modele.registry.IPathAlgorithm;

import java.io.IOException;

public class ClientAlgorithmRegistry {
    public static void main(String[] args) throws IOException {
        AlgorithmRegistry registry = new AlgorithmRegistry();

        System.out.println("Available algorithms:");
        for (IPathAlgorithm algo : registry.getChAlgorithms()) {
            System.out.println(algo.getId() + " -> " + algo.getDisplayName());
        }
    }
}
