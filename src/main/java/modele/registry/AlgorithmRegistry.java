package modele.registry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AlgorithmRegistry {
    private final Map<String, IPathAlgorithm> chAlgorithms =new HashMap<>();
    private static final String FILE_NAME = "modele.registry.IPathAlgorithm";
    private static final String PATH_DIC = "modele.algorithm.";
    private static final String WRITING_DIC = "target/classes/META-INF/services/";
    private static final String SCANNING_DIC = "src/main/java/modele/algorithm";

    public AlgorithmRegistry() throws IOException {
        writeAlgoPath();
        loadAlgorithms();
    }

    //TODO add snanity check for algorithms before loading
    private void loadAlgorithms() throws IOException {
        String[] classPathEntries = System.getProperty("java.class.path").split(File.pathSeparator);
        java.net.URL[] urls = new java.net.URL[classPathEntries.length];
        for (int i = 0; i < classPathEntries.length; i++) {
            urls[i] = new File(classPathEntries[i]).toURI().toURL();
        }

        // Use the current classloader as parent so IPathAlgorithm is the same
        // loaded class in both the parent and child — avoids "not a subtype" error
        ClassLoader freshLoader = new java.net.URLClassLoader(urls, this.getClass().getClassLoader());

        ServiceLoader<IPathAlgorithm> loader = ServiceLoader.load(IPathAlgorithm.class, freshLoader);
        for (IPathAlgorithm algorithm : loader) {
            if (chAlgorithms.containsKey(algorithm.getId())) {
                throw new AlgorithmRegistryException(AlgorithmRegistryExceptionType.DUPLICATE_IDENTIFIER);
            }
            chAlgorithms.put(algorithm.getId(), algorithm);
        }
    }

    private static void writeAlgoPath() throws IOException {

        // File prep
        File file = new File(WRITING_DIC, FILE_NAME);
        try {
            File dir = new File(WRITING_DIC);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Algo file name parsing
        ArrayList<String> algoNames = new ArrayList<>();
        try {
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get(SCANNING_DIC));
            for (Path path : ds) {
                File f = path.toFile();
                String[] fileName = f.getName().split("\\.");
                algoNames.add(PATH_DIC + fileName[0] + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // File scanning and additions
        String content = Files.readString(Path.of(WRITING_DIC + FILE_NAME));
        StringBuilder toAppend = new StringBuilder();
        for (String name : algoNames) {
            if (!content.contains(name)) {
                toAppend.append(name);  // collect all missing names first
            }
        }
        if (!toAppend.isEmpty()) {
            try (FileWriter fw = new FileWriter(WRITING_DIC + FILE_NAME, true)) { // true = append mode
                fw.write(toAppend.toString());
            }
        }
    }

    public List<IPathAlgorithm> getChAlgorithmsValues() {
        return new ArrayList<>(chAlgorithms.values());
    }

    public Optional<IPathAlgorithm> getAlgorithmById(String id) {
        return Optional.ofNullable(chAlgorithms.get(id));
    }
}
