package modele;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraphParserTest {

    @TempDir
    Path tempDir;

    private GraphParser graphParser;

    @BeforeEach
    void setUp() {
        graphParser = new GraphParser();
        graphParser.getGraphs().clear();
    }

    @Test
    void parseGraph_validFile_returnsCorrectGraph() throws IOException {
        File file = createTempGraphFile("1 2\n0 3\n0");
        Graph graph = GraphParser.parseGraph(file);
        assertNotNull(graph);
    }

    @Test
    void parseGraph_singleVertex_returnsGraph() throws IOException {
        File file = createTempGraphFile("0");
        Graph graph = GraphParser.parseGraph(file);
        assertNotNull(graph);
    }

    @Test
    void parseGraph_fileNotFound_throwsFileNotFoundException() {
        File nonExistentFile = new File("fichier_inexistant_xyz.txt");

        assertThrows(FileNotFoundException.class,
                () -> GraphParser.parseGraph(nonExistentFile));
    }

    @Test
    void parseGraph_emptyFile_returnsEmptyGraph() throws IOException {
        File file = createTempGraphFile("");
        Graph graph = GraphParser.parseGraph(file);
        assertNotNull(graph);
    }

    @Test
    void preloadGraph_directoryWithFiles_loadsAllGraphs() throws IOException {
        createTempGraphFile("1 2\n0\n0", "graph1.txt");
        createTempGraphFile("1\n0",      "graph2.txt");
        graphParser.preloadGraph(tempDir.toString());
        assertEquals(2, graphParser.getGraphs().size());
    }

    @Test
    void preloadGraph_emptyDirectory_loadsNoGraphs() {
        graphParser.preloadGraph(tempDir.toString());
        assertTrue(graphParser.getGraphs().isEmpty());
    }

    @Test
    void preloadGraph_invalidPath_throwsRuntimeException() {
        assertThrows(RuntimeException.class,
                () -> graphParser.preloadGraph("chemin/inexistant/"));
    }

    @Test
    void getGraphs_afterPreload_returnsNonNullList() throws IOException {
        createTempGraphFile("1\n0", "g.txt");
        graphParser.preloadGraph(tempDir.toString());
        ArrayList<Graph> graphs = graphParser.getGraphs();
        assertNotNull(graphs);
        assertFalse(graphs.isEmpty());
    }

    @Test
    void getGraphs_beforePreload_returnsEmptyList() {
        assertNotNull(graphParser.getGraphs());
    }

    private File createTempGraphFile(String content) throws IOException {
        Path file = Files.createTempFile(tempDir, "graph", ".txt");
        Files.writeString(file, content);
        return file.toFile();
    }

    private File createTempGraphFile(String content, String fileName) throws IOException {
        Path file = tempDir.resolve(fileName);
        Files.writeString(file, content);
        return file.toFile();
    }
}