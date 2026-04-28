import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vue.VboxRoot;

public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller();
        VboxRoot root = new VboxRoot(controller::handle);
        controller.setRoot(root);
        Scene scene = new Scene(root, 1000, 900);
        stage.setTitle("Graph Visualizer");
        stage.setScene(scene);
        stage.show();
    }
}