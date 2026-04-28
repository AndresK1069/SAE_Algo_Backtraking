package vue;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GraphForm extends GridPane {
    //TODO ajouter accessibilité
    public GraphForm() {

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("PathFinder");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(scenetitle, 0, 0, 2, 1);

        Label graphLabel = new Label("Graph :");
        this.add(graphLabel, 0, 1);

        ComboBox<Integer> graphComboBox = new ComboBox<>();
        graphComboBox.setId("listGraphDisponible");
        this.add(graphComboBox, 1, 1);

        Label algo = new Label("Algorithme :");
        this.add(algo, 0, 2);


        ComboBox<String> algoClass = new ComboBox<>();
        algoClass.setId("listAlgorithm");
        this.add(algoClass, 1, 2);
    }

    public void setButtons(EventHandler<ActionEvent> onSubmit){
        Button btn = new Button("Submit");
        btn.setId("btnSubmit");
        btn.setOnAction(onSubmit);
        this.add(btn, 0, 3);
    }

}
