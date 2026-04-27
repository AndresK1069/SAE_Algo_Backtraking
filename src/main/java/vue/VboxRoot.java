package vue;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import modele.GraphExplorationLogger;

public class VboxRoot extends VBox {
    public VboxRoot(GraphExplorationLogger logger) {
        TextArea ta = new TextArea();
        ta.setEditable(false);

        HboxContent hb = new HboxContent(logger);

        this.getChildren().addAll(hb,ta);
    }
}
