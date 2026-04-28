package vue;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import modele.GraphExplorationLogger;

public class HboxContent extends HBox {
    private GraphForm graphForm = new GraphForm();
    private GraphBox graphBox = new GraphBox();

    public HboxContent(EventHandler<ActionEvent> onSubmit) {
        initUI();
        this.graphForm.setButtons(onSubmit);
    }

    public HboxContent(GraphExplorationLogger graphExplorationLogger) {
        initUI();
        this.setGraphBox(graphExplorationLogger);
    }

    private void initUI() {
        this.setSpacing(10);
        this.getChildren().addAll(graphForm, graphBox);
    }

    public void setGraphBox(GraphExplorationLogger graphExplorationLogger) {
        graphBox.setLogger(graphExplorationLogger);
    }

}
