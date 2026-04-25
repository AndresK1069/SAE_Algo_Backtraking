package vue.GraphVisualizer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import modele.GraphExplorationLogger;

import javafx.scene.layout.Region;
import java.util.*;


public class GraphVisualizerView extends Region {

    private static final double NODE_RADIUS = 20;
    private static final Color COLOR_DEFAULT_NODE= Color.STEELBLUE;
    private static final Color COLOR_VISITED_NODE= Color.ORANGE;
    private static final Color COLOR_PATH_NODE= Color.LIMEGREEN;
    private static final Color COLOR_CURRENT_NODE= Color.RED;
    private static final Color COLOR_DEFAULT_EDGE= Color.LIGHTGRAY;
    private static final Color COLOR_PATH_EDGE= Color.LIMEGREEN;

    private GraphExplorationLogger chLogger;
    private Map<Integer , double[]> chPositions = new HashMap<>();

    private final IntegerProperty chPathStep = new SimpleIntegerProperty(0);

    private final Canvas chCanvas = new Canvas();
    private final Slider chSlider = new Slider();
    private final Label chStepLabel = new Label("Step: 0");
    private final VBox chRoot;
    private ArrayList<Integer> chDisplayList = new ArrayList<>();

    public GraphVisualizerView() {
        chCanvas.widthProperty().bind(widthProperty());
        chCanvas.heightProperty().bind(heightProperty().subtract(60)); // leave room for slider

        chSlider.setMin(0);
        chSlider.setMajorTickUnit(1);
        chSlider.setMinorTickCount(0);
        chSlider.setSnapToTicks(true);
        chSlider.setShowTickMarks(true);

        // Bind step property to slider
        chSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            chPathStep.set(newVal.intValue());
            chStepLabel.setText("Step: " + newVal.intValue());
            redraw();
        });

        // Redraw when canvas size changes
        chCanvas.widthProperty().addListener(e -> recomputeLayout());
        chCanvas.heightProperty().addListener(e -> recomputeLayout());

        HBox controls = new HBox(10, new Label("Path step:"), chSlider, chStepLabel);
        controls.setPadding(new Insets(8));
        controls.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(chSlider, Priority.ALWAYS);

        chRoot = new VBox(chCanvas, controls);
        getChildren().add(chRoot);

        // Make VBox fill this Region
        chRoot.prefWidthProperty().bind(widthProperty());
        chRoot.prefHeightProperty().bind(heightProperty());
    }

    public void setLogger(GraphExplorationLogger logger) {
        this.chLogger = logger;
        // Default: show full visited (exploration view)
        setDisplayList(logger.getChVisitedNodes());
    }

    public void setDisplayList(ArrayList<Integer> list) {
        // Filter out negative backtrack markers for slider max
        long positiveCount = list.stream().filter(n -> n >= 0).count();
        chSlider.setMax(list.size());
        chSlider.setValue(0);
        this.chDisplayList = list; // ← new field
        recomputeLayout();
    }

    public IntegerProperty pathStepProperty() { return chPathStep; }

    private void recomputeLayout() {
        if (chLogger == null) return;

        Set<Integer> allNodes = chLogger.getChGraph().getChListAdjacence().keySet();
        double cx = chCanvas.getWidth()  / 2;
        double cy = chCanvas.getHeight() / 2;
        double r  = Math.min(cx, cy) * 0.75;

        chPositions = GraphLayout.computeCircularLayout(allNodes, cx, cy, r);
        redraw();
    }

    private void redraw() {
        if (chLogger == null) return;

        GraphicsContext gc = chCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, chCanvas.getWidth(), chCanvas.getHeight());

        int step = chPathStep.get();
        List<Integer> activeSlice = chDisplayList.subList(0, Math.min(step, chDisplayList.size()));

        // Decode the slice — negatives are backtrack markers
        Set<Integer> forwardNodes   = new HashSet<>();
        Set<Integer> backtrackNodes = new HashSet<>();
        Integer currentNode = null;

        for (int entry : activeSlice) {
            if (entry >= 0) {
                forwardNodes.add(entry);
                currentNode = entry;
            } else {
                int backtracked = -entry - 1;
                backtrackNodes.add(backtracked);
                currentNode = backtracked;
            }
        }

        Set<Integer> finalPathSet = new HashSet<>(chLogger.getChHamiltonienPath());

        // Draw edges
        Map<Integer, ArrayList<Integer>> adj = chLogger.getChGraph().getChListAdjacence();
        for (Map.Entry<Integer, ArrayList<Integer>> entry : adj.entrySet()) {
            int from = entry.getKey();
            for (int to : entry.getValue()) {
                if (from < to) drawEdge(gc, from, to, forwardNodes, finalPathSet);
            }
        }

        // Draw nodes
        for (int node : chPositions.keySet()) {
            Color color;
            if (Integer.valueOf(node).equals(currentNode)) {
                color = COLOR_CURRENT_NODE;
            } else if (backtrackNodes.contains(node) && !finalPathSet.contains(node)) {
                color = COLOR_VISITED_NODE;
            } else if (forwardNodes.contains(node) && finalPathSet.contains(node)) {
                color = COLOR_PATH_NODE;
            } else if (forwardNodes.contains(node)) {
                color = Color.ORCHID;
            } else {
                color = COLOR_DEFAULT_NODE;
            }
            drawNode(gc, node, color);
        }
    }

    private void drawEdge(GraphicsContext gc, int from, int to,
                          Set<Integer> forwardNodes, Set<Integer> finalPath) {
        double[] p1 = chPositions.get(from);
        double[] p2 = chPositions.get(to);
        if (p1 == null || p2 == null) return;

        boolean isPathEdge = forwardNodes.contains(from) && forwardNodes.contains(to)
                && finalPath.contains(from)    && finalPath.contains(to);

        gc.setStroke(isPathEdge ? COLOR_PATH_EDGE : COLOR_DEFAULT_EDGE);
        gc.setLineWidth(isPathEdge ? 3 : 1);
        gc.strokeLine(p1[0], p1[1], p2[0], p2[1]);
    }


    private void drawNode(GraphicsContext gc, int node, Color color) {
        double[] pos = chPositions.get(node);
        if (pos == null) return;

        // Shadow
        gc.setFill(Color.rgb(0, 0, 0, 0.15));
        gc.fillOval(pos[0] - NODE_RADIUS + 2, pos[1] - NODE_RADIUS + 2,
                NODE_RADIUS * 2, NODE_RADIUS * 2);

        // Node body
        gc.setFill(color);
        gc.fillOval(pos[0] - NODE_RADIUS, pos[1] - NODE_RADIUS,
                NODE_RADIUS * 2, NODE_RADIUS * 2);

        // Border
        gc.setStroke(color.darker());
        gc.setLineWidth(2);
        gc.strokeOval(pos[0] - NODE_RADIUS, pos[1] - NODE_RADIUS,
                NODE_RADIUS * 2, NODE_RADIUS * 2);

        // Label
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("System", FontWeight.BOLD, 13));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(String.valueOf(node), pos[0], pos[1]);
    }

}
