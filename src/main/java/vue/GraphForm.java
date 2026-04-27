package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import modele.Graph;
import modele.registry.IPathAlgorithm;

public class GraphForm extends GridPane {
    GridPane gridPane = new GridPane();
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

        ComboBox<Graph> graphComboBox = new ComboBox<>();
        this.add(graphComboBox, 1, 1);

        Label algo = new Label("Algorithme :");
        this.add(algo, 0, 2);


        ComboBox<IPathAlgorithm> algoClas = new ComboBox<>();
        this.add(algoClas, 1, 2);

        Button btn = new Button("Submit");
        this.add(btn, 0, 3);
    }


}
