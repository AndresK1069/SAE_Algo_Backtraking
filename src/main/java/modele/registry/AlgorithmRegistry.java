package modele.registry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AlgorithmRegistry {
    private final Map<String, IPathAlgorithm> chAlgorithms = new HashMap<>();

    private static final String FILE_NAME        = "modele.registry.IPathAlgorithm";
    private static final String ALGORITHM_PKG    = "modele.algorithm";
    private static final String ALGORITHM_PKG_PATH = "modele/algorithm";
    private static final String SERVICES_DIR     = "META-INF/services/";

    public AlgorithmRegistry() throws IOException {
        writeAlgoPath();
        loadAlgorithms();
    }

    private void writeAlgoPath() throws IOException {
        List<String> classNames = scanAlgorithmClasses();
        Path serviceFile = buildServiceFile();
        appendMissingEntries(serviceFile, classNames);
    }

    private List<String> scanAlgorithmClasses() throws IOException {
        List<String> classNames = new ArrayList<>();
        String[] classPathEntries = System.getProperty("java.class.path").split(File.pathSeparator);

        for (String entry : classPathEntries) {
            File f = new File(entry);
            if (f.isDirectory()) {
                scanDirectory(f, classNames);
            } else if (entry.endsWith(".jar")) {
                scanJar(f, classNames);
            }
        }
        return classNames;
    }

    private void scanDirectory(File classesRoot, List<String> result) {
        File pkgDir = new File(classesRoot, ALGORITHM_PKG_PATH);
        if (!pkgDir.isDirectory()) return;

        File[] files = pkgDir.listFiles((dir, name) -> name.endsWith(".class"));
        if (files == null) return;

        for (File f : files) {
            String simpleName = f.getName().replace(".class", "");
            result.add(ALGORITHM_PKG + "." + simpleName);
        }
    }

    private void scanJar(File jarFile, List<String> result) throws IOException {
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(ALGORITHM_PKG_PATH + "/") && name.endsWith(".class")) {
                    String className = name.replace('/', '.').replace(".class", "");
                    result.add(className);
                }
            }
        }
    }

    private Path buildServiceFile() throws IOException {
        Path servicesDir = resolveServicesDir();
        Files.createDirectories(servicesDir);

        Path serviceFile = servicesDir.resolve(FILE_NAME);
        Files.deleteIfExists(serviceFile);
        Files.createFile(serviceFile);
        return serviceFile;
    }

    private Path resolveServicesDir() throws IOException {
        URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
        try {
            Path root = Path.of(location.toURI());
            if (root.toString().endsWith(".jar")) {
                root = root.getParent();
            }
            return root.resolve(SERVICES_DIR);
        } catch (URISyntaxException e) {
            throw new IOException("Cannot resolve services directory from location: " + location, e);
        }
    }

    private void appendMissingEntries(Path serviceFile, List<String> classNames) throws IOException {
        String existing = Files.readString(serviceFile);
        StringBuilder toAppend = new StringBuilder();

        for (String name : classNames) {
            String line = name + "\n";
            if (!existing.contains(line)) {
                toAppend.append(line);
            }
        }

        if (!toAppend.isEmpty()) {
            try (FileWriter fw = new FileWriter(serviceFile.toFile(), true)) {
                fw.write(toAppend.toString());
            }
        }
    }

    private void loadAlgorithms() throws IOException {
        String[] classPathEntries = System.getProperty("java.class.path").split(File.pathSeparator);
        java.net.URL[] urls = new java.net.URL[classPathEntries.length];
        for (int i = 0; i < classPathEntries.length; i++) {
            urls[i] = new File(classPathEntries[i]).toURI().toURL();
        }

        ClassLoader freshLoader = new java.net.URLClassLoader(urls, getClass().getClassLoader());
        ServiceLoader<IPathAlgorithm> loader = ServiceLoader.load(IPathAlgorithm.class, freshLoader);

        for (IPathAlgorithm algorithm : loader) {
            if (chAlgorithms.containsKey(algorithm.getId())) {
                throw new AlgorithmRegistryException(AlgorithmRegistryExceptionType.DUPLICATE_IDENTIFIER);
            }
            chAlgorithms.put(algorithm.getId(), algorithm);
        }
    }

    public List<IPathAlgorithm> getChAlgorithmsValues() {
        return new ArrayList<>(chAlgorithms.values());
    }

    public Optional<IPathAlgorithm> getAlgorithmById(String id) {
        return Optional.ofNullable(chAlgorithms.get(id));
    }
}