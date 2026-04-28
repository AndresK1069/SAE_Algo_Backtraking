package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import modele.GraphExplorationLogger;

public class VboxRoot extends VBox {
    private HboxContent chHboxContent;
    private TextArea chTextArea = new TextArea();

    public VboxRoot(EventHandler<ActionEvent> onSubmit) {
        this.initHbox(onSubmit);
    }

    private void initHbox(EventHandler<ActionEvent> onSubmit) {
        chHboxContent = new HboxContent(onSubmit);
        chTextArea.setEditable(false);
        chTextArea.setPrefHeight(500);
        this.getChildren().addAll(chHboxContent, chTextArea);
    }

    public void setTextArea(String text) {
        chTextArea.clear();
        chTextArea.setText(text);
    }

    public void setLogger(GraphExplorationLogger logger) {
        chHboxContent.setGraphBox(logger);
    }
}