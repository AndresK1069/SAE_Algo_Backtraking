package vue;

import javafx.scene.layout.HBox;
import modele.GraphExplorationLogger;

public class HboxContent extends HBox {

    public HboxContent(GraphExplorationLogger logger) {
        GraphForm form = new GraphForm();
        GraphBox graphBox = new GraphBox(logger);

        this.setSpacing(10);
        this.getChildren().addAll(form, graphBox);
    }
}
